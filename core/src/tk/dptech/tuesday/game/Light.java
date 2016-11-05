package tk.dptech.tuesday.game;

import tk.dptech.tuesday.Maths;

/**
 * Created by brandon on 11/4/2016.
 */

public class Light {

    public float x, y, intensity;

    public Light(float x, float y, float intensity) {
        this.x = x;
        this.y = y;
        this.intensity = intensity;
    }

    public float get(float x, float y) {
        return intensity / (Maths.dist(this.x, this.y, x, y) * 10 + 1);
    }

}
