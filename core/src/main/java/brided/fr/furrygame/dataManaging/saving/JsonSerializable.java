package brided.fr.furrygame.dataManaging.saving;

public interface JsonSerializable {
    String toJson();
    void saveJsonToFile();
}
