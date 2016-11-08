package tk.dptech.tuesday.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;

import tk.dptech.tuesday.Maths;
import tk.dptech.tuesday.MyGdxGame;

public class Player {

    public float x, y, w, h;
    public Light light;

    public static final Texture tex = new Texture("fish.png");

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        w = 1f;
        h = 0.7f;
        light = new Light(x, y, 2);
        GameScreen.lights.add(light);
    }

    public void render(float delta, float Y, SpriteBatch batch, Camera camera) {
        light.x = x + w / 2f;
        light.y = y + h / 2f-Y;
        x = Maths.lerp(x, MyGdxGame.getX(camera) - w, 0.1f, delta);
        y = Maths.lerp(y, MyGdxGame.getY(camera) - h / 2, 0.1f, delta);
        float rot = MyGdxGame.getY(camera) - (y + h / 2f);
        batch.draw(new TextureRegion(tex), x, y - Y, w / 2f, h / 2f, w, h, 1, 1, (float) Math.toDegrees(rot));
    }
}
