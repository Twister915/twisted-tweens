package me.twister915.tweens.equation;

public abstract class Quint extends BaseEquation {
    public static final Quint IN = new Quint() {
        @Override
        public final double compute(double t) {
            return t*t*t*t*t;
        }
    };

    public static final Quint OUT = new Quint() {
        @Override
        public final double compute(double t) {
            return (t-=1)*t*t*t*t + 1;
        }
    };

    public static final Quint INOUT = new Quint() {
        @Override
        public final double compute(double t) {
            if ((t*=2) < 1) return 0.5f*t*t*t*t*t;
            return 0.5f*((t-=2)*t*t*t*t + 2);
        }
    };
}
