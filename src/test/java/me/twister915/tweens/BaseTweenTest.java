package me.twister915.tweens;

import lombok.Data;
import me.twister915.tweens.equation.Quad;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

public class BaseTweenTest {
    @Test
    public void testTween() {
        TweenManager manager = new TweenManager();
        TestObject testObject = new TestObject();
        Tween.on(testObject, "obj.y", 100, 10).yoyo().withEasingFunction(Quad.INOUT).start(manager);
        Tween.on(testObject, "x", 100, 10).start(manager);
        System.out.println("Y : " + testObject.obj.y + "; X : " + testObject.x);
        for (int i = 0; i < 100; i++) {
            manager.update(100, TimeUnit.MILLISECONDS);
            System.out.println("Y : " + testObject.obj.y + "; X : " + testObject.x);
        }
        assertTrue(testObject.x == 100);
    }

    @Test
    public void testSequence() {
        TweenManager tweenManager = new TweenManager();
        TestObject testObject = new TestObject();
        Sequence.with(Tween.on(testObject, "x", 10, 5)).and(Tween.on(testObject, "x", 0, 5)).start(tweenManager);
        System.out.println("X: " + testObject.x);
        for (int i = 0; i < 500; i++) {
            try {
                tweenManager.update(100, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.err.println("Absolutely haram");
                throw new RuntimeException();
            }
            System.out.println("X: " + testObject.x);
        }
    }

    @Test
    public void testYoyo() {
        TweenManager tweenManager = new TweenManager();
        TestObject testObject = new TestObject();
        Tween.on(testObject, "x", 10, 10).yoyo().start(tweenManager);
        for (int i = 0; i < 500; i++) {
            tweenManager.update(100, TimeUnit.MILLISECONDS);
            System.out.println("x = " + testObject.x);
        }
    }

    @Test
    public void testRepeat() {
        TweenManager tweenManager = new TweenManager();
        TestObject testObject = new TestObject();
        Tween.on(testObject, "x", 10, 10).loop().start(tweenManager);
        for (int i = 0; i < 50; i++) {
            tweenManager.update(1, TimeUnit.SECONDS);
            System.out.println("x = " + testObject.x);
        }
    }

    @Data private final static class TestObject {
        private float x = 0;
        private final InnerTestObj obj = new InnerTestObj();
    }

    @Data private final static class InnerTestObj {
        private float y = 50f;
    }
}
