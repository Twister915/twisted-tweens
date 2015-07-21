package me.twister915.tweens.equation;

import static java.lang.Math.PI;

public abstract class Sine extends BaseEquation {
    public static final Sine IN = new Sine() {
        @Override
        public final double compute(double t) {
            return -Math.cos(t * (PI/2)) + 1;
        }

        @Override
        public String toString() {
            return "Sine.IN";
        }
    };

    public static final Sine OUT = new Sine() {
        @Override
        public final double compute(double t) {
            return Math.sin(t * (PI/2));
        }

        @Override
        public String toString() {
            return "Sine.OUT";
        }
    };

    public static final Sine INOUT = new Sine() {
        @Override
        public final double compute(double t) {
            return -0.5f * (Math.cos(PI*t) - 1);
        }

        @Override
        public String toString() {
            return "Sine.INOUT";
        }
    };
}
