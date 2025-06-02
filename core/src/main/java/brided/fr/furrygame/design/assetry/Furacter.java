package brided.fr.furrygame.design.assetry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class Furacter implements Disposable {
    private final String name;

    private final Texture facingLeft;
    private final Texture facingRight;
    private final Texture facingUp;
    private final Texture facingDown;

    private float x;
    private float y;

    private Direction currentDirection;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

    public Furacter(String name, float startX, float startY, Texture left, Texture right, Texture up, Texture down) {
        this.name = name;
        this.x = startX;
        this.y = startY;
        this.facingLeft = left;
        this.facingRight = right;
        this.facingUp = up;
        this.facingDown = down;
        this.currentDirection = Direction.DOWN; // default
    }

    // Movement
    public void move(Direction dir, float distance) {
        this.currentDirection = dir;
        switch (dir) {
            case LEFT -> x -= distance;
            case RIGHT -> x += distance;
            case UP -> y += distance;
            case DOWN -> y -= distance;
        }
    }

    // Draw
    public void render(SpriteBatch batch) {
        Texture currentTexture = switch (currentDirection) {
            case LEFT -> facingLeft;
            case RIGHT -> facingRight;
            case UP -> facingUp;
            case DOWN -> facingDown;
        };
        batch.draw(currentTexture, x, y);
    }

    // Getters
    public String getName() { return name; }
    public float getX() { return x; }
    public float getY() { return y; }

    @Override
    public void dispose() {
        facingLeft.dispose();
        facingRight.dispose();
        facingUp.dispose();
        facingDown.dispose();
    }
}
