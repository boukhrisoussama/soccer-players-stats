# Title
Load initial data from CSV files

## Description
As a developer, I want to load player, team, and competition data from CSV files so that the application starts with relevant content in the database.

## Acceptance Criteria
- CSVs are parsed automatically on application startup.
- Data is persisted into H2 if not already present.
- Logs confirm data loading success or errors.

## Definition of Done
- Entities: Player, Team, Competition, Match, and Transfer are populated.
- Data visible via repository endpoints.
- Unit test validates loading logic.

## Subtasks
- Create CSV parser utility.
- Implement initialization service.
- Write integration test confirming CSV import.
