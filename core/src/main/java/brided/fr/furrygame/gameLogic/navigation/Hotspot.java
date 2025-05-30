package brided.fr.furrygame.gameLogic.navigation;

import brided.fr.furrygame.gameLogic.actions.Action;
import com.badlogic.gdx.math.Rectangle;

public class Hotspot {
    public Rectangle bounds;
    public Action action;

    public Hotspot(float x, float y, float width, float height, Action action) {
        this.bounds = new Rectangle(x, y, width, height);
        this.action = action;
    }
}
