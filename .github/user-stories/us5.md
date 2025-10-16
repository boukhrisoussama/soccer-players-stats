# Title
Display and track player transfers

## Description
As a football fan, I want to see the transfer history of players so that I can follow their career movements.

## Acceptance Criteria
- `/api/transfers` lists all transfers.
- Each transfer includes player, origin team, destination team, date, and fee.
- Transfers link correctly with Player and Team.

## Definition of Done
- Transfer data visible on player details.
- Integration test validates transfer relationships.

## Subtasks
- Implement `Transfer` entity, controller, repository.
- Load transfer.csv on startup.
- Write JUnit repository tests.
