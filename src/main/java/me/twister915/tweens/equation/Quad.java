package me.twister915.tweens.equation;

public abstract class Quad extends BaseEquation {
    public static final Quad IN = new Quad() {
        @Override
        public final double compute(double t) {
            return t*t;
        }
    };

    public static final Quad OUT = new Quad() {
        @Override
        public final double compute(double t) {
            return -t*(t-2);
        }
    };

    public static final Quad INOUT = new Quad() {
        @Override
        public final double compute(double t) {
            if ((t*=2) < 1) return 0.5f*t*t;
            return -0.5f * ((--t)*(t-2) - 1);
        }
    };
}
