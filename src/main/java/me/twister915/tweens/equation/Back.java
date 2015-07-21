package me.twister915.tweens.equation;

public abstract class Back extends BaseEquation {
    protected double s = 1.70158f;

    public static final Back IN = new Back() {
        @Override
        public final double compute(double t) {
            return t*t*((s+1)*t - s);
        }
    };

    public static final Back OUT = new Back() {
        @Override
        public final double compute(double t) {
            return (t-=1)*t*((s+1)*t + s) + 1;
        }
    };

    public static final Back INOUT = new Back() {
        @Override
        public final double compute(double t) {
            if ((t*=2) < 1) return 0.5f*(t*t*(((s*=(1.525f))+1)*t - s));
            return 0.5f*((t-=2)*t*(((s*=(1.525f))+1)*t + s) + 2);
        }
    };
}
