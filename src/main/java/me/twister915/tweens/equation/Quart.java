package me.twister915.tweens.equation;

public abstract class Quart extends BaseEquation {
    public static final Quart IN = new Quart() {
        @Override
        public final double compute(double t) {
            return t*t*t*t;
        }
    };

    public static final Quart OUT = new Quart() {
        @Override
        public final double compute(double t) {
            return -((t-=1)*t*t*t - 1);
        }
    };

    public static final Quart INOUT = new Quart() {
        @Override
        public final double compute(double t) {
            if ((t*=2) < 1) return 0.5f*t*t*t*t;
            return -0.5f * ((t-=2)*t*t*t - 2);
        }
    };
}
