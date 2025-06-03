package brided.fr.furrygame.mains;

import brided.fr.furrygame.design.assetry.Tile;
import brided.fr.furrygame.design.assetry.TileSet;
import brided.fr.furrygame.design.worldBuild.Room;
import brided.fr.furrygame.design.worldBuild.TileMap;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EditingMain extends ApplicationAdapter {
    private Stage stage;

    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Viewport ui;
    private OrthographicCamera camera;

    private Room room;
    private TileSet worldTileSet;

    private TileMap editingGrid;
    private TileSet gridTileSet;
    private Tile singleTile;

    private int width = 100;
    private int height = 100;

    InputMultiplexer inputMultiplexer;

    private TextButton button;

    @Override
    public void create() {
        Gdx.graphics.setTitle("CyFur Effect");

        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        ui = new ScreenViewport();
        stage = new Stage(ui);

        camera.position.set(0, 0, 0);

        gridTileSet = new TileSet("textures/tileSheets/singleTile.png", "single_tile");
        gridTileSet.load();
        singleTile = gridTileSet.getAt(0);

        button = new TextButton("Click me", new Skin(Gdx.files.internal("uiskin.json")));
        button.setPosition(10, 10);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button clicked!");
            }
        });
        stage.addActor(button);

        worldTileSet = new TileSet("textures/tileSheets/tileSheetTest.png", "testing");
        worldTileSet.load();

        editingGrid = TileMap.createGridMap(singleTile, width, height);
        room = new Room("editing_room", worldTileSet, width, height);

        System.out.println("room\n" + room.toJson());

        setInput();
    }

    public void setInput() {
        inputMultiplexer = new InputMultiplexer();

        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new InputAdapter() {
            private int lastX, lastY;
            private boolean rightDown;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                rightDown = false;

                if (button == 0) {
                    Vector3 worldPos = camera.unproject(new Vector3(screenX, screenY, 0));
                    if (worldPos.x < 0) {
                        worldPos.x -= Tile.TILE_SIZE;
                    }
                    if (worldPos.y < 0) {
                        worldPos.y -= Tile.TILE_SIZE;
                    }

                    int cellX = (int)(worldPos.x / Tile.TILE_SIZE);
                    int cellY = (int)(worldPos.y / Tile.TILE_SIZE);

                    room.getBackground().setTile(worldTileSet.getAt(3), cellX, cellY);
                    return true;
                } else if (button == 1) {
                    lastX = screenX;
                    lastY = screenY;
                    rightDown = true;
                    return true;
                }

                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                // amountY is usually the vertical scroll direction
                zoom(amountY);
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (!rightDown) {
                    return false;
                }

                float deltaX = (screenX - lastX) * camera.zoom;
                float deltaY = (screenY - lastY) * camera.zoom;

                camera.translate(-deltaX, deltaY);

                lastX = screenX;
                lastY = screenY;
                return true;
            }
        });

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void zoom(float amountY) {
        camera.zoom += amountY * 0.1f;

        camera.zoom = MathUtils.clamp(camera.zoom, 0.5f, 4f);
    }

    @Override
    public void render() {
        draw();
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.3f, 1f);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        room.render(spriteBatch);
        editingGrid.render(spriteBatch);

        spriteBatch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        ui.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        singleTile.dispose();
    }
}
