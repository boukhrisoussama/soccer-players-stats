import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'teams', component: TeamListComponent },
  { path: 'teams/:teamId/players', component: PlayerListComponent },
  { path: 'players/:playerId', component: PlayerDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

/**
 * Module de routage pour l’application.
 */
