package tk.dptech.tuesday;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


public class MyGdxGame extends Game {

    public static final int WIDTH = 8, HEIGHT = 4;
    public static float cameraZoom = 1f;
    public static OrthographicCamera camera;
    public static OrthographicCamera stillCam;
    private SpriteBatch batch;

    public static final Random random = new Random();

    public static Texture texDot;

    // These are for the engine
    public static final int lagTime = 0;

    @Override
    public void create() {
        texDot = new Texture("dot.png");
        Gdx.input.setCatchBackKey(true);
        batch = new SpriteBatch();
        camera = new OrthographicCamera(getCamWidth(HEIGHT), HEIGHT);
        stillCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setScreen(new TitleScreen());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        super.render();
    }

    public static void drawBlackBars(SpriteBatch batch) {
        /*batch.draw(texDot, -Gdx.graphics.getWidth() / 2f, HEIGHT / 2f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(texDot, -Gdx.graphics.getWidth() / 2f, -HEIGHT / 2f - Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());*/
        batch.draw(texDot, WIDTH / 2f, -HEIGHT / 2f, Gdx.graphics.getWidth(), HEIGHT);
        batch.draw(texDot, -WIDTH / 2f, -HEIGHT / 2f, -Gdx.graphics.getWidth(), HEIGHT);
        batch.draw(texDot, -WIDTH / 2f, HEIGHT / 2f, WIDTH, Gdx.graphics.getHeight());
        batch.draw(texDot, -WIDTH / 2f, -HEIGHT / 2f, WIDTH, -Gdx.graphics.getHeight());

    }

    public static void setCameraLocation(float x, float y) {
        camera.position.x = x;
        camera.position.y = y;
        resizeStatic(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Gets the width of a camera with specified height
     *
     * @param camHeight The height of the camera.
     * @return The width of the camera.
     */
    public static int getCamWidth(int camHeight) {
        // cam.height / cam.width = width / height
        // cam.width = (width * cam.height) / height
        int camWidth = (Gdx.graphics.getWidth() * camHeight) / Gdx.graphics.getHeight();
        return camWidth;
    }

    /**
     * Gets the height of a camera with specified height
     *
     * @param camWidth The width of the camera.
     * @return The height of the camera.
     */
    public static int getCamHeight(int camWidth) {
        // cam.height / cam.width = width / height
        // cam.width = (width * cam.height) / height
        int camHeight = (Gdx.graphics.getHeight() * camWidth) / Gdx.graphics.getWidth();
        return camHeight;
    }

    public static void resizeStatic(int width, int height) {
        stillCam.viewportWidth = width;
        stillCam.viewportHeight = height;
        if ((float) WIDTH / HEIGHT < (float) width / height) {
            camera.viewportHeight = HEIGHT * cameraZoom;
            camera.viewportWidth = getCamWidth(HEIGHT) * cameraZoom;
        } else {
            camera.viewportHeight = getCamHeight(WIDTH) * cameraZoom;
            camera.viewportWidth = WIDTH * cameraZoom;
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        resizeStatic(width, height);
    }

    public static void setTheScreen(Screen screen) {
        ((Game) Gdx.app.getApplicationListener()).setScreen(screen);

    }

    public static float getX(Camera camera) {
        return camera.position.x + (Gdx.input.getX() - Gdx.graphics.getWidth() / 2) * (camera.viewportWidth / Gdx.graphics.getWidth());
    }

    public static float getY(Camera camera) {
        return camera.position.y + (Gdx.graphics.getHeight() - Gdx.input.getY() - Gdx.graphics.getHeight() / 2)
                * (camera.viewportHeight / Gdx.graphics.getHeight());
    }
}