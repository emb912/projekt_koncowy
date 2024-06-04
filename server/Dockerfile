# obrazu bazowy JRE
FROM eclipse-temurin:17-jre-focal

# ustawienie katalogu roboczego
WORKDIR /app

#kKopiowanie plików z danymi źródłowymi
COPY src/main/resources/data ./data

# skopiowanie pliku JAR (skompilowanego kodu servera) do obrazu
COPY target/server-0.0.1-SNAPSHOT.jar app.jar

# otwarcie portu 8080
EXPOSE 8080

# uruchomienie aplikacji
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
