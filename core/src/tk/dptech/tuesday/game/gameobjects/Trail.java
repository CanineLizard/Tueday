package tk.dptech.tuesday.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

import tk.dptech.tuesday.MyGdxGame;
import tk.dptech.tuesday.game.GameObject;

/**
 * Created by brandon on 11/6/2016.
 */

public class Trail extends GameObject {

    public static final Texture tex = new Texture("trail.png");

    public float time = 1;

    public Trail(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    @Override
    public boolean render(float delta, float layerSpeed, float Y, SpriteBatch batch, LinkedList<GameObject> layer) {
        y += delta/8f;
        time += delta / 2f;
        x -= delta * layerSpeed;
        batch.draw(tex, x - (w / time) / 2f, y - Y - (h / time) / 2f, w / time, h / time);
        if (x < -MyGdxGame.WIDTH / 2f - 3) {
            layer.remove(this);
            return false;
        } else {
            return true;
        }
    }
}
