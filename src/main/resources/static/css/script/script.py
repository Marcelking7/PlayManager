import os
import shutil

# --- Configuration Ajustée ---
# BASE_DIR est le chemin vers le dossier 'css' qui contient tous les fichiers et le dossier 'script'.
# os.path.dirname(os.path.abspath(__file__)) est le dossier 'css/script'.
# os.path.join(..., '..') permet de remonter au dossier 'css'.
BASE_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), '..')

print(f"Le script s'attend à ce que le dossier CSS (BASE_DIR) soit : {BASE_DIR}\n")

# Nouvelle structure de dossiers et mapping des fichiers
NEW_STRUCTURE = {
    'base': [
        '_variables.css',
        '_reset.css',
        '_base.css',
        '_typography.css'
    ],
    'layout': [
        '_app-layout.css',
        '_header.css',
        '_main-content.css'
    ],
    'components': [
        '_buttons.css',
        '_cards.css',
        '_forms.css',
        '_modal.css',
        '_link-list.css',
        '_stats-bar.css'
    ],
    'utilities': [
        '_animations.css',
        '_empty-state.css',
        '_responsive.css'
    ]
}

# Mapping pour déplacer les anciens fichiers vers les nouveaux noms/emplacements
FILE_MAPPING = {
    # Fichiers de votre architecture précédente (à la racine de css) -> [Dossier cible, Nom du fichier cible]
    'variable.css': ['base', '_variables.css'],
    'reset.css': ['base', '_reset.css'],
    'layouts.css': ['layout', '_app-layout.css'],
    'buttons.css': ['components', '_buttons.css'],
    'cards.css': ['components', '_cards.css'],
    'links.list.css': ['components', '_link-list.css'],
    'empty.state.css': ['utilities', '_empty-state.css'],
    'modals.css': ['components', '_modal.css'],
    'animations.css': ['utilities', '_animations.css'],
    'responsive.css': ['utilities', '_responsive.css'],

    # Le fichier main.css initial sera renommé et déplacé pour l'étape de découpage manuel
    'main.css': ['.', 'main_original.css'],

    # Fichier principal qui devient l'entry point (à créer/garder à la racine)
    'style.css': ['.', 'style.css'],
}


def setup_css_architecture():
    """
    Crée la nouvelle architecture de dossiers et déplace/renomme les fichiers existants.
    """
    # 1. Création de la structure des dossiers
    print("Étape 1 : Création de la nouvelle structure de dossiers...")
    for folder in NEW_STRUCTURE.keys():
        folder_path = os.path.join(BASE_DIR, folder)
        if not os.path.exists(folder_path):
            os.makedirs(folder_path)
            print(f"  - Dossier créé : {folder}")
        else:
            print(f"  - Dossier existe déjà : {folder}")

    # 2. Déplacement et renommage des fichiers existants
    print("\nÉtape 2 : Déplacement et renommage des fichiers existants...")
    for old_name, (new_folder, new_name) in FILE_MAPPING.items():
        old_path = os.path.join(BASE_DIR, old_name)
        new_path = os.path.join(BASE_DIR, new_folder, new_name)

        if os.path.exists(old_path):
            try:
                # Créer le chemin de destination au cas où (si new_folder est '.')
                os.makedirs(os.path.dirname(new_path), exist_ok=True)
                shutil.move(old_path, new_path)
                print(f"  - Déplacé : '{old_name}' -> '{new_folder}/{new_name}'")
            except Exception as e:
                print(f"  - ERREUR de déplacement pour '{old_name}': {e}")
        else:
            # Ne pas afficher si c'est un des fichiers attendus qui n'est pas encore créé
            if old_name not in ['_typography.css', '_forms.css', '_stats-bar.css', '_responsive.css', 'style.css']:
                print(f"  - Fichier non trouvé : '{old_name}'. (Sera créé à l'étape 3)")

    # 3. Création des fichiers vides manquants et du style.css principal
    print("\nÉtape 3 : Création des fichiers manquants...")

    # Fichier style.css principal (entry point)
    style_path = os.path.join(BASE_DIR, 'style.css')
    if not os.path.exists(style_path):
        with open(style_path, 'w') as f:
            f.write("/* Fichier principal pour l'importation de tous les modules CSS */\n")
            f.write("/* Importez les modules dans l'ordre: variables, reset, base, layout, components, utilities */\n")
            f.write("/* -------------------------------------------------------------------------- */\n")
            f.write("@import url(\"./base/_variables.css\");\n")
            f.write("@import url(\"./base/_reset.css\");\n")
            f.write("@import url(\"./base/_base.css\");\n")
            f.write("@import url(\"./base/_typography.css\");\n")
            f.write("\n")
            f.write("@import url(\"./layout/_app-layout.css\");\n")
            f.write("@import url(\"./layout/_header.css\");\n")
            f.write("@import url(\"./layout/_main-content.css\");\n")
            f.write("\n")
            f.write("@import url(\"./components/_buttons.css\");\n")
            f.write("@import url(\"./components/_cards.css\");\n")
            f.write("@import url(\"./components/_forms.css\");\n")
            f.write("@import url(\"./components/_modal.css\");\n")
            f.write("@import url(\"./components/_link-list.css\");\n")
            f.write("@import url(\"./components/_stats-bar.css\");\n")
            f.write("\n")
            f.write("@import url(\"./utilities/_animations.css\");\n")
            f.write("@import url(\"./utilities/_empty-state.css\");\n")
            f.write("@import url(\"./utilities/_responsive.css\");\n")

        print(f"  - Créé : style.css (avec les directives @import)")

    # Création des fichiers vides pour les modules non couverts par les anciens fichiers
    files_to_create = [
        ('base', '_typography.css'),
        ('components', '_forms.css'),
        ('components', '_stats-bar.css'), # Sera rempli depuis links.list.css
    ]

    for folder, file in files_to_create:
        file_path = os.path.join(BASE_DIR, folder, file)
        if not os.path.exists(file_path):
            with open(file_path, 'w') as f:
                f.write(f"/* Styles pour le module '{file}' dans le dossier '{folder}' */\n")
            print(f"  - Créé : {folder}/{file}")

    print("\n✅ Processus de création et déplacement terminé.")
    print("\n⚠️ **Prochaine étape CRUCIALE : Le Découpage du Contenu**")
    print("---------------------------------------------------------")
    print("1. Vous avez maintenant 'main_original.css' à la racine de 'css'.")
    print("2. Coupez et collez les blocs de styles de 'main_original.css' dans leurs nouveaux fichiers (ex: Variables dans 'base/_variables.css', Layout dans 'layout/').")
    print("3. Répétez l'opération pour les fichiers déplacés qui contiennent plusieurs modules (ex: 'links.list.css' contient stats-bar).")


if __name__ == "__main__":
    setup_css_architecture()