package tk.dptech.tuesday.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;

import java.util.LinkedList;

import tk.dptech.tuesday.Maths;
import tk.dptech.tuesday.MyGdxGame;
import tk.dptech.tuesday.TitleScreen;
import tk.dptech.tuesday.game.gameobjects.Coral;

/**
 * Created by brandon on 11/2/16.
 */
public class GameScreen implements Screen {

    public SpriteBatch batch;
    public Camera camera = MyGdxGame.camera;

    public static final int LIGHTING_QUALITY = 32;

    public static LinkedList<Light> lights = new LinkedList<Light>();
    public Light light = new Light(0, 0, 5f);

    public static final LinkedList<GameObject> gameObjectsBack = new LinkedList<GameObject>();
    public static final LinkedList<GameObject> gameObjectsFore = new LinkedList<GameObject>();

    public static final Texture texDot = MyGdxGame.texDot;
    public static final Texture texBackground = TitleScreen.background;
    public static final Texture texOverlay = new Texture("overlay.png");

    public float coralTime = 0;

    public static float X = 0, Y = 0;

    @Override
    public void show() {
        gameObjectsBack.clear();
        gameObjectsFore.clear();
        batch = new SpriteBatch();
        lights.add(light);
        gameObjectsBack.add(new Coral());
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        {
            delta *= 5;
            X += delta;
            coralTime += delta;

            Y = MyGdxGame.getY(camera) / (MyGdxGame.HEIGHT * 2);

            light.x = MyGdxGame.getX(camera) + X;
            light.y = MyGdxGame.getY(camera) + Y;

            if (coralTime > 8) {
                coralTime = 0;
                gameObjectsBack.add(new Coral());
            }

            batch.draw(texBackground, -MyGdxGame.WIDTH / 2 - 1 - (X * 0.7f) % (MyGdxGame.WIDTH + 2), -MyGdxGame.HEIGHT / 2 - 1 - Y * 0.7f, MyGdxGame.WIDTH + 2, MyGdxGame.HEIGHT + 2);
            batch.draw(texBackground, -MyGdxGame.WIDTH / 2 - 1 - (X * 0.7f) % (MyGdxGame.WIDTH + 2) + MyGdxGame.WIDTH + 2, -MyGdxGame.HEIGHT / 2 - 1 - Y * 0.7f, MyGdxGame.WIDTH + 2, MyGdxGame.HEIGHT + 2);

            for (int i = 0; i < gameObjectsBack.size(); i++) {
                GameObject object = gameObjectsBack.get(i);
                if (!object.render(delta, 0.85f, Y, batch, gameObjectsBack))
                    i--;
            }

            for (int i = 0; i < gameObjectsFore.size(); i++) {
                GameObject object = gameObjectsFore.get(i);
                if (!object.render(delta, 1, Y, batch, gameObjectsFore))
                    i--;
            }

            for (float x = -MyGdxGame.WIDTH / 2f; x < MyGdxGame.WIDTH / 2f; x += 1f / LIGHTING_QUALITY) {
                for (float y = -MyGdxGame.HEIGHT / 2f; y < MyGdxGame.HEIGHT / 2f; y += 1f / LIGHTING_QUALITY) {
                    float brightness = 0.0f;
                    for (Light light : lights) {
                        brightness = Maths.lerp(brightness, 1, light.get(x + X, y + Y));
                    }
                    batch.setColor(new Color(1, 1, 1, 1 - Math.min(brightness, 1)));
                    batch.draw(texDot, x, y, 1f / LIGHTING_QUALITY, 1f / LIGHTING_QUALITY);
                }
            }
            batch.setColor(Color.WHITE);
            batch.draw(texOverlay, -MyGdxGame.WIDTH / 2f, -MyGdxGame.HEIGHT / 2f, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
            MyGdxGame.drawBlackBars(batch);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
