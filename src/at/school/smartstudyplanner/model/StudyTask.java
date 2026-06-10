package at.school.smartstudyplanner.model;

import java.time.LocalDate;

/**
 * Modellklasse für eine Lernaufgabe.
 * Eine Lernaufgabe besitzt ein Fach, einen Titel, eine Deadline,
 * eine Priorität und einen Status.
 */
public class StudyTask {

    /**
     * Schulfach der Aufgabe.
     */
    private String subject;

    /**
     * Titel oder kurze Beschreibung der Aufgabe.
     */
    private String title;

    /**
     * Datum, bis wann die Aufgabe erledigt werden soll.
     */
    private LocalDate dueDate;

    /**
     * Priorität der Aufgabe.
     */
    private Priority priority;

    /**
     * Aktueller Status der Aufgabe.
     */
    private TaskStatus status;

    /**
     * Erstellt eine neue Lernaufgabe.
     *
     * @param subject Fach der Aufgabe.
     * @param title Titel der Aufgabe.
     * @param dueDate Deadline der Aufgabe.
     * @param priority Priorität der Aufgabe.
     * @param status Status der Aufgabe.
     */
    public StudyTask(String subject, String title, LocalDate dueDate, Priority priority, TaskStatus status) {
        this.subject = subject;
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    /**
     * Prüft, ob eine Aufgabe dringend ist.
     * Eine Aufgabe gilt als dringend, wenn sie offen ist und innerhalb
     * der nächsten drei Tage fällig wird.
     *
     * @return true, wenn die Aufgabe dringend ist, sonst false.
     */
    public boolean isUrgent() {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(3);
        return status == TaskStatus.OPEN && !dueDate.isAfter(limit);
    }

    /**
     * Wandelt die Aufgabe in eine speicherbare Textzeile um.
     *
     * @return Textzeile für die Datei.
     */
    public String toFileLine() {
        return subject + ";" + title + ";" + dueDate + ";" + priority + ";" + status;
    }

    /**
     * Erstellt eine Lernaufgabe aus einer gespeicherten Textzeile.
     *
     * @param line Textzeile aus der Datei.
     * @return Lernaufgabe oder null, falls die Zeile ungültig ist.
     */
    public static StudyTask fromFileLine(String line) {
        String[] parts = line.split(";");

        if (parts.length != 5) {
            return null;
        }

        try {
            String subject = parts[0];
            String title = parts[1];
            LocalDate dueDate = LocalDate.parse(parts[2]);
            Priority priority = Priority.valueOf(parts[3]);
            TaskStatus status = TaskStatus.valueOf(parts[4]);

            return new StudyTask(subject, title, dueDate, priority, status);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Gibt das Fach der Aufgabe zurück.
     *
     * @return Fach.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setzt das Fach der Aufgabe.
     *
     * @param subject Neues Fach.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Gibt den Titel der Aufgabe zurück.
     *
     * @return Titel.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setzt den Titel der Aufgabe.
     *
     * @param title Neuer Titel.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gibt die Deadline der Aufgabe zurück.
     *
     * @return Deadline.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Setzt die Deadline der Aufgabe.
     *
     * @param dueDate Neue Deadline.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gibt die Priorität der Aufgabe zurück.
     *
     * @return Priorität.
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Setzt die Priorität der Aufgabe.
     *
     * @param priority Neue Priorität.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Gibt den Status der Aufgabe zurück.
     *
     * @return Status.
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Setzt den Status der Aufgabe.
     *
     * @param status Neuer Status.
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}