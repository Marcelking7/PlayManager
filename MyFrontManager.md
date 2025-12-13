// ============= src/main/resources/static/css/main.css =============
/* ===== CSS Variables ===== */
:root {
--color-primary: #6366f1;
--color-primary-dark: #4f46e5;
--color-primary-light: #818cf8;
--color-secondary: #64748b;
--color-secondary-dark: #475569;

    --color-success: #10b981;
    --color-danger: #ef4444;
    --color-warning: #f59e0b;
    
    --color-bg: #f8fafc;
    --color-surface: #ffffff;
    --color-text: #0f172a;
    --color-text-muted: #64748b;
    --color-border: #e2e8f0;
    
    --shadow-sm: 0 1px 2px 0 rgb(0 0 0 / 0.05);
    --shadow-md: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
    --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
    --shadow-xl: 0 20px 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1);
    
    --radius-sm: 0.375rem;
    --radius-md: 0.5rem;
    --radius-lg: 0.75rem;
    --radius-xl: 1rem;
    
    --transition-fast: 150ms cubic-bezier(0.4, 0, 0.2, 1);
    --transition-base: 200ms cubic-bezier(0.4, 0, 0.2, 1);
    --transition-slow: 300ms cubic-bezier(0.4, 0, 0.2, 1);
}

/* ===== Reset & Base ===== */
*, *::before, *::after {
margin: 0;
padding: 0;
box-sizing: border-box;
}

html {
font-size: 16px;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
}

body {
font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
background-color: var(--color-bg);
color: var(--color-text);
line-height: 1.5;
min-height: 100vh;
}

/* ===== Layout ===== */
.app-container {
min-height: 100vh;
display: flex;
flex-direction: column;
}

.app-header {
background: var(--color-surface);
border-bottom: 1px solid var(--color-border);
padding: 1.5rem 2rem;
box-shadow: var(--shadow-sm);
position: sticky;
top: 0;
z-index: 100;
}

.header-content {
max-width: 1400px;
margin: 0 auto;
display: flex;
align-items: center;
justify-content: space-between;
gap: 2rem;
}

.logo {
display: flex;
align-items: center;
gap: 0.75rem;
}

.logo-icon {
width: 2rem;
height: 2rem;
color: var(--color-primary);
stroke-width: 2;
}

.logo h1 {
font-size: 1.5rem;
font-weight: 700;
color: var(--color-text);
}

.header-title {
display: flex;
align-items: center;
gap: 1rem;
}

.category-icon-large {
width: 3rem;
height: 3rem;
background: linear-gradient(135deg, var(--color-primary-light), var(--color-primary));
border-radius: var(--radius-lg);
display: flex;
align-items: center;
justify-content: center;
color: white;
flex-shrink: 0;
}

.category-icon-large svg {
width: 1.75rem;
height: 1.75rem;
stroke-width: 2;
}

.subtitle {
color: var(--color-text-muted);
font-size: 0.875rem;
margin-top: 0.25rem;
}

.back-link {
display: inline-flex;
align-items: center;
gap: 0.5rem;
color: var(--color-text-muted);
text-decoration: none;
font-weight: 500;
transition: color var(--transition-fast);
padding: 0.5rem 1rem;
border-radius: var(--radius-md);
}

.back-link:hover {
color: var(--color-text);
background: var(--color-bg);
}

.back-link svg {
width: 1.25rem;
height: 1.25rem;
stroke-width: 2;
}

.main-content {
flex: 1;
padding: 2rem;
}

.content-wrapper {
max-width: 1400px;
margin: 0 auto;
}

/* ===== Buttons ===== */
.btn {
display: inline-flex;
align-items: center;
justify-content: center;
gap: 0.5rem;
padding: 0.625rem 1.25rem;
border: none;
border-radius: var(--radius-md);
font-weight: 500;
font-size: 0.875rem;
cursor: pointer;
transition: all var(--transition-fast);
text-decoration: none;
white-space: nowrap;
}

.btn-primary {
background: var(--color-primary);
color: white;
box-shadow: var(--shadow-sm);
}

.btn-primary:hover {
background: var(--color-primary-dark);
box-shadow: var(--shadow-md);
transform: translateY(-1px);
}

