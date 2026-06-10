package at.school.smartstudyplanner.service;

import at.school.smartstudyplanner.model.Priority;
import at.school.smartstudyplanner.model.StudyTask;
import at.school.smartstudyplanner.model.TaskStatus;
import at.school.smartstudyplanner.storage.TaskStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltet alle Lernaufgaben der Anwendung.
 * Diese Klasse enthält die zentrale Logik zum Hinzufügen,
 * Bearbeiten, Löschen, Filtern und Speichern von Aufgaben.
 */
public class TaskManager {

    /**
     * Liste aller Aufgaben.
     */
    private final List<StudyTask> tasks;

    /**
     * Speicherklasse für Dateioperationen.
     */
    private final TaskStorage storage;

    /**
     * Erstellt einen neuen TaskManager.
     *
     * @param storage Speicherklasse zum Laden und Speichern.
     */
    public TaskManager(TaskStorage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();
    }

    /**
     * Fügt eine neue Aufgabe hinzu.
     *
     * @param task Neue Lernaufgabe.
     */
    public void addTask(StudyTask task) {
        tasks.add(task);
        saveTasks();
    }

    /**
     * Löscht eine Aufgabe.
     *
     * @param task Aufgabe, die gelöscht werden soll.
     */
    public void deleteTask(StudyTask task) {
        tasks.remove(task);
        saveTasks();
    }

    /**
     * Markiert eine Aufgabe als erledigt.
     *
     * @param task Aufgabe, die erledigt wurde.
     */
    public void markTaskAsDone(StudyTask task) {
        task.setStatus(TaskStatus.DONE);
        saveTasks();
    }

    /**
     * Speichert Änderungen an einer Aufgabe.
     */
    public void updateTasks() {
        saveTasks();
    }

    /**
     * Gibt alle Aufgaben zurück.
     *
     * @return Kopie der Aufgabenliste.
     */
    public List<StudyTask> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Filtert Aufgaben nach Suchtext und Filtertyp.
     *
     * @param searchText Suchtext für Fach oder Titel.
     * @param filter Filtertyp: ALL, OPEN, DONE oder URGENT.
     * @return Gefilterte Aufgabenliste.
     */
    public List<StudyTask> getFilteredTasks(String searchText, String filter) {
        List<StudyTask> result = new ArrayList<>();

        for (StudyTask task : tasks) {
            boolean matchesSearch = task.getSubject().toLowerCase().contains(searchText.toLowerCase())
                    || task.getTitle().toLowerCase().contains(searchText.toLowerCase());

            boolean matchesFilter = switch (filter) {
                case "OPEN" -> task.getStatus() == TaskStatus.OPEN;
                case "DONE" -> task.getStatus() == TaskStatus.DONE;
                case "URGENT" -> task.isUrgent();
                default -> true;
            };

            if (matchesSearch && matchesFilter) {
                result.add(task);
            }
        }

        return result;
    }

    /**
     * Gibt die Anzahl aller Aufgaben zurück.
     *
     * @return Gesamtanzahl der Aufgaben.
     */
    public int getTotalCount() {
        return tasks.size();
    }

    /**
     * Gibt die Anzahl offener Aufgaben zurück.
     *
     * @return Anzahl offener Aufgaben.
     */
    public int getOpenCount() {
        int count = 0;

        for (StudyTask task : tasks) {
            if (task.getStatus() == TaskStatus.OPEN) {
                count++;
            }
        }

        return count;
    }

    /**
     * Gibt die Anzahl erledigter Aufgaben zurück.
     *
     * @return Anzahl erledigter Aufgaben.
     */
    public int getDoneCount() {
        int count = 0;

        for (StudyTask task : tasks) {
            if (task.getStatus() == TaskStatus.DONE) {
                count++;
            }
        }

        return count;
    }

    /**
     * Gibt die Anzahl dringender Aufgaben zurück.
     *
     * @return Anzahl dringender Aufgaben.
     */
    public int getUrgentCount() {
        int count = 0;

        for (StudyTask task : tasks) {
            if (task.isUrgent()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Speichert alle Aufgaben in einer Datei.
     */
    public void saveTasks() {
        storage.save(tasks);
    }

    /**
     * Lädt alle Aufgaben aus einer Datei.
     */
    public void loadTasks() {
        tasks.clear();
        tasks.addAll(storage.load());
    }

    /**
     * Erstellt Demo-Aufgaben zum Testen.
     */
    public void createDemoTasks() {
        addTask(new StudyTask("Mathematik", "Matrizen wiederholen", LocalDate.now().plusDays(2), Priority.HIGH, TaskStatus.OPEN));
        addTask(new StudyTask("Deutsch", "Präsentation vorbereiten", LocalDate.now().plusDays(5), Priority.MEDIUM, TaskStatus.OPEN));
        addTask(new StudyTask("Java", "Javadoc schreiben", LocalDate.now().plusDays(1), Priority.HIGH, TaskStatus.OPEN));
    }
}