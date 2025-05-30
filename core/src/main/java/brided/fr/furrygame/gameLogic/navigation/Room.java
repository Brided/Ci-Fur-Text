package brided.fr.furrygame.gameLogic.navigation;

import brided.fr.furrygame.gameLogic.actions.Action;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Room {
    public String roomName;
    public Texture background;
    public Array<Hotspot> hotspots;

    public Room(String roomName, String backgroundPath) {
        Texture background = new Texture(backgroundPath);

        this.roomName = roomName;
        this.background = background;

        this.hotspots = new Array<>();
    }

    public void addHotspot(float x, float y, float width, float height, String text) {
        Action action = new Action("text: " + text, text);

        Hotspot added = new Hotspot(x, y, width, height, action);
        hotspots.add(added);
    }

    public void addHotspot(float x, float y, float width, float height, Room room) {
        Action action = new Action("go_room: " + room.roomName, room);

        Hotspot added = new Hotspot(x, y, width, height, action);
        hotspots.add(added);
    }

    public void dispose() {
        background.dispose();
    }
}

