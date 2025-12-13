# PlayManager - Configuration de Développement

Ce projet Spring Boot utilise une configuration VS Code complète pour un développement optimal avec Java, Thymeleaf, HTML, CSS et JavaScript.

## 🚀 Extensions VS Code Recommandées

Installez ces extensions pour une expérience de développement optimale :

### Extensions Java & Spring Boot
- **Extension Pack for Java** (`vscjava.vscode-java-pack`) - Pack complet pour le développement Java
- **Spring Boot Extension Pack** (`vmware.vscode-spring-boot`) - Support Spring Boot
- **Spring Boot Tools** (`pivotal.vscode-boot-dev-pack`) - Outils Spring Boot
- **Lombok Annotations Support** (`gabrielbb.vscode-lombok`) - Support Lombok

### Extensions Frontend
- **Thymeleaf** (`thymeleaf.thymeleaf-vscode`) - Support Thymeleaf
- **HTML CSS Support** (`ecmel.vscode-html-css`) - IntelliSense HTML/CSS
- **CSS Peek** (`pranaygp.vscode-css-peek`) - Navigation CSS
- **Auto Rename Tag** (`formulahendry.auto-rename-tag`) - Renommage automatique des balises
- **Path Intellisense** (`christian-kohler.path-intellisense`) - Auto-complétion des chemins

### Extensions Qualité du Code
- **SonarLint** (`sonarsource.sonarlint-vscode`) - Analyse statique du code
- **Checkstyle** (`shengchen.vscode-checkstyle`) - Vérification du style Java
- **ESLint** (`ms-vscode.vscode-eslint`) - Linting JavaScript
- **Prettier** (`esbenp.prettier-vscode`) - Formatage du code

### Extensions Outils
- **Maven for Java** (`ms-vscode.vscode-maven`) - Support Maven
- **YAML** (`redhat.vscode-yaml`) - Support YAML
- **XML** (`ms-vscode.vscode-xml`) - Support XML
- **GitLens** (`eamodio.gitlens`) - Fonctionnalités Git avancées

## ⚙️ Configuration VS Code

### Paramètres Automatiques
Les paramètres suivants sont configurés automatiquement dans `.vscode/settings.json` :

- **Formatage automatique** lors de la sauvegarde
- **Organisation automatique** des imports Java
- **Validation** HTML, CSS, JavaScript
- **Support Thymeleaf** activé
- **JDK 17** configuré comme runtime par défaut
- **Formatage Google Style** pour Java

### Configurations de Debug
Plusieurs configurations de debug sont disponibles dans `.vscode/launch.json` :

1. **Debug (Launch) - PlayManagerApplication** : Lance l'application en mode debug
2. **Debug (Attach) - Remote** : Attache à un processus Java distant
3. **Debug JavaScript (Chrome/Edge)** : Debug du frontend
4. **Debug Maven** : Lance via Maven avec debug activé

### Tâches Automatisées
Des tâches VS Code sont configurées dans `.vscode/tasks.json` :

- `compile` : Compilation Maven
- `test` : Exécution des tests
- `package` : Création du JAR
- `run` : Lancement de l'application
- `debug` : Lancement en mode debug
- `clean` : Nettoyage du projet

## 🛠️ Utilisation

### Démarrage Rapide
1. Ouvrez le projet dans VS Code
2. Installez les extensions recommandées (elles apparaîtront automatiquement)
3. Utilisez `Ctrl+Shift+P` → `Tasks: Run Task` → `run` pour lancer l'application
4. Ou utilisez `F5` pour lancer en mode debug

### Debug de l'Application
1. Placez des points d'arrêt dans le code Java
2. Appuyez sur `F5` ou utilisez la configuration "Debug (Launch) - PlayManagerApplication"
3. L'application se lance avec le debugger attaché

### Debug du Frontend
1. Lancez l'application normalement
2. Ouvrez `http://localhost:8080` dans Chrome/Edge
3. Utilisez `F5` avec la configuration "Debug JavaScript (Chrome)" ou "Debug JavaScript (Edge)"
4. Placez des points d'arrêt dans les fichiers JavaScript

### Compilation et Tests
- **Compiler** : `Ctrl+Shift+P` → `Tasks: Run Task` → `compile`
- **Tester** : `Ctrl+Shift+P` → `Tasks: Run Task` → `test`
- **Package** : `Ctrl+Shift+P` → `Tasks: Run Task` → `package`

## 📁 Structure du Projet

```
PlayManager/
├── .vscode/                    # Configuration VS Code
│   ├── settings.json          # Paramètres VS Code
│   ├── launch.json            # Configurations de debug
│   ├── tasks.json             # Tâches automatisées
│   └── java-formatter.xml     # Configuration formatage Java
├── src/
│   ├── main/
│   │   ├── java/              # Code Java Spring Boot
│   │   └── resources/
│   │       ├── static/        # CSS, JS, images
│   │       └── templates/     # Templates Thymeleaf
│   └── test/                  # Tests unitaires
├── target/                    # Artefacts de build (généré)
├── pom.xml                    # Configuration Maven
└── mvnw/mvnw.cmd             # Wrapper Maven
```

## 🔧 Dépannage

### Problèmes Courants

**Erreur "JAVA_HOME not found"**
- Vérifiez que JDK 17 est installé
- Modifiez le chemin dans `.vscode/settings.json` si nécessaire

**Extensions non détectées**
- Redémarrez VS Code
- Vérifiez que les extensions sont installées

**Debug ne fonctionne pas**
- Assurez-vous que le port 5005 n'est pas utilisé
- Vérifiez les configurations de lancement

**Thymeleaf non reconnu**
- Installez l'extension Thymeleaf
- Redémarrez VS Code

### Logs et Debugging
- Les logs Spring Boot apparaissent dans le terminal intégré
- Utilisez les points d'arrêt pour déboguer le code Java
- Les erreurs de compilation s'affichent dans l'onglet "Problems"

## 📚 Ressources

- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [Guide Thymeleaf](https://www.thymeleaf.org/documentation.html)
- [VS Code Java](https://code.visualstudio.com/docs/languages/java)
- [Debugging in VS Code](https://code.visualstudio.com/docs/editor/debugging)

---

*Configuration créée pour optimiser le développement Java Spring Boot avec Thymeleaf, HTML, CSS et JavaScript.*