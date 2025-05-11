# Vigie des Médias – Projet Java

Projet réalisé dans le cadre du module **Concepts Objet et Java**  
💻 1ère année du Cycle Ingénieur Informatique (ET3)  
🏫 Polytech Paris-Saclay – Année universitaire 2024–2025  
👨‍💻 Auteur : Paolo BRACQ

---

## 📂 Contenu du dépôt

Ce dépôt contient deux répertoires principaux :

- **`Projet_final/`** : code source complet du projet "Vigie des Médias"
- **`TPs/`** : l’ensemble des TP réalisés au cours du semestre (PDF + code)

---
## 🏗️ Architecture du projet

### Packages principaux

- `model/` : entités (`Entite`, `Personne`, `Media`, `GroupInd`), et relations (`Proprietaire`)
- `modules/` : modules spécialisés (`SuiviPersonne`, `SuiviMedia`) et interface `ModuleSpecialise`
- `Importer.java` : lecture des fichiers `.tsv` et création des objets
- `SimulationEvenement.java` : gestion des événements simulés (publications, rachats)
- `Console.java` : point d’entrée du programme, interface texte avec menus et actions

---

## ▶️ Exécution

### ✅ Recommandé : depuis un IDE (IntelliJ IDEA)

1. Ouvrir le projet dans IntelliJ
2. Vérifier que tous les fichiers `.tsv` sont dans le bon répertoire (`Projet_final`)
3. Lancer la classe `Console.java`
4. Suivre les instructions affichées dans la console

### ⚠️ Exécution en ligne de commande

- Le projet fonctionne en ligne de commande, **mais des problèmes d’affichage avec les caractères accentués** peuvent apparaître (notamment sous Windows).
- Aucun problème rencontré lors de l’exécution via **IntelliJ**.

---

## 📁 À propos des TPs

Le dossier `TPs/` contient :

- tous les **TPs réalisés en cours**
- en deux formats :
  - **PDF** : pour lecture rapide
  - **Code source** : pour exécution ou réutilisation
