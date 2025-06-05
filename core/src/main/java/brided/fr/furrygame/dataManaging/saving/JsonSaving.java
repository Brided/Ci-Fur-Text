package brided.fr.furrygame.dataManaging.saving;

import com.badlogic.gdx.utils.Json;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonSaving<S extends JsonSerializable> {
    private final S savedObject;

    public JsonSaving(S savedObject) {
        this.savedObject = savedObject;
    }

    public void saveWithDialog() {
        String json = this.savedObject.toJson();

        // Set up the file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save JSON File");

        // 🥄 Add file filter to show only .json files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Files (*.json)", "json");
        fileChooser.setFileFilter(filter);

        // Show save dialog and capture result
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write(json);
                System.out.println("Saved to: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not save file!");
            }
        } else {
            System.out.println("Save canceled.");
        }
    }
}
