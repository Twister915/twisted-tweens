package me.twister915.tweens.equation;

public abstract class Cubic extends BaseEquation{
    public static final Cubic IN = new Cubic() {
        @Override
        public final double compute(double t) {
            return t*t*t;
        }
    };

    public static final Cubic OUT = new Cubic() {
        @Override
        public final double compute(double t) {
            return (t-=1)*t*t + 1;
        }
    };

    public static final Cubic INOUT = new Cubic() {
        @Override
        public final double compute(double t) {
            if ((t*=2) < 1) return 0.5f*t*t*t;
            return 0.5f * ((t-=2)*t*t + 2);
        }
    };
}
