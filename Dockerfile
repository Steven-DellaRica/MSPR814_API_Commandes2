# Utiliser une image de base JDK
FROM openjdk:17
WORKDIR /app

# Copier le fichier JAR généré
COPY target/*.jar app.jar

# Exposer le port de l'API
EXPOSE 8083

# Démarrer l'application
CMD ["java", "-jar", "app.jar"]