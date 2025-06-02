package brided.fr.furrygame.mains;

import brided.fr.furrygame.design.assetry.Furacter;
import brided.fr.furrygame.design.assetry.Tile;
import brided.fr.furrygame.design.assetry.TileSet;
import brided.fr.furrygame.design.worldBuild.TileMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayingMain extends ApplicationAdapter {
    private SpriteBatch spriteBatch;
    private Viewport viewport;
    private Camera camera;

    private Furacter bull;

    private TileSet tileSet;
    private Tile tile;
    private Tile tile2;

    private TileMap tileMap;

    private float delta;

    @Override
    public void create() {
        Gdx.graphics.setTitle("CyFur Effect");

        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ScreenViewport(camera);

        bull = new Furacter(
            "Teagan", 0,0,
            new Texture("textures/characterSprites/rpgBullFacing_Left.png"),
            new Texture("textures/characterSprites/rpgBullFacing_Right.png"),
            new Texture("textures/characterSprites/rpgBullFacing_Up.png"),
            new Texture("textures/characterSprites/rpgBullFacing_Down.png")
        );

        tileSet = new TileSet("textures/tileSheets/tileSheetTest.png");
        tileSet.addTile("tile_0_2", "grass");
        tileSet.addTile("tile_0_0", "wood");

        tileMap = new TileMap(10, 10);
        tileMap.setTile(tile,0,0);
        tileMap.setTile(tile,1,5);
        tileMap.setTile(tile2,0,1);
        tileMap.setTile(tile2,2,3);
        tileMap.setTile(tile2,4,3);

        Json json = new Json();

        String text = json.toJson(tileMap, TileMap.class);
        System.out.println("Tile map\n" + json.prettyPrint(text));

        text = json.toJson(tileSet, TileSet.class);
        System.out.println("Tile set\n" +json.prettyPrint(text));
    }

    @Override
    public void render() {
        delta = Gdx.graphics.getDeltaTime();

        input();
        draw();
    }

    private void input() {
        float speed = 200f;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            bull.move(Furacter.Direction.LEFT, delta * speed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            bull.move(Furacter.Direction.RIGHT, delta * speed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            bull.move(Furacter.Direction.UP, delta * speed);
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            bull.move(Furacter.Direction.DOWN, delta * speed);
        }
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.3f, 1f);
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        camera.update();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        tileMap.render(spriteBatch);
        bull.render(spriteBatch);

        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        bull.dispose();
    }
}
