package tk.dptech.tuesday.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

/**
 * Created by brandon on 11/4/2016.
 */

public abstract class GameObject {

    public float x, y, w, h;

    public GameObject(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract boolean render(float delta, float layerSpeed, float Y, SpriteBatch batch, LinkedList<GameObject> layer);

}
