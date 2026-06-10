package at.school.smartstudyplanner.ui;

import at.school.smartstudyplanner.model.Priority;
import at.school.smartstudyplanner.model.StudyTask;
import at.school.smartstudyplanner.model.TaskStatus;
import at.school.smartstudyplanner.service.TaskManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

/**
 * Hauptfenster der Anwendung.
 * Diese Klasse erstellt die grafische Oberfläche und verbindet
 * die Buttons mit der Programmlogik.
 */
public class MainFrame extends JFrame {

    /**
     * Verwaltet die Aufgaben und enthält die Logik.
     */
    private final TaskManager manager;

    /**
     * Tabellenmodell für die Aufgabenanzeige.
     */
    private final TaskTableModel tableModel;

    /**
     * Tabelle für die Aufgaben.
     */
    private final JTable taskTable;

    /**
     * Eingabefeld für Suchtext.
     */
    private final JTextField searchField;

    /**
     * Auswahlfeld für Filter.
     */
    private final JComboBox<String> filterBox;

    /**
     * Label für Statistikdaten.
     */
    private final JLabel statsLabel;

    /**
     * Erstellt das Hauptfenster.
     *
     * @param manager TaskManager mit Programmlogik.
     */
    public MainFrame(TaskManager manager) {
        this.manager = manager;
        this.tableModel = new TaskTableModel();
        this.taskTable = new JTable(tableModel);
        this.searchField = new JTextField();
        this.filterBox = new JComboBox<>(new String[]{"ALL", "OPEN", "DONE", "URGENT"});
        this.statsLabel = new JLabel();

        setupFrame();
        setupLayout();
        refreshTable();
    }

    /**
     * Legt Grundeinstellungen des Fensters fest.
     */
    private void setupFrame() {
        setTitle("Smart Study Planner");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Baut die grafische Oberfläche auf.
     */
    private void setupLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Smart Study Planner - Aufgabenübersicht");
        titleLabel.setFont(titleLabel.getFont().deriveFont(22f));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(taskTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 8, 8));

        JPanel searchPanel = new JPanel(new BorderLayout(8, 8));
        searchPanel.add(new JLabel("Suche:"), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(filterBox, BorderLayout.EAST);

        JButton searchButton = new JButton("Aktualisieren");
        searchPanel.add(searchButton, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Aufgabe hinzufügen");
        JButton editButton = new JButton("Aufgabe bearbeiten");
        JButton deleteButton = new JButton("Aufgabe löschen");
        JButton doneButton = new JButton("Als erledigt markieren");
        JButton demoButton = new JButton("Demo-Daten");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(doneButton);
        buttonPanel.add(demoButton);

        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.add(statsLabel, BorderLayout.CENTER);

        controlPanel.add(searchPanel);
        controlPanel.add(buttonPanel);
        controlPanel.add(statsPanel);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);

        searchButton.addActionListener(e -> refreshTable());
        filterBox.addActionListener(e -> refreshTable());
        addButton.addActionListener(e -> addTask());
        editButton.addActionListener(e -> editSelectedTask());
        deleteButton.addActionListener(e -> deleteSelectedTask());
        doneButton.addActionListener(e -> markSelectedTaskDone());
        demoButton.addActionListener(e -> {
            manager.createDemoTasks();
            refreshTable();
        });
    }

    /**
     * Aktualisiert die Tabelle und die Statistik.
     */
    private void refreshTable() {
        String searchText = searchField.getText();
        String filter = String.valueOf(filterBox.getSelectedItem());

        tableModel.setTasks(manager.getFilteredTasks(searchText, filter));
        updateStats();
    }

    /**
     * Aktualisiert die Statistik im unteren Bereich.
     */
    private void updateStats() {
        statsLabel.setText(
                "Gesamt: " + manager.getTotalCount()
                        + " | Offen: " + manager.getOpenCount()
                        + " | Erledigt: " + manager.getDoneCount()
                        + " | Dringend: " + manager.getUrgentCount()
        );
    }

    /**
     * Öffnet einen Dialog zum Hinzufügen einer neuen Aufgabe.
     */
    private void addTask() {
        StudyTask task = showTaskDialog(null);

        if (task != null) {
            manager.addTask(task);
            refreshTable();
        }
    }

