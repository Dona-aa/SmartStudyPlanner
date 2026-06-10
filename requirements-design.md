# Requirements Design - Smart Study Planner

## Ziele

### Ziel 1: Aufgabenverwaltung

Das Programm soll Lernaufgaben verwalten können.
Der Benutzer kann Aufgaben hinzufügen, bearbeiten und löschen.

### Ziel 2: Grafische Oberfläche

Das Programm soll eine übersichtliche grafische Oberfläche mit Java Swing besitzen.
Die wichtigsten Funktionen sollen über Buttons erreichbar sein.

### Ziel 3: Aufgabenübersicht

Alle Aufgaben sollen in einer Tabelle angezeigt werden.
Der Benutzer sieht Fach, Titel, Deadline, Priorität, Status und ob die Aufgabe dringend ist.

### Ziel 4: Such- und Filterfunktion

Der Benutzer soll Aufgaben nach Fach oder Titel suchen können.
Außerdem soll er Aufgaben nach Status filtern können.

### Ziel 5: Dringende Aufgaben erkennen

Das Programm soll erkennen, ob eine Aufgabe dringend ist.
Eine Aufgabe gilt als dringend, wenn sie offen ist und innerhalb von drei Tagen fällig wird.

### Ziel 6: Speicherung

Die Aufgaben sollen lokal in einer Datei gespeichert werden.
Beim Neustart des Programms sollen die gespeicherten Aufgaben wieder geladen werden.

### Ziel 7: Statistik

Das Programm soll eine kleine Statistik anzeigen.
Angezeigt werden Gesamtanzahl, offene Aufgaben, erledigte Aufgaben und dringende Aufgaben.

### Ziel 8: Projektorganisation mit Git

Das Projekt soll mit Git organisiert werden.
Es gibt einen Master-Branch, einen Devil-Branch und eigene Feature-Branches für die Teammitglieder.

## Nicht-Ziele

### Nicht-Ziel 1: Keine Online-Synchronisation

Das Programm synchronisiert keine Daten über das Internet.

### Nicht-Ziel 2: Keine Benutzerkonten

Es gibt kein Login-System und keine verschiedenen Benutzer.

### Nicht-Ziel 3: Keine Datenbank

Die Aufgaben werden nicht in einer Datenbank gespeichert, sondern in einer lokalen Datei.

### Nicht-Ziel 4: Keine mobile App

Das Programm ist nur eine Java-Desktopanwendung und keine Android- oder iOS-App.

### Nicht-Ziel 5: Keine automatische Kalenderintegration

Das Programm verbindet sich nicht automatisch mit Google Calendar, Outlook oder anderen Kalenderdiensten.

### Nicht-Ziel 6: Kein Mehrbenutzer-System

Das Programm ist nicht für mehrere Benutzer gleichzeitig gedacht.