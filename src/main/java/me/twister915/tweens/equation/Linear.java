package me.twister915.tweens.equation;

public abstract class Linear extends BaseEquation {
    public static final Linear INOUT = new Linear() {
        @Override
        public double compute(double t) {
            return t;
        }
    };
}
