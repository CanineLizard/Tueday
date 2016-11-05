package tk.dptech.tuesday.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

import tk.dptech.tuesday.MyGdxGame;
import tk.dptech.tuesday.game.GameObject;

public class Coral extends GameObject {

    public static final Texture tex = new Texture("coral_1.png");

    public boolean top, right;

    public Coral() {
        super(MyGdxGame.WIDTH / 2f + 3, MyGdxGame.random.nextBoolean() ? -MyGdxGame.HEIGHT / 2f - 0.5f : MyGdxGame.HEIGHT / 2f + 0.5f, 3, 2);
        top = y > 0;
        right = MyGdxGame.random.nextBoolean();
    }

    @Override
    public boolean render(float delta, float layerSpeed, float Y, SpriteBatch batch, LinkedList<GameObject> layer) {
        x -= delta * layerSpeed;
        batch.draw(tex, x, y - Y, w * (right ? 1 : -1), h * (top ? -1 : 1));
        if (x < -MyGdxGame.WIDTH / 2f - 3) {
            layer.remove(this);
            return false;
        } else {
            return true;
        }
    }
}
