# Title
Scrape Ligue 1 calendar and results

## Description
As a system, I want to scrape the Tunisian Ligue 1 calendar and results from the FTF website so that the app displays real match schedules and results.

## Acceptance Criteria
- Scraper loops over all matchdays (`journee=1–30`).
- Extracts date, time, home/away teams, score, and stadium.
- Matches are persisted or updated in the DB.
- `/api/matches` and `/api/matches/journee/{n}` endpoints return data.

## Definition of Done
- Data scraping is automated via a service or batch job.
- Endpoint `/api/matches/update` triggers scraping.
- Logging confirms number of matches imported.

## Subtasks
- Implement `MatchScraperService` using Jsoup.
- Create `Match`, `MatchRepository`, and `MatchController`.
- Write scraping integration test.