.btn-secondary {
background: var(--color-surface);
color: var(--color-text);
border: 1px solid var(--color-border);
}

.btn-secondary:hover {
background: var(--color-bg);
border-color: var(--color-secondary);
}

.btn-full {
width: 100%;
}

.btn-icon {
width: 1.25rem;
height: 1.25rem;
stroke-width: 2;
}

.btn-icon-right {
width: 1rem;
height: 1rem;
stroke-width: 2.5;
}

.btn-icon-only {
padding: 0.5rem;
background: transparent;
border: none;
color: var(--color-text-muted);
cursor: pointer;
border-radius: var(--radius-md);
transition: all var(--transition-fast);
display: inline-flex;
align-items: center;
justify-content: center;
}

.btn-icon-only svg {
width: 1.25rem;
height: 1.25rem;
stroke-width: 2;
}

.btn-icon-only:hover {
background: var(--color-bg);
color: var(--color-text);
}

.btn-delete:hover {
background: var(--color-danger);
color: white;
}

/* ===== Cards ===== */
.categories-grid {
display: grid;
grid-template-columns: repeat(auto-fill, minmax(320px, 1fr));
gap: 1.5rem;
}

.category-card {
background: var(--color-surface);
border-radius: var(--radius-xl);
box-shadow: var(--shadow-sm);
transition: all var(--transition-base);
border: 1px solid var(--color-border);
overflow: hidden;
display: flex;
flex-direction: column;
}

.category-card:hover {
box-shadow: var(--shadow-lg);
transform: translateY(-4px);
border-color: var(--color-primary-light);
}

.card-header {
padding: 1.5rem 1.5rem 0;
display: flex;
justify-content: space-between;
align-items: flex-start;
}

.card-icon {
width: 3rem;
height: 3rem;
background: linear-gradient(135deg, var(--color-primary-light), var(--color-primary));
border-radius: var(--radius-lg);
display: flex;
align-items: center;
justify-content: center;
color: white;
}

.card-icon svg {
width: 1.5rem;
height: 1.5rem;
stroke-width: 2;
}

.card-body {
padding: 1.25rem 1.5rem;
flex: 1;
}

.card-body h3 {
font-size: 1.125rem;
font-weight: 600;
color: var(--color-text);
margin-bottom: 0.5rem;
}

.card-description {
font-size: 0.875rem;
color: var(--color-text-muted);
line-height: 1.5;
margin-bottom: 1rem;
display: -webkit-box;
-webkit-line-clamp: 2;
-webkit-box-orient: vertical;
overflow: hidden;
}

.card-stats {
display: flex;
gap: 0.75rem;
flex-wrap: wrap;
}

.stat-badge {
display: inline-flex;
align-items: center;
gap: 0.375rem;
padding: 0.375rem 0.75rem;
background: var(--color-bg);
border-radius: var(--radius-md);
font-size: 0.8125rem;
color: var(--color-text-muted);
font-weight: 500;
}

.stat-badge svg {
width: 1rem;
height: 1rem;
stroke-width: 2;
}

.card-footer {
padding: 0 1.5rem 1.5rem;
}

/* ===== Links List ===== */
.stats-bar {
background: var(--color-surface);
border-radius: var(--radius-lg);
padding: 1.5rem;
margin-bottom: 1.5rem;
box-shadow: var(--shadow-sm);
border: 1px solid var(--color-border);
}

.stat-item {
display: flex;
align-items: center;
gap: 0.75rem;
}

.stat-item svg {
width: 2rem;
height: 2rem;
color: var(--color-primary);
stroke-width: 2;
}

.stat-value {
font-size: 2rem;
font-weight: 700;
color: var(--color-text);
}

.stat-label {
color: var(--color-text-muted);
font-size: 0.875rem;
}

.links-list {
display: flex;
flex-direction: column;
gap: 1rem;
}

.link-card {
background: var(--color-surface);
border-radius: var(--radius-lg);
padding: 1.5rem;
box-shadow: var(--shadow-sm);
border: 1px solid var(--color-border);
display: flex;
gap: 1.25rem;
align-items: flex-start;
transition: all var(--transition-base);
}

.link-card:hover {
box-shadow: var(--shadow-md);
border-color: var(--color-primary-light);
}

