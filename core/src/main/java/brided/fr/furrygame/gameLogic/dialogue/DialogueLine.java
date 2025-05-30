package brided.fr.furrygame.gameLogic.dialogue;

import java.util.List;

public class DialogueLine {
    public String speaker;
    public String text;
    public List<String> choices;

    public DialogueLine(String speaker, String text, List<String> choices) {
        this.speaker = speaker;
        this.text = text;
        this.choices = choices;
    }
}
