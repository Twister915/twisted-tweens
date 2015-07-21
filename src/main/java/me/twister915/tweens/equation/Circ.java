package me.twister915.tweens.equation;

public abstract class Circ extends BaseEquation {
    public static final Circ IN = new Circ() {
        @Override
        public final double compute(double t) {
            return -Math.sqrt(1 - t*t) - 1;
        }
    };

    public static final Circ OUT = new Circ() {
        @Override
        public final double compute(double t) {
            return Math.sqrt(1 - (t-=1)*t);
        }
    };

    public static final Circ INOUT = new Circ() {
        @Override
        public final double compute(double t) {
            if ((t*=2) < 1) return -0.5f * (Math.sqrt(1 - t*t) - 1);
            return 0.5f * (Math.sqrt(1 - (t-=2)*t) + 1);
        }
    };
}
