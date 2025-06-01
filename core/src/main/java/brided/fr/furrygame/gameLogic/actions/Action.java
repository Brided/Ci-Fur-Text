package brided.fr.furrygame.gameLogic.actions;

import brided.fr.furrygame.Main;
import brided.fr.furrygame.gameLogic.navigation.Room;

public class Action {
    private final String name;
    private final ActionType actionType;
    private String text;

    public Action(String name, String text) {
        this.name = name;
        this.actionType = ActionType.TEXT;
        this.text = text;
    }

    public Action(String name, Room room) {
        this.name = name;
        this.actionType = ActionType.GO_ROOM;
    }

    public void execute(Main main) {
        switch (actionType) {
            case TEXT:
                System.out.println(text);
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return name + " " + text;
    }

}
