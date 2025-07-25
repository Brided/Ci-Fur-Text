package brided.fr.furrygame.design.worldBuild;

import brided.fr.furrygame.dataManaging.saving.JsonSaving;
import brided.fr.furrygame.dataManaging.saving.JsonSerializable;
import brided.fr.furrygame.design.assetry.Tile;
import brided.fr.furrygame.design.assetry.TileSet;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Room implements JsonSerializable {
    private final String roomName;
    private ArrayList<String> tileKeys;

    private final int width;
    private final int height;

    private TileMap background;
    private TileMap walls;
    private TileMap decorations;
    private TileMap hazards;

    public Room(String roomName, TileSet tileSet, int width, int height) {
        this.roomName = roomName;
        this.tileKeys = tileSet.getKeySet();

        this.width = width;
        this.height = height;

        this.background = new TileMap(width, height);
        this.walls = new TileMap(width, height);
        this.decorations = new TileMap(width, height);
        this.hazards = new TileMap(width, height);
    }

    public void render(SpriteBatch batch) {
        this.background.render(batch);
        this.walls.render(batch);
        this.decorations.render(batch);
        this.hazards.render(batch);
    }

    public int getWorldHeight() {
        return height * Tile.TILE_SIZE;
    }

    public int getWorldHWidth() {
        return width * Tile.TILE_SIZE;
    }

    public String toJson() {
        Json json = new Json();
        return json.prettyPrint(json.toJson(this, Room.class));
    }

    public void saveJsonToFile() {
        JsonSaving<Room> jsonSaving = new JsonSaving<>(this);
        jsonSaving.saveWithDialog();
    }

    public TileMap getBackground() { return background; }

    public TileMap getWalls() { return walls; }

    public TileMap getDecorations() { return decorations; }

    public TileMap getHazards() { return hazards; }
}
