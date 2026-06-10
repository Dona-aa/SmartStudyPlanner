package at.school.smartstudyplanner;

import at.school.smartstudyplanner.service.TaskManager;
import at.school.smartstudyplanner.storage.TaskStorage;
import at.school.smartstudyplanner.ui.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Startklasse der Anwendung Smart Study Planner.
 * Diese Klasse startet das Programm und öffnet das Hauptfenster.
 */
public class Main {

    /**
     * Einstiegspunkt des Programms.
     *
     * @param args Startargumente des Programms, werden hier nicht verwendet.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskStorage storage = new TaskStorage("tasks.txt");
            TaskManager manager = new TaskManager(storage);
            manager.loadTasks();

            MainFrame frame = new MainFrame(manager);
            frame.setVisible(true);
        });
    }
}