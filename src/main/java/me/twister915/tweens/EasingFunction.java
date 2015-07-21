package me.twister915.tweens;

import java.math.BigDecimal;

/**
 * Defines a function used to ease animation. Will augment a percentage value, refereed to as the <em>factor</em> herein, to provide a smooth animation.
 */
public interface EasingFunction {
    /**
     * Returns the augmented factor percentage using the linear factor.
     * @param linearFactor The linear factor. This is equal to the percentage (expressed in [0,1]) of time since start until finish.
     * @return The augmented factor within [0,1] based on the linear factor.
     */
    BigDecimal getFactor(BigDecimal linearFactor);
}
