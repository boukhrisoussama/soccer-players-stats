import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';
import { TransferListComponent } from './components/transfer-list/transfer-list.component';
import { LeagueListComponent } from './components/league-list/league-list.component';
import { CupListComponent } from './components/cup-list/cup-list.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'teams', component: TeamListComponent },
  { path: 'teams/:teamId', component: TeamDetailComponent },
  { path: 'players', component: PlayerListComponent },
  { path: 'players/:playerId', component: PlayerDetailComponent },
  { path: 'transfers', component: TransferListComponent },
  { path: 'transfers/:transferId', component: PlayerListComponent },
  { path: 'championship', component: LeagueListComponent },
  { path: 'cup', component: CupListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

/**
 * Module de routage pour l’application.
 */
