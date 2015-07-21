package me.twister915.tweens.equation;

public abstract class Expo extends BaseEquation {
    public static final Expo IN = new Expo() {
        @Override
        public final double compute(double t) {
            return (t==0) ? 0 : Math.pow(2, 10 * (t - 1));
        }
    };

    public static final Expo OUT = new Expo() {
        @Override
        public final double compute(double t) {
            return (t==1) ? 1 : -Math.pow(2, -10 * t) + 1;
        }
    };

    public static final Expo INOUT = new Expo() {
        @Override
        public final double compute(double t) {
            if (t==0) return 0;
            if (t==1) return 1;
            if ((t*=2) < 1) return 0.5f * Math.pow(2, 10 * (t - 1));
            return 0.5f * (-Math.pow(2, -10 * --t) + 2);
        }
    };
}
