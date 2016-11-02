package tk.dptech.tuesday;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import tk.dptech.tuesday.game.GameScreen;

public class TitleScreen implements Screen {

    public static final Texture background = new Texture("background.png");
    public static final Texture title = new Texture("title.png");
    public static final Texture play = new Texture("play.png");

    public Camera camera;

    public float x = 0, y = 0;
    public float xAccel = 0, yAccel = 0;
    public float xAccelL = 0, yAccelL = 0;
    public float time = 0;

    SpriteBatch batch;

    @Override
    public void show() {
        camera = MyGdxGame.camera;
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        time += delta;
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        {

            Rectangle playRect = new Rectangle(-1, -0.5f, 2, 1);
            if (Gdx.input.justTouched()) {
                if (playRect.contains(MyGdxGame.getX(camera), MyGdxGame.getY(camera))) {
                    MyGdxGame.setTheScreen(new GameScreen());
                }
            }

            float newAccelX = getAccelX();
            float newAccelY = getAccelY();
            xAccel += (newAccelX - xAccelL);
            xAccelL = newAccelX;
            yAccel += (newAccelY - yAccelL);
            yAccelL = newAccelY;
            xAccel = Maths.lerp(xAccel, 0, 0.01f, delta);
            yAccel = Maths.lerp(yAccel, 0, 0.01f, delta);
            x = xAccel;// + (float) Math.cos(time / 2f) / 2f;
            y = yAccel;// + (float) Math.sin(time / 2f) / 2f;
            batch.draw(background, -5 - x, -3 - y, 10, 6);
            batch.draw(play, playRect.x, playRect.y, playRect.width, playRect.height);
            batch.draw(title, -1 + x, 0.75f + y, 2, 1);
            MyGdxGame.drawBlackBars(batch);
        }
        batch.end();
    }

    private float getAccelX() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            return Gdx.input.getAccelerometerX();
        }
        return MyGdxGame.getX(camera) / 4f;
    }

    private float getAccelY() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            return Gdx.input.getAccelerometerY();
        }
        return MyGdxGame.getY(camera) / 4f;
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
