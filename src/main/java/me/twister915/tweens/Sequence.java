package me.twister915.tweens;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@EqualsAndHashCode(of = "interpolatables", callSuper = false)
public final class Sequence extends BaseInterpolateable {
    private final List<SequenceMember> interpolatables = new ArrayList<SequenceMember>();
    private long lengthMs;

    public static Sequence with(Interpolatable tween) {
        return new Sequence().and(tween);
    }

    private SequenceMember getNewSequenceMember(Interpolatable interpolatable) {
        return new SequenceMember(interpolatable, lengthMs);
    }

    public Sequence and(Interpolatable tween) {
        interpolatables.add(getNewSequenceMember(tween));
        lengthMs += tween.getLength();
        return this;
    }

    public Sequence wait(float time) {
        long l = (long) (time * 1000);
        interpolatables.add(getNewSequenceMember(new WaitInterpolate(l)));
        lengthMs += l;
        return this;
    }

    public Sequence yoyo() {
        setYoyo(!isYoyo());
        return this;
    }

    public Sequence loop() {
        setLoop(!isLoop());
        return this;
    }

    public void start(TweenManager manager) {
        setLength(lengthMs);
        Collections.sort(interpolatables, new Comparator<SequenceMember>() {
            public int compare(SequenceMember o1, SequenceMember o2) {
                return (int) (o1.getStartTime() - o2.getStartTime());
            }
        });
        manager.start(this);
    }

    public void interpolate(BigDecimal factor) throws ReflectiveOperationException {
        SequenceMember currentMember = null;
        long passedTime = getPassedTime();
        for (SequenceMember interpolatable : interpolatables)
            if (interpolatable.getStartTime() < passedTime && interpolatable.getEndTime() >= passedTime) {
                currentMember = interpolatable;
                break;
            }
        if (currentMember == null) return;
        currentMember.interpolatable.interpolate(new BigDecimal(passedTime - currentMember.getStartTime()).divide(new BigDecimal(currentMember.getLength()), 4, BigDecimal.ROUND_CEILING));
    }

    @Override
    public void setStarted(boolean started) {
        super.setStarted(started);
        for (SequenceMember interpolatable : interpolatables) interpolatable.getInterpolatable().setStarted(started);
    }

    private static final class WaitInterpolate extends BaseInterpolateable {
        public WaitInterpolate(long length) {super(length);}

        public void interpolate(BigDecimal factor) throws ReflectiveOperationException {}
    }

    @Data private static final class SequenceMember {
        private final Interpolatable interpolatable;
        private final long startTime;
        @Getter(lazy = true) private final long endTime = _getEndTime();
        @Getter(lazy = true) private final long length = _getLength();

        private long _getLength() {return getEndTime()-startTime;}
        private long _getEndTime() {return startTime + interpolatable.getLength();}
    }
}
