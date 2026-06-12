# Smart Study Planner

Smart Study Planner ist ein Java-Desktopprogramm mit grafischer Benutzeroberfläche.
Das Programm hilft Schülern dabei, Lernaufgaben, Hausübungen, Tests und Projekte übersichtlich zu planen.

## Projektbeschreibung

Mit Smart Study Planner können Lernaufgaben gespeichert und verwaltet werden.
Jede Aufgabe besitzt ein Fach, einen Titel, eine Deadline, eine Priorität und einen Status.

Die Aufgaben werden in einer Tabelle angezeigt. Zusätzlich kann man Aufgaben suchen, filtern,
bearbeiten, löschen und als erledigt markieren.

Das Programm erkennt außerdem dringende Aufgaben automatisch.
Eine Aufgabe gilt als dringend, wenn sie offen ist und innerhalb der nächsten drei Tage fällig wird.

## Funktionen

- Aufgaben hinzufügen
- Aufgaben bearbeiten
- Aufgaben löschen
- Aufgaben als erledigt markieren
- Aufgaben nach Status filtern
- Aufgaben suchen
- Dringende Aufgaben erkennen
- Aufgaben mit hoher Priorität zählen
- Statistik anzeigen
- Aufgaben lokal in einer Datei speichern und laden
- Grafische Oberfläche mit Java Swing

## Technologien

- Java
- Java Swing
- IntelliJ IDEA
- Git
- GitHub
- PlantUML
- Javadoc
- Lokale Datei-Speicherung

## Projektstruktur

```text
SmartStudyPlanner/
├── README.md
├── requirements-design.md
├── uml/
│   └── SmartStudyPlanner.puml
└── src/
    └── at/
        └── school/
            └── smartstudyplanner/
                ├── Main.java
                ├── model/
                │   ├── Priority.java
                │   ├── StudyTask.java
                │   └── TaskStatus.java
                ├── service/
                │   └── TaskManager.java
                ├── storage/
                │   └── TaskStorage.java
                └── ui/
                    ├── MainFrame.java
                    └── TaskTableModel.java
