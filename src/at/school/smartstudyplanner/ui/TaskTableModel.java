package at.school.smartstudyplanner.ui;

import at.school.smartstudyplanner.model.StudyTask;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Tabellenmodell für die Anzeige der Lernaufgaben in einer JTable.
 */
public class TaskTableModel extends AbstractTableModel {

    /**
     * Spaltennamen der Tabelle.
     */
    private final String[] columns = {"Fach", "Titel", "Deadline", "Priorität", "Status", "Dringend"};

    /**
     * Aktuell angezeigte Aufgaben.
     */
    private List<StudyTask> tasks;

    /**
     * Erstellt ein neues Tabellenmodell.
     */
    public TaskTableModel() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Setzt die Aufgaben, die in der Tabelle angezeigt werden sollen.
     *
     * @param tasks Neue Aufgabenliste.
     */
    public void setTasks(List<StudyTask> tasks) {
        this.tasks = tasks;
        fireTableDataChanged();
    }

    /**
     * Gibt eine Aufgabe anhand der Tabellenzeile zurück.
     *
     * @param row Zeilennummer.
     * @return Aufgabe in der Zeile.
     */
    public StudyTask getTaskAt(int row) {
        return tasks.get(row);
    }

    /**
     * Gibt die Anzahl der Zeilen zurück.
     *
     * @return Anzahl der Aufgaben.
     */
    @Override
    public int getRowCount() {
        return tasks.size();
    }

    /**
     * Gibt die Anzahl der Spalten zurück.
     *
     * @return Anzahl der Spalten.
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * Gibt den Namen einer Spalte zurück.
     *
     * @param column Spaltenindex.
     * @return Name der Spalte.
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    /**
     * Gibt den Wert einer Tabellenzelle zurück.
     *
     * @param rowIndex Zeilenindex.
     * @param columnIndex Spaltenindex.
     * @return Wert der Tabellenzelle.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudyTask task = tasks.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> task.getSubject();
            case 1 -> task.getTitle();
            case 2 -> task.getDueDate();
            case 3 -> task.getPriority();
            case 4 -> task.getStatus();
            case 5 -> task.isUrgent() ? "Ja" : "Nein";
            default -> "";
        };
    }
}