    /**
     * Bearbeitet die aktuell ausgewählte Aufgabe.
     */
    private void editSelectedTask() {
        int row = taskTable.getSelectedRow();

        if (row == -1) {
            showMessage("Bitte zuerst eine Aufgabe auswählen.");
            return;
        }

        StudyTask selectedTask = tableModel.getTaskAt(row);
        StudyTask editedTask = showTaskDialog(selectedTask);

        if (editedTask != null) {
            selectedTask.setSubject(editedTask.getSubject());
            selectedTask.setTitle(editedTask.getTitle());
            selectedTask.setDueDate(editedTask.getDueDate());
            selectedTask.setPriority(editedTask.getPriority());
            selectedTask.setStatus(editedTask.getStatus());

            manager.updateTasks();
            refreshTable();
        }
    }

    /**
     * Löscht die aktuell ausgewählte Aufgabe.
     */
    private void deleteSelectedTask() {
        int row = taskTable.getSelectedRow();

        if (row == -1) {
            showMessage("Bitte zuerst eine Aufgabe auswählen.");
            return;
        }

        StudyTask selectedTask = tableModel.getTaskAt(row);
        int result = JOptionPane.showConfirmDialog(
                this,
                "Soll diese Aufgabe wirklich gelöscht werden?",
                "Löschen bestätigen",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            manager.deleteTask(selectedTask);
            refreshTable();
        }
    }

    /**
     * Markiert die aktuell ausgewählte Aufgabe als erledigt.
     */
    private void markSelectedTaskDone() {
        int row = taskTable.getSelectedRow();

        if (row == -1) {
            showMessage("Bitte zuerst eine Aufgabe auswählen.");
            return;
        }

        StudyTask selectedTask = tableModel.getTaskAt(row);
        manager.markTaskAsDone(selectedTask);
        refreshTable();
    }

    /**
     * Zeigt einen Eingabedialog für eine Aufgabe.
     *
     * @param existingTask Bestehende Aufgabe oder null bei neuer Aufgabe.
     * @return Neue oder bearbeitete Aufgabe.
     */
    private StudyTask showTaskDialog(StudyTask existingTask) {
        JTextField subjectField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField dueDateField = new JTextField();
        JComboBox<Priority> priorityBox = new JComboBox<>(Priority.values());
        JComboBox<TaskStatus> statusBox = new JComboBox<>(TaskStatus.values());

        if (existingTask != null) {
            subjectField.setText(existingTask.getSubject());
            titleField.setText(existingTask.getTitle());
            dueDateField.setText(existingTask.getDueDate().toString());
            priorityBox.setSelectedItem(existingTask.getPriority());
            statusBox.setSelectedItem(existingTask.getStatus());
        } else {
            dueDateField.setText(LocalDate.now().plusDays(7).toString());
        }

        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.add(new JLabel("Fach:"));
        panel.add(subjectField);
        panel.add(new JLabel("Titel:"));
        panel.add(titleField);
        panel.add(new JLabel("Deadline YYYY-MM-DD:"));
        panel.add(dueDateField);
        panel.add(new JLabel("Priorität:"));
        panel.add(priorityBox);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                existingTask == null ? "Neue Aufgabe" : "Aufgabe bearbeiten",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return null;
        }

        try {
            String subject = subjectField.getText().trim();
            String title = titleField.getText().trim();
            LocalDate dueDate = LocalDate.parse(dueDateField.getText().trim());
            Priority priority = (Priority) priorityBox.getSelectedItem();
            TaskStatus status = (TaskStatus) statusBox.getSelectedItem();

            if (subject.isEmpty() || title.isEmpty()) {
                showMessage("Fach und Titel dürfen nicht leer sein.");
                return null;
            }

            return new StudyTask(subject, title, dueDate, priority, status);
        } catch (Exception e) {
            showMessage("Ungültige Eingabe. Bitte Datum im Format YYYY-MM-DD eingeben.");
            return null;
        }
    }

    /**
     * Zeigt eine einfache Meldung an.
     *
     * @param message Text der Meldung.
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}