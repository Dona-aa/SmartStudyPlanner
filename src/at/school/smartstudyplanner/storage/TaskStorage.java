package at.school.smartstudyplanner.storage;

import at.school.smartstudyplanner.model.StudyTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Zuständig für das Speichern und Laden von Lernaufgaben.
 * Die Aufgaben werden in einer einfachen Textdatei gespeichert.
 */
public class TaskStorage {

    /**
     * Dateiname der Speicherdatei.
     */
    private final String fileName;

    /**
     * Erstellt ein neues Speicherobjekt.
     *
     * @param fileName Name der Datei, in der die Aufgaben gespeichert werden.
     */
    public TaskStorage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Speichert eine Liste von Aufgaben in einer Datei.
     *
     * @param tasks Aufgaben, die gespeichert werden sollen.
     */
    public void save(List<StudyTask> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (StudyTask task : tasks) {
                writer.write(task.toFileLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    /**
     * Lädt alle Aufgaben aus einer Datei.
     *
     * @return Liste der geladenen Aufgaben.
     */
    public List<StudyTask> load() {
        List<StudyTask> tasks = new ArrayList<>();
        File file = new File(fileName);

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                StudyTask task = StudyTask.fromFileLine(line);

                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Laden: " + e.getMessage());
        }

        return tasks;
    }
}