.link-platform {
display: flex;
flex-direction: column;
align-items: center;
gap: 0.5rem;
padding: 0.75rem;
background: var(--color-bg);
border-radius: var(--radius-md);
min-width: 5rem;
font-size: 0.75rem;
font-weight: 600;
text-align: center;
color: var(--color-text-muted);
}

.link-platform svg {
width: 1.75rem;
height: 1.75rem;
stroke-width: 2;
}

.platform-youtube {
background: #fee;
color: #c00;
}

.platform-spotify {
background: #efe;
color: #1db954;
}

.platform-facebook {
background: #eef;
color: #1877f2;
}

.link-content {
flex: 1;
min-width: 0;
}

.link-content h3 {
font-size: 1.125rem;
font-weight: 600;
color: var(--color-text);
margin-bottom: 0.5rem;
}

.link-url {
display: inline-flex;
align-items: center;
gap: 0.5rem;
color: var(--color-primary);
text-decoration: none;
font-size: 0.875rem;
word-break: break-all;
transition: color var(--transition-fast);
margin-bottom: 0.5rem;
}

.link-url:hover {
color: var(--color-primary-dark);
text-decoration: underline;
}

.link-url svg {
width: 1rem;
height: 1rem;
flex-shrink: 0;
stroke-width: 2;
}

.link-date {
display: block;
font-size: 0.75rem;
color: var(--color-text-muted);
margin-top: 0.5rem;
}

/* ===== Empty State ===== */
.empty-state {
text-align: center;
padding: 4rem 2rem;
}

.empty-icon {
width: 5rem;
height: 5rem;
margin: 0 auto 1.5rem;
color: var(--color-text-muted);
opacity: 0.3;
stroke-width: 1.5;
}

.empty-state h2 {
font-size: 1.5rem;
color: var(--color-text);
margin-bottom: 0.5rem;
}

.empty-state p {
color: var(--color-text-muted);
margin-bottom: 1.5rem;
}

/* ===== Modal ===== */
.modal {
position: fixed;
inset: 0;
z-index: 1000;
display: none;
align-items: center;
justify-content: center;
padding: 1rem;
}

.modal.is-active {
display: flex;
}

.modal-overlay {
position: absolute;
inset: 0;
background: rgba(0, 0, 0, 0.5);
backdrop-filter: blur(4px);
animation: fadeIn var(--transition-base);
}

.modal-content {
position: relative;
background: var(--color-surface);
border-radius: var(--radius-xl);
box-shadow: var(--shadow-xl);
max-width: 500px;
width: 100%;
max-height: 90vh;
overflow: auto;
animation: slideUp var(--transition-base);
}

.modal-header {
display: flex;
align-items: center;
justify-content: space-between;
padding: 1.5rem;
border-bottom: 1px solid var(--color-border);
}

.modal-header h2 {
font-size: 1.25rem;
font-weight: 600;
}

.modal-form {
padding: 1.5rem;
}

.form-group {
margin-bottom: 1.25rem;
}

.form-group:last-child {
margin-bottom: 0;
}

.form-group label {
display: block;
font-size: 0.875rem;
font-weight: 500;
color: var(--color-text);
margin-bottom: 0.5rem;
}

.form-input,
.form-textarea {
width: 100%;
padding: 0.625rem 0.875rem;
border: 1px solid var(--color-border);
border-radius: var(--radius-md);
font-size: 0.875rem;
transition: all var(--transition-fast);
font-family: inherit;
}

.form-input:focus,
.form-textarea:focus {
outline: none;
border-color: var(--color-primary);
box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
}

.form-textarea {
resize: vertical;
min-height: 5rem;
}

.form-hint {
display: block;
font-size: 0.75rem;
color: var(--color-text-muted);
margin-top: 0.375rem;
}

.modal-actions {
display: flex;
gap: 0.75rem;
padding: 1.5rem;
border-top: 1px solid var(--color-border);
justify-content: flex-end;
}

/* ===== Animations ===== */
@keyframes fadeIn {
from { opacity: 0; }
to { opacity: 1; }
}

@keyframes slideUp {
from {
opacity: 0;
transform: translateY(1rem);
}
to {
opacity: 1;
transform: translateY(0);
}
}

