# Image-Gallery_223-Backend

Herzlich Willkommen zum Backend unseres üK Projektes, Image-Gallery. Folgen Sie die unteren Schritte um das Backend aufzusetzen und nutzen zu können.

### Schritt 1
Um das Projekt nutzen zu können müssen sie zu erst das Git Repository clonen.

### Schritt 2
Zu Beginn müssen Sie ihre Postgres Datenbank mit diesem Command erstellen:
### Command
```
docker run --name gallery_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```
### Schritt 3
Öffnen Sie Ihre IDE und dort den Ordner des Backend. Klicken Sie auf den Button Gradle, welcher sich auf der rechten Seitenleiste befindet. Öffnen Sie die Ordner in der Leiste in dieser Reihenfolge: demo -> Tasks -> application -> bootRun. Klicken Sie bootRun um das Programm starten zu können.
### Bild zur Hilfe
![image](https://github.com/user-attachments/assets/dbe8165b-e74b-4fbb-8045-acc4dac0c5c1)

### Schritt 4
Um die Applikation vollständig nutzen zu können, starten Sie Ihr Frontend mithilfe der Anleitung. [Hier](https://github.com/MaximilianNoethe/Image-Gallery_223-Frontend/tree/main) finden Sie die Anleitung für das Frontend.

Ausserdem starten Sie den Docker damit die Verbinung zur Datenbank besteht.

### Schritt 5
Um sich anzumelden benutzen Sie einer dieser User:

Admin: admin@example.com

PW: 1234


User: user@example.com

PW: 1234

### Postman Testing
Das Testen des Backends haben wir mit einer Postman Collection getestet. 

Links zu den Postman Files:
[Link](https://github.com/FabianoM07/Image-Gallery_223-Backend/tree/main/nypag-spring_backend-5b58b4e2560c/src/test/resources/postman)

### Schritt 1
Laden Sie die Postman Files herunter und importieren Sie die Files in Postman.

### Schritt 2
Als nächstes müssen Sie die richtigen enviroment variables hinzufügen:

![image](https://github.com/user-attachments/assets/004dabf0-2f38-4676-a5e1-d49f696bd463)


### Schritt 3
Jetzt sind Sie bereit um das Backend in Postman zu testen. Wichtig ist immer das Login durchzuführen und den Bearer Token zu den enviroment variables neu hinzufügen.

Ausserdem ist zu beachten, dass Sie nach dem Sie eine komplette Test Colletion getestet haben, dass Sie das Backend neustarten um die nächste Test Colletion zu testen. Wenn Sie das nicht machen kann es zu Problemen führen.



