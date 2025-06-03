package brided.fr.furrygame.mains;

import brided.fr.furrygame.design.assetry.Tile;
import brided.fr.furrygame.design.assetry.TileSet;
import brided.fr.furrygame.design.worldBuild.Room;
import brided.fr.furrygame.design.worldBuild.TileMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EditingMain extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Room room;
    private TileMap editingGrid;
    private TileSet gridTileSet;
    private Tile singleTile;

    private int width = 20;
    private int height = 20;

    @Override
    public void create() {
        Gdx.graphics.setTitle("CyFur Effect");

        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        camera.position.set(0,0,0);

        gridTileSet = new TileSet("textures/tileSheets/singleTile.png", "single_tile");
        gridTileSet.load();

        singleTile = gridTileSet.getAt(0);

        editingGrid = TileMap.createGridMap(singleTile, width, height);
        room = new Room("editing_room", gridTileSet, width, height);

        System.out.println("room\n" + room.toJson());

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (button != 0) {
                    return false;
                }

                Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 0));

                int cellX = (int)(worldPos.x / Tile.TILE_SIZE);
                int cellY = (int)(worldPos.y / Tile.TILE_SIZE);

                System.out.println("Clicked at: " + cellX + ", " + cellY);
                return true;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                // amountY is usually the vertical scroll direction
                zoom(amountY);
                return true;
            }
        });
    }

    private void zoom(float amountY) {
        camera.zoom += amountY * 0.1f;

        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 4f);
    }

    @Override
    public void render() {
        input();
        draw();
    }

    private void input() {
        if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            float deltaX = -Gdx.input.getDeltaX() * camera.zoom;
            float deltaY = Gdx.input.getDeltaY() * camera.zoom;
            camera.translate(deltaX, deltaY);
        }
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.3f, 1f);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        editingGrid.render(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        singleTile.dispose();
    }
}
