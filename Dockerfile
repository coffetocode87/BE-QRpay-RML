# Gunakan base image dengan JDK 17
FROM openjdk:17-jdk-slim

# Tambahkan jar hasil build ke container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Port yang digunakan Spring Boot
EXPOSE 8080

# Jalankan aplikasi
ENTRYPOINT ["java","-jar","/app.jar"]
