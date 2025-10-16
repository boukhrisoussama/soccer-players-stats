# Title
Manage teams via REST API

## Description
As a user, I want to view and manage teams so that I can access club information like name, stadium, and city.

## Acceptance Criteria
- `/api/teams` lists all teams.
- `/api/teams/{id}` returns team details.
- Teams and players are properly linked.

## Definition of Done
- Entities and relationships validated.
- Repository methods tested.
- Data loads correctly from teams.csv.

## Subtasks
- Implement `TeamController`, `TeamService`, `TeamRepository`.
- Add one-to-many relation with Player.
- Write service tests.
