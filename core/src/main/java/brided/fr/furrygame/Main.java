package brided.fr.furrygame;

import brided.fr.furrygame.gameLogic.navigation.Hotspot;
import brided.fr.furrygame.gameLogic.navigation.Room;
import brided.fr.furrygame.gameLogic.navigation.RoomsLayout;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private FitViewport viewport;

    private Room currentRoom;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(640, 480);

        currentRoom = RoomsLayout.room1;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    @Override
    public void render() {
        draw();
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        batch.draw(currentRoom.background,0,0, worldWidth, worldHeight);

        if (Gdx.input.justTouched()) {
            Vector2 touch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            for (Hotspot hotspot : currentRoom.hotspots) {
                if (hotspot.bounds.contains(touch)) {
                    hotspot.action.execute(this);
                    break;
                }
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        currentRoom.dispose();
    }
}
