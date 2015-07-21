package me.twister915.tweens.equation;

import me.twister915.tweens.EasingFunction;

import java.math.BigDecimal;

abstract class BaseEquation implements EasingFunction {
    public BigDecimal getFactor(BigDecimal linearFactor) {
        return BigDecimal.valueOf(compute(linearFactor.doubleValue()));
    }

    protected abstract double compute(double v);
}
