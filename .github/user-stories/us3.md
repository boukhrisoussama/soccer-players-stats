# Title
Manage players via REST API

## Description
As a frontend developer, I want to retrieve and update player data through REST endpoints so that I can display player information on the frontend.

## Acceptance Criteria
- `/api/players` returns all players.
- `/api/players/{id}` returns a specific player.
- `/api/players/search?name=` filters players by name.
- `/api/players/update` updates stats when new data is available.

## Definition of Done
- Controller and Service implemented and tested.
- Integration tests for CRUD operations pass.
- API documented via Swagger or OpenAPI.

## Subtasks
- Implement `PlayerController`, `PlayerService`, `PlayerRepository`.
- Add DTO mapping if needed.
- Write JUnit + MockMvc tests.
