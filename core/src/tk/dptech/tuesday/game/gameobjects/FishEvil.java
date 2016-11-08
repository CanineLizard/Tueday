package tk.dptech.tuesday.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;

import tk.dptech.tuesday.Maths;
import tk.dptech.tuesday.MyGdxGame;
import tk.dptech.tuesday.game.GameObject;
import tk.dptech.tuesday.game.GameScreen;
import tk.dptech.tuesday.game.Light;

public class FishEvil extends GameObject {

    public static final Texture tex = new Texture("fish_evil.png");

    public float time = 1;
    public Light light;
    public float lightTime = 0;
    public float lightDelay;

    public FishEvil() {
        super(MyGdxGame.WIDTH / 2, MyGdxGame.random.nextInt(MyGdxGame.HEIGHT) - MyGdxGame.HEIGHT / 2f, 2, 1);
        light = new Light(x + w * 0.25f, y + h * 0.5f, 0);
        GameScreen.lights.add(light);
        lightDelay = MyGdxGame.random.nextInt(5) + 3;
    }

    @Override
    public boolean render(float delta, float layerSpeed, float Y, SpriteBatch batch, LinkedList<GameObject> layer) {
        time += delta / 2f;
        lightTime += delta;
        light.x = x + w * 0.12f;
        light.y = y + h * 0.45f-Y;
        if (lightTime > lightDelay) {
            lightTime = 0;
            light.intensity = 1;
        }
        light.intensity = Maths.lerp(light.intensity, 0, 0.1f, delta);
        x -= delta * layerSpeed;
        y = Maths.lerp(y, (GameScreen.player.y + GameScreen.player.h / 2f) - h / 2, 0.6f, delta);
        float rot = (y + h / 2f) - (GameScreen.player.y + GameScreen.player.h / 2f);
        batch.draw(new TextureRegion(tex), x, y - Y, w / 2f, h / 2f, w, h, 1, 1, (float) Math.toDegrees(rot / 8f));
        if (x < -MyGdxGame.WIDTH / 2f - 3) {
            layer.remove(this);
            GameScreen.lights.remove(light);
            return false;
        } else {
            return true;
        }
    }
}
