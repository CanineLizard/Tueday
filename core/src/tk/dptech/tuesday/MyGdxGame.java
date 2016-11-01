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


public class MyGdxGame extends Game {

	public static final int WIDTH = 8, HEIGHT = 8;
	public static float cameraZoom = 1f;
	public static OrthographicCamera camera;
	public static OrthographicCamera stillCam;
	private SpriteBatch batch;
	private BitmapFont font;

	public static boolean showJoystick = false;

	public static boolean pretty = true;

	public static Vector2 touchPoint = new Vector2(0, 0);

	public static Texture touchTex;

	// These are for the engine
	public static final int lagTime = 0;

	@Override
	public void create() {
		Gdx.input.setCatchBackKey(true);
		touchTex = new Texture("touch_point.png");
		touchTex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera(getCamWidth(HEIGHT), HEIGHT);
		stillCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//setScreen(new TitleScreen());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_LINEAR);

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

	public static void setTheScreen(Screen screen){
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