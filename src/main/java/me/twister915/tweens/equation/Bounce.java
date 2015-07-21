package me.twister915.tweens.equation;

public abstract class Bounce extends BaseEquation {
    public static final Bounce IN = new Bounce() {
        @Override
        public final double compute(double t) {
            return 1 - OUT.compute(1-t);
        }
    };

    public static final Bounce OUT = new Bounce() {
        @Override
        public final double compute(double t) {
            if (t < (1/2.75)) {
                return 7.5625f*t*t;
            } else if (t < (2/2.75)) {
                return 7.5625f*(t-=(1.5f/2.75f))*t + .75f;
            } else if (t < (2.5/2.75)) {
                return 7.5625f*(t-=(2.25f/2.75f))*t + .9375f;
            } else {
                return 7.5625f*(t-=(2.625f/2.75f))*t + .984375f;
            }
        }
    };

    public static final Bounce INOUT = new Bounce() {
        @Override
        public final double compute(double t) {
            if (t < 0.5f) return IN.compute(t*2) * .5f;
            else return OUT.compute(t*2-1) * .5f + 0.5f;
        }
    };
}