/* ===== Responsive ===== */
@media (max-width: 768px) {
.main-content {
padding: 1rem;
}

    .app-header {
        padding: 1rem;
    }

    .header-content {
        flex-direction: column;
        gap: 1rem;
    }

    .categories-grid {
        grid-template-columns: 1fr;
    }

    .link-card {
        flex-direction: column;
    }

    .link-platform {
        flex-direction: row;
        width: 100%;
    }

    .modal-actions {
        flex-direction: column;
    }

    .modal-actions .btn {
        width: 100%;
    }
}
package bj.csam.playlist.PlayManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaylistManagerApplication {
public static void main(String[] args) {
SpringApplication.run(PlaylistManagerApplication.class, args);
}
}

// ============= src/main/resources/templates/index.html =============
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionnaire de Playlists</title>
    <link rel="stylesheet" th:href="@{/css/main.css}">
</head>
<body>
    <div class="app-container">
        <!-- Header -->
        <header class="app-header">
            <div class="header-content">
                <div class="logo">
                    <svg class="logo-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                        <path d="M3 7V17C3 18.1046 3.89543 19 5 19H19C20.1046 19 21 18.1046 21 17V7M3 7L12 2L21 7M3 7L12 12M21 7L12 12M12 12V22"/>
                    </svg>
                    <h1>Playlist Manager</h1>
                </div>
                <button class="btn btn-primary" onclick="toggleModal('addCategoryModal')">
                    <svg class="btn-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                        <path d="M12 5v14M5 12h14"/>
                    </svg>
                    Nouvelle Catégorie
                </button>
            </div>
        </header>

        <!-- Main Content -->
        <main class="main-content">
            <div class="content-wrapper">
                <!-- Empty State -->
                <div th:if="${categories.empty}" class="empty-state">
                    <svg class="empty-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                        <path d="M3 7V17C3 18.1046 3.89543 19 5 19H19C20.1046 19 21 18.1046 21 17V7M3 7L12 2L21 7M3 7L12 12M21 7L12 12"/>
                    </svg>
                    <h2>Aucune catégorie</h2>
                    <p>Commencez par créer votre première catégorie pour organiser vos playlists</p>
                    <button class="btn btn-primary" onclick="toggleModal('addCategoryModal')">Créer une catégorie</button>
                </div>

                <!-- Categories Grid -->
                <div th:unless="${categories.empty}" class="categories-grid">
                    <article class="category-card" th:each="cat : ${categories}">
                        <div class="card-header">
                            <div class="card-icon">
                                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                    <path d="M3 7V17C3 18.1046 3.89543 19 5 19H19C20.1046 19 21 18.1046 21 17V7"/>
                                </svg>
                            </div>
                            <button class="btn-icon-only" th:onclick="'deleteCategory(' + ${cat.id} + ')'">
                                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                    <path d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
                                </svg>
                            </button>
                        </div>
                        <div class="card-body">
                            <h3 th:text="${cat.name}">Catégorie</h3>
                            <p class="card-description" th:text="${cat.description} ?: 'Aucune description'">Description</p>
                            <div class="card-stats">
                                <span class="stat-badge">
                                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                        <path d="M13.828 10.172a4 4 0 00-5.656 0l-4 4a4 4 0 105.656 5.656l1.102-1.101m-.758-4.899a4 4 0 005.656 0l4-4a4 4 0 00-5.656-5.656l-1.1 1.1"/>
                                    </svg>
                                    <span th:text="${cat.linksCount}">0</span>
                                    <span th:text="${cat.linksCount > 1} ? 'liens' : 'lien'">lien</span>
                                </span>
                            </div>
                        </div>
                        <div class="card-footer">
                            <a th:href="@{/category/{id}(id=${cat.id})}" class="btn btn-secondary btn-full">
                                Voir les liens
                                <svg class="btn-icon-right" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                    <path d="M9 5l7 7-7 7"/>
                                </svg>
                            </a>
                        </div>
                    </article>
                </div>
            </div>
        </main>

        <!-- Add Category Modal -->
        <div id="addCategoryModal" class="modal">
            <div class="modal-overlay" onclick="toggleModal('addCategoryModal')"></div>
            <div class="modal-content">
                <div class="modal-header">
                    <h2>Nouvelle Catégorie</h2>
                    <button class="btn-icon-only" onclick="toggleModal('addCategoryModal')">
                        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                            <path d="M6 18L18 6M6 6l12 12"/>
                        </svg>
                    </button>
                </div>
                <form th:action="@{/category/add}" method="post" class="modal-form">
                    <div class="form-group">
                        <label for="categoryName">Nom de la catégorie</label>
                        <input type="text" id="categoryName" name="name" class="form-input" placeholder="Ex: Musique relaxante" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="categoryDescription">Description</label>
                        <textarea id="categoryDescription" name="description" class="form-textarea" placeholder="Description optionnelle..." rows="3"></textarea>
                    </div>
                    <div class="modal-actions">
                        <button type="button" class="btn btn-secondary" onclick="toggleModal('addCategoryModal')">Annuler</button>
                        <button type="submit" class="btn btn-primary">Créer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script th:src="@{/js/main.js}"></script>
