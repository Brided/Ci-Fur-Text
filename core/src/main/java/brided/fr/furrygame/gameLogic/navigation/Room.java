package brided.fr.furrygame.gameLogic.navigation;

import com.badlogic.gdx.graphics.Texture;

public class Room {
    public String roomName;
    public Texture background;

    public Room(String roomName, String backgroundPath) {
        Texture background = new Texture(backgroundPath);

        this.roomName = roomName;
        this.background = background;
    }

    public void dispose() {
        background.dispose();
    }
}

