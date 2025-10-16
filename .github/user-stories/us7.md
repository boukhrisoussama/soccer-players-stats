# Title
Implement intuitive landing page (Dashboard)

## Description
As a user, I want a dashboard that shows important football stats so that I get a quick overview when I open the app.

## Acceptance Criteria
- Displays total number of players, teams, matches, and transfers.
- Includes top 5 scorers and active clubs.
- Responsive and works on mobile.

## Definition of Done
- Dashboard component compiles and fetches data.
- Stats are live via `/api/stats`.
- Charts render correctly on desktop and mobile.

## Subtasks
- Create `dashboard` component in Angular.
- Implement `/api/stats` endpoint in Spring Boot.
- Integrate `ngx-charts` or `Chart.js`.
