package brided.fr.furrygame.gameLogic.actions;

import brided.fr.furrygame.Main;
import brided.fr.furrygame.gameLogic.navigation.Room;

public class Action {
    public String name;
    public ActionType actionType;
    public String text;
    public Room targetRoom;

    public Action(String name, String text) {
        this.name = name;
        this.actionType = ActionType.TEXT;
        this.text = text;
    }

    public Action(String name, Room room) {
        this.name = name;
        this.actionType = ActionType.GO_ROOM;
        this.targetRoom = room;
    }

    public void execute(Main main) {
        switch (actionType) {
            case TEXT:
                System.out.println(text);
                break;
            case GO_ROOM:
                main.setCurrentRoom(targetRoom);
        }
    }

    @Override
    public String toString() {
        return name + " " + text;
    }

}
