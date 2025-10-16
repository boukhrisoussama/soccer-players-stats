# Title
Display leagues and cups

## Description
As a user, I want to view the list of Tunisian leagues and cup competitions with logos and basic details.

## Acceptance Criteria
- `/api/competitions` returns all competitions.
- Angular `league-list` and `cup-list` components display competitions.
- Each card shows name, season, and number of teams.

## Definition of Done
- Competitions visible in the frontend.
- Routes `/leagues` and `/cups` available.

## Subtasks
- Implement `Competition` entity and controller.
- Create Angular `league-list` and `cup-list` components.
- Load competitions from `competitions.csv`.
