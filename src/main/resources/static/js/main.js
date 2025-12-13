/**
 * main.js
 * Ce fichier gère l'interactivité de base : ouverture/fermeture des modales
 * et gestion des actions de suppression via l'envoi de requêtes POST.
 */

// ==========================================================
// 1. GESTION DES MODALES
// ==========================================================

/**
 * Ouvre ou ferme une modale spécifique.
 * @param {string} id L'ID de la modale à basculer (ex: 'addCategoryModal').
 */
function toggleModal(id) {
    const modal = document.getElementById(id);
    if (modal) {
        modal.classList.toggle('is-active');

        // Optionnel : Focus sur le premier champ de formulaire lors de l'ouverture
        if (modal.classList.contains('is-active')) {
            const firstInput = modal.querySelector('input, textarea');
            if (firstInput) {
                firstInput.focus();
            }
        }
    }
}


// ==========================================================
// 2. GESTION DES ACTIONS POST (Suppression)
// ==========================================================

/**
 * Supprime une catégorie en envoyant une requête POST au contrôleur.
 * (Utilisée dans index.html)
 * * @param {number} id L'ID de la catégorie à supprimer.
 */
function deleteCategory(id) {
    if (confirm("Êtes-vous sûr de vouloir supprimer cette catégorie et tous ses liens ? Cette action est irréversible.")) {

        // Crée un formulaire caché pour envoyer une requête POST
        const form = document.createElement('form');
        form.method = 'POST';
        // Route : /category/{id}/delete
        form.action = `/category/${id}/delete`;

        // Assurez-vous d'avoir un token CSRF si vous utilisez Spring Security
        // (Non inclus ici car non visible dans le pom.xml, mais bonne pratique)

        document.body.appendChild(form);
        form.submit();
    }
}

/**
 * Supprime un lien en envoyant une requête POST au contrôleur.
 * (Utilisée dans category.html)
 * * @param {number} id L'ID du lien à supprimer.
 * @param {number} categoryId L'ID de la catégorie parente pour la redirection.
 */
function deleteLink(id, categoryId) {
    if (confirm("Êtes-vous sûr de vouloir supprimer ce lien ?")) {

        // Crée un formulaire caché pour envoyer une requête POST
        const form = document.createElement('form');
        form.method = 'POST';
        // Route : /link/{id}/delete
        form.action = `/link/${id}/delete`;

        // Ajoute categoryId comme paramètre requis par le MainController
        const inputCategory = document.createElement('input');
        inputCategory.type = 'hidden';
        inputCategory.name = 'categoryId';
        inputCategory.value = categoryId;

        form.appendChild(inputCategory);
        document.body.appendChild(form);
        form.submit();
    }
}

// ==========================================================
// 3. INITIALISATION (Fermer la modale en appuyant sur ESC)
// ==========================================================

document.addEventListener('keydown', function (e) {
    // Vérifie si la touche pressée est 'Escape'
    if (e.key === 'Escape') {
        // Trouve toutes les modales actives
        const activeModals = document.querySelectorAll('.modal.is-active');

        // Ferme la dernière modale ouverte
        if (activeModals.length > 0) {
            activeModals[activeModals.length - 1].classList.remove('is-active');
        }
    }
});