# ⚽ Tunisian Soccer Players Stats Platform

> 🧠 **Projet complet pour la centralisation, la visualisation et la mise à jour automatisée des données des joueurs et équipes du championnat tunisien de football.**

---

## 🎯 Objectif du projet

Le but de cette plateforme est de constituer **une base de données centralisée et actualisée** contenant toutes les informations sur :
- les **joueurs tunisiens** (locaux et internationaux),
- les **équipes du championnat tunisien (Ligue 1 & Ligue 2)**,
- leurs **performances, transferts, compétitions et statistiques match par match.**

Ces données sont exploitées par une **application web Angular intégrée à Spring Boot** et peuvent être **automatiquement mises à jour** via un batch Python/Spring AI.

---

## 🧩 Architecture technique

### 🔹 Technologies principales
| Côté | Stack |
|------|--------|
| **Backend** | Spring Boot 3, Spring Data JPA, Spring Scheduler, Spring AI |
| **Frontend** | Angular 17 (embarqué dans le JAR Spring Boot) |
| **Base de données** | H2 (chargée à partir de CSVs au démarrage) |
| **Batch / Scraping** | Python 3 (Requests, BeautifulSoup, Pandas), ou Spring Boot Batch |
| **Communication inter-apps** | REST API + Apache Kafka (optionnel) |
| **Tests / CI** | JUnit 5, GitLab CI/CD |

---

## 🏗️ Architecture logicielle

### 🔸 Composants principaux
1. **Web Application (`soccer-players-stats`)**
   - Sert les données joueurs/équipes depuis H2.
   - Affiche graphiquement les statistiques et classements.
   - Peut recevoir des mises à jour via REST ou Kafka.

2. **Batch d’actualisation (`data-updater-batch`)**
   - Récupère et met à jour les données depuis différentes sources :
     - [Football-Data.org](https://www.football-data.org/)
     - [Sofifa](https://sofifa.com/)
     - [Transfermarkt](https://www.transfermarkt.com/)
     - [Footballdatabase.eu](https://www.footballdatabase.eu/)
   - Produit et met à jour les CSV :
     - `players.csv`
     - `teams.csv`
     - `transfers.csv`
     - `matches.csv`
     - `player_match_stats.csv`
     - `competitions.csv`
   - Peut déclencher la mise à jour de la base via REST ou Kafka.

3. **API REST**
   - `/api/players`
   - `/api/teams`
   - `/api/transfers`
   - `/api/matches`
   - `/api/update-data` → déclenche manuellement la mise à jour batch

---

## 🧱 Modèle de données

### **Entités principales**

| Entité | Description | CSV source |
|--------|--------------|-------------|
| `Player` | Données d’un joueur tunisien (identité, stats, club, etc.) | `players.csv` |
| `Team` | Informations d’un club (nom, ville, président, logo) | `teams.csv` |
| `Transfer` | Historique des transferts joueur ↔ club | `transfers.csv` |
| `Match` | Données d’un match (score, date, compétition) | `matches.csv` |
| `PlayerMatchStats` | Statistiques d’un joueur sur un match | `player_match_stats.csv` |
| `Competition` | Informations sur les ligues et coupes | `competitions.csv` |

---

## 🔄 Cycle de mise à jour

### ⚙️ Étapes du batch

1. **Collecte**
   - Récupère les données brutes depuis les API et sites web.
   - Nettoie et harmonise les formats (noms, dates, clubs).

2. **Fusion**
   - Combine les données issues de plusieurs sources.
   - Met à jour les CSV sous `/resources/data/`.

3. **Chargement**
   - Recharge les CSV dans H2 au démarrage **ou** à chaud via REST.
   - Option : publier les modifications sur Kafka pour synchroniser plusieurs instances.

4. **Planification**
   - Exécution automatique chaque dimanche à minuit :  
     `@Scheduled(cron = "0 0 0 * * SUN")`

---

## 📊 Diagramme d’architecture (Mermaid)

```mermaid
graph TD
<<<<<<< Updated upstream
    A[Sources externes Sofifa, Transfermarkt, Football-Data] -->|API/Scraping| B[Python Batch / Spring Batch]
    B --> C[CSV Files players.csv, teams.csv...]
=======
    A[Sources externes(Sofifa, Transfermarkt, Football-Data)] -->|API/Scraping| B[Python Batch / Spring Batch]
    B --> C[CSV Files]
>>>>>>> Stashed changes
    C --> D[H2 Database]
    D --> E[Spring Boot Web App]
    E --> F[Angular Frontend]
    E -->|REST API| G[/api/update-data/]
    E -->|Kafka Events| H[Autres Services / Instances]
```

---

## 📦 Structure des fichiers

```
src/
 ├─ main/
 │   ├─ java/com/soccerstats/
 │   │   ├─ model/              # Entités JPA
 │   │   ├─ repository/         # Repositories Spring Data
 │   │   ├─ controller/         # REST endpoints
 │   │   ├─ service/            # Services applicatifs
 │   │   └─ batch/              # Batch d’actualisation
 │   └─ resources/
 │       ├─ data/               # CSV sources
 │       │   ├─ players.csv
 │       │   ├─ teams.csv
 │       │   ├─ transfers.csv
 │       │   ├─ matches.csv
 │       │   ├─ player_match_stats.csv
 │       │   └─ competitions.csv
 │       └─ application.yml
 └─ test/
     └─ ...
```

---

## 📅 Roadmap

| Étape | Description | Statut |
|-------|--------------|--------|
| 1️⃣ | Structure de base Spring Boot + Angular | ✅ Fait |
| 2️⃣ | Chargement des `players.csv` et `teams.csv` | ✅ Fait |
| 3️⃣ | Extension du modèle (`Transfer`, `Match`, etc.) | ✅ Fait |
| 4️⃣ | Génération des CSV complets de test | ✅ Fait |
| 5️⃣ | Batch de mise à jour automatique | 🚧 En cours |
| 6️⃣ | Intégration Spring AI pour enrichissement auto | ⏳ À faire |
| 7️⃣ | Déploiement CI/CD sur GitLab + Monitoring | ⏳ À faire |

---

## 🧠 Intelligence artificielle (Spring AI)

Une fois les CSV fonctionnels, l’objectif est d’utiliser **Spring AI** pour :
- Déduire les informations manquantes (poste, performance, potentiel).  
- Évaluer les performances globales des joueurs.  
- Générer des rapports ou visualisations automatiques (ex: “meilleur buteur par club”).  

---

## 🧰 Outils & Dépendances clés

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `spring-boot-starter-ai`
- `spring-boot-starter-scheduling`
- `opencsv` ou `jackson-dataformat-csv`
- `h2database`
- `lombok`
- `jsoup` (pour scraping léger)
- `python` (scripts externes pour enrichissement)

---

## 🧪 Exemple de log de mise à jour

```
[INFO] Starting football data update job...
[INFO] Fetching data from football-data.org...
[INFO] Found 382 players, 16 teams, 240 matches.
[INFO] Writing updated CSV files...
[INFO] Reloading updated data into H2 database...
[INFO] Football data update completed successfully.
```

---

## 🤝 Contributions

Les contributions sont les bienvenues :  
- ajout de nouvelles sources de données,  
- amélioration du modèle,  
- optimisation des batchs ou intégration AI.  

---
