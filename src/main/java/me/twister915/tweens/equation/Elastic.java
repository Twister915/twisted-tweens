package me.twister915.tweens.equation;

import static java.lang.Math.PI;

public abstract class Elastic extends BaseEquation{
    public static final Elastic IN = new Elastic() {
        @Override
        public final double compute(double t) {
            double a = param_a;
            double p = param_p;
            if (t==0) return 0;  if (t==1) return 1; if (!setP) p=.3f;
            double s;
            if (!setA || a < 1) { a=1; s=p/4; }
            else s = p/(2* PI) * Math.asin(1/a);
            return -(a* Math.pow(2,10*(t-=1)) * Math.sin( (t-s)*(2*PI)/p ));
        }
    };

    public static final Elastic OUT = new Elastic() {
        @Override
        public final double compute(double t) {
            double a = param_a;
            double p = param_p;
            if (t==0) return 0;  if (t==1) return 1; if (!setP) p=.3f;
            double s;
            if (!setA || a < 1) { a=1; s=p/4; }
            else s = p/(2*PI) * Math.asin(1/a);
            return a* Math.pow(2,-10*t) * Math.sin( (t-s)*(2*PI)/p ) + 1;
        }
    };

    public static final Elastic INOUT = new Elastic() {
        @Override
        public final double compute(double t) {
            double a = param_a;
            double p = param_p;
            if (t==0) return 0;  if ((t*=2)==2) return 1; if (!setP) p=.3f*1.5f;
            double s;
            if (!setA || a < 1) { a=1; s=p/4; }
            else s = p/(2*PI) * Math.asin(1/a);
            if (t < 1) return -.5f*(a* Math.pow(2,10*(t-=1)) * Math.sin( (t-s)*(2*PI)/p ));
            return a* Math.pow(2,-10*(t-=1)) * Math.sin( (t-s)*(2*PI)/p ) *.5f + 1;
        }
    };

    protected double param_a;
    protected double param_p;
    protected boolean setA = false;
    protected boolean setP = false;
}
