package tk.dptech.tuesday;

import com.badlogic.gdx.math.Vector2;

public class Maths {

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public static float dist(Vector2 a, Vector2 b) {
        return dist(a.x, a.y, b.x, b.y);
    }

    /**
     * Checks if the number passed a threshold based on its previous value. A -> T -> B or B <- T <- A.
     *
     * @param a last value
     * @param t threshold
     * @param b current value
     * @return whether or not is passed through
     */
    public static boolean passed(double a, double t, double b) {
        return ((a <= t && t <= b) || (b <= t && t <= a));
    }

    public static double direction(float x1, float y1, float x2, float y2) {
        return Math.atan2(y2 - y1, x2 - x1);
    }

    public static float clamp(float i, float a, float b) {
        return Math.min(Math.max(a, i), b);
    }

    public static float lerp(float v0, float v1, float t) {
        return (1 - t) * v0 + t * v1;
    }

    public static float lerp(float v0, float v1, float t, float dt) {
        return lerp(v0, v1, 1 - (float) Math.pow(t, dt));
    }

    public static float lerpQuad(float bl, float br, float tr, float tl, float x, float y) {
        return 0;
    }

}
