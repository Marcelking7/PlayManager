# PlayManager 🎧 🎬

[![Spring Boot](https://shields.io)](https://spring.io)
[![Java](https://shields.io)](https://oracle.com)
[![License](https://shields.io)](LICENSE)

**PlayManager** est une application web full-stack moderne permettant de centraliser, gérer et organiser vos playlists et liens multimédias par catégories. 

Grâce à son architecture robuste et son système d'analyse intelligent, l'application détecte automatiquement la plateforme d'origine des liens que vous ajoutez.

---

## ✨ Fonctionnalités principales

*   📂 **Gestion des catégories :** Créez, consultez et supprimez des catégories thématiques personnalisées (ex: *Musique, Relax, Formations, Podcast*).
*   🔗 **Gestion des liens :** Ajoutez et organisez vos liens multimédias en toute simplicité.
*   🤖 **Détection intelligente :** Analyse automatique des URL pour identifier la plateforme source (*YouTube, Spotify, Facebook, SoundCloud, Deezer* ou autre).
*   🎨 **Interface moderne :** UI responsive, soignée et fluide basée sur Thymeleaf, des variables CSS modernes et des modales interactives.
*   💾 **Persistance locale :** Sauvegarde transparente des données dans une base embarquée.

---

## 🛠️ Stack technique & Architecture

### Backend & Persistance
*   **Java 17** & **Spring Boot 3.2.0** (Spring Web, Spring Data JPA)
*   **Base de données :** H2 Database (Mode fichier local embarqué)
*   **Lombok :** Suppression du code boilerplate (Entités, DTOs, Services)

### Frontend
*   **Moteur de template :** Thymeleaf (Rendu côté serveur)
*   **Design :** HTML5, CSS3 personnalisé (Grille responsive), JavaScript Vanilla

### Architecture du code
Le projet applique rigoureusement les patrons de conception standards avec une stricte séparation des couches :
`Entities` ➡️ `Repositories` ➡️ `Services` ➡️ `Mappers & DTOs` ➡️ `Controllers`

---

## 🚀 Installation et Démarrage

### Prérequis
*   **JDK 17** ou version supérieure installée.
*   **Maven** (Optionnel, le wrapper `mvnw` est inclus dans le projet).

### Étapes de configuration

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/Marcelking7/PlayManager.git
   cd PlayManager
   ```

2. **Lancer l'application :**
   *   *Sur Linux / macOS :*
       ```bash
       ./mvnw spring-boot:run
       ```
   *   *Sur Windows (Invite de commandes) :*
       ```cmd
       mvnw.cmd spring-boot:run
       ```

3. **Accéder à l'interface :**
   Ouvrez votre navigateur et rendez-vous sur [http://localhost:8080](http://localhost:8080)

---

## 🗄️ Accès à la Console H2

Pour inspecter ou manipuler directement les données en cours de développement, vous pouvez utiliser la console H2 intégrée :

*   **URL de la console :** `http://localhost:8080/h2-console`
*   **JDBC URL :** `jdbc:h2:file:./data/playlistdb`
*   **Nom d'utilisateur / Mot de passe :** *(Consulter le fichier `application.properties` si requis)*

> ⚠️ **Note :** La base de données est configurée en mode fichier local (`./data/playlistdb`). Les données sont donc conservées même après l'arrêt du serveur.

---

## 🤝 Contributions

Les contributions, signalements de bugs et suggestions d'améliorations sont les bienvenus ! 
1. Forkez le projet.
2. Créez votre branche de fonctionnalité (`git checkout -b feature/AmazingFeature`).
3. Commitez vos changements (`git commit -m 'Add some AmazingFeature'`).
4. Pushez votre branche (`git push origin feature/AmazingFeature`).
5. Ouvrez une **Pull Request**.