</body>
</html>

// ============= src/main/resources/templates/category.html =============
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title th:text="${category.name}">Catégorie</title>
    <link rel="stylesheet" th:href="@{/css/main.css}">
</head>
<body>
    <div class="app-container">
        <!-- Header -->
        <header class="app-header">
            <div class="header-content">
                <a href="/" class="back-link">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                        <path d="M15 19l-7-7 7-7"/>
                    </svg>
                    Retour
                </a>
                <div class="header-title">
                    <div class="category-icon-large">
                        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                            <path d="M3 7V17C3 18.1046 3.89543 19 5 19H19C20.1046 19 21 18.1046 21 17V7"/>
                        </svg>
                    </div>
                    <div>
                        <h1 th:text="${category.name}">Catégorie</h1>
                        <p class="subtitle" th:text="${category.description} ?: 'Aucune description'">Description</p>
                    </div>
                </div>
                <button class="btn btn-primary" onclick="toggleModal('addLinkModal')">
                    <svg class="btn-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                        <path d="M12 5v14M5 12h14"/>
                    </svg>
                    Ajouter un lien
                </button>
            </div>
        </header>

        <!-- Main Content -->
        <main class="main-content">
            <div class="content-wrapper">
                <!-- Stats Bar -->
                <div class="stats-bar">
                    <div class="stat-item">
                        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                            <path d="M13.828 10.172a4 4 0 00-5.656 0l-4 4a4 4 0 105.656 5.656l1.102-1.101m-.758-4.899a4 4 0 005.656 0l4-4a4 4 0 00-5.656-5.656l-1.1 1.1"/>
                        </svg>
                        <span class="stat-value" th:text="${links.size()}">0</span>
                        <span class="stat-label" th:text="${links.size() > 1} ? 'liens enregistrés' : 'lien enregistré'">liens</span>
                    </div>
                </div>

                <!-- Empty State -->
                <div th:if="${links.empty}" class="empty-state">
                    <svg class="empty-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                        <path d="M13.828 10.172a4 4 0 00-5.656 0l-4 4a4 4 0 105.656 5.656l1.102-1.101m-.758-4.899a4 4 0 005.656 0l4-4a4 4 0 00-5.656-5.656l-1.1 1.1"/>
                    </svg>
                    <h2>Aucun lien</h2>
                    <p>Ajoutez votre premier lien de playlist pour commencer</p>
                    <button class="btn btn-primary" onclick="toggleModal('addLinkModal')">Ajouter un lien</button>
                </div>

                <!-- Links List -->
                <div th:unless="${links.empty}" class="links-list">
                    <article class="link-card" th:each="link : ${links}">
                        <div class="link-platform" th:classappend="${'platform-' + #strings.toLowerCase(link.platform)}">
                            <svg th:if="${link.platform == 'YouTube'}" viewBox="0 0 24 24" fill="currentColor">
                                <path d="M23.498 6.186a3.016 3.016 0 0 0-2.122-2.136C19.505 3.545 12 3.545 12 3.545s-7.505 0-9.377.505A3.017 3.017 0 0 0 .502 6.186C0 8.07 0 12 0 12s0 3.93.502 5.814a3.016 3.016 0 0 0 2.122 2.136c1.871.505 9.376.505 9.376.505s7.505 0 9.377-.505a3.015 3.015 0 0 0 2.122-2.136C24 15.93 24 12 24 12s0-3.93-.502-5.814zM9.545 15.568V8.432L15.818 12l-6.273 3.568z"/>
                            </svg>
                            <svg th:if="${link.platform == 'Spotify'}" viewBox="0 0 24 24" fill="currentColor">
                                <path d="M12 0C5.4 0 0 5.4 0 12s5.4 12 12 12 12-5.4 12-12S18.66 0 12 0zm5.521 17.34c-.24.359-.66.48-1.021.24-2.82-1.74-6.36-2.101-10.561-1.141-.418.122-.779-.179-.899-.539-.12-.421.18-.78.54-.9 4.56-1.021 8.52-.6 11.64 1.32.42.18.479.659.301 1.02zm1.44-3.3c-.301.42-.841.6-1.262.3-3.239-1.98-8.159-2.58-11.939-1.38-.479.12-1.02-.12-1.14-.6-.12-.48.12-1.021.6-1.141C9.6 9.9 15 10.561 18.72 12.84c.361.181.54.78.241 1.2zm.12-3.36C15.24 8.4 8.82 8.16 5.16 9.301c-.6.179-1.2-.181-1.38-.721-.18-.601.18-1.2.72-1.381 4.26-1.26 11.28-1.02 15.721 1.621.539.3.719 1.02.419 1.56-.299.421-1.02.599-1.559.3z"/>
                            </svg>
                            <svg th:if="${link.platform != 'YouTube' and link.platform != 'Spotify'}" viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                <path d="M13.828 10.172a4 4 0 00-5.656 0l-4 4a4 4 0 105.656 5.656l1.102-1.101m-.758-4.899a4 4 0 005.656 0l4-4a4 4 0 00-5.656-5.656l-1.1 1.1"/>
                            </svg>
                            <span th:text="${link.platform}">Platform</span>
                        </div>
                        <div class="link-content">
                            <h3 th:text="${link.title}">Titre</h3>
                            <a th:href="${link.url}" target="_blank" rel="noopener" class="link-url">
                                <span th:text="${link.url}">URL</span>
                                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                    <path d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14"/>
                                </svg>
                            </a>
                            <span class="link-date" th:text="${#temporals.format(link.createdAt, 'dd/MM/yyyy à HH:mm')}">Date</span>
                        </div>
                        <button class="btn-icon-only btn-delete" th:onclick="'deleteLink(' + ${link.id} + ', ' + ${category.id} + ')'">
                            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                                <path d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"/>
                            </svg>
                        </button>
                    </article>
                </div>
            </div>
        </main>

        <!-- Add Link Modal -->
        <div id="addLinkModal" class="modal">
            <div class="modal-overlay" onclick="toggleModal('addLinkModal')"></div>
            <div class="modal-content">
                <div class="modal-header">
                    <h2>Ajouter un lien</h2>
                    <button class="btn-icon-only" onclick="toggleModal('addLinkModal')">
                        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor">
                            <path d="M6 18L18 6M6 6l12 12"/>
                        </svg>
                    </button>
                </div>
                <form th:action="@{/link/add}" method="post" class="modal-form">
                    <input type="hidden" name="categoryId" th:value="${category.id}">
                    <div class="form-group">
                        <label for="linkTitle">Titre de la playlist</label>
                        <input type="text" id="linkTitle" name="title" class="form-input" placeholder="Ex: Mes meilleures chansons 2024" required autofocus>
                    </div>
                    <div class="form-group">
                        <label for="linkUrl">URL de la playlist</label>
                        <input type="url" id="linkUrl" name="url" class="form-input" placeholder="https://youtube.com/playlist?list=..." required>
                        <small class="form-hint">Formats supportés : YouTube, Spotify, Facebook, SoundCloud, Deezer</small>
                    </div>
                    <div class="modal-actions">
                        <button type="button" class="btn btn-secondary" onclick="toggleModal('addLinkModal')">Annuler</button>
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script th:src="@{/js/main.js}"></script>
    <script th:inline="javascript">
        const categoryId = /*[[${category.id}]]*/ 0;
    </script>
</body>
</html>