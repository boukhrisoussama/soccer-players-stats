import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';
import { TransferListComponent } from './components/transfer-list/transfer-list.component';
import { LeagueListComponent } from './components/league-list/league-list.component';
import { LeagueDetailComponent } from './components/league-detail/league-detail.component';
import { CupListComponent } from './components/cup-list/cup-list.component';
import { StatsComponent } from './components/stats/stats.component';
import { ArticleListComponent } from './components/article-list/article-list.component';
import { CupDetailComponent } from './components/cup-detail/cup-detail.component';

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'teams', component: TeamListComponent },
  { path: 'teams/:teamId', component: TeamDetailComponent },
  { path: 'teams/:teamId/players', component: PlayerListComponent },
  { path: 'players', component: PlayerListComponent },
  { path: 'players/:playerId', component: PlayerDetailComponent },
  { path: 'transfers', component: TransferListComponent },
  { path: 'transfers/:transferId', component: PlayerListComponent },
  { path: 'championship', component: LeagueListComponent },
  { path: 'championship/:championshipId', component: LeagueDetailComponent },
  { path: 'cup', component: CupListComponent },
  { path: 'cup/:cupId', component: LeagueDetailComponent },
  { path: 'stats', component: StatsComponent },
  { path: 'articles', component: ArticleListComponent },
  {
    path: 'competitions/:id',
    resolve: {
      competition: (route: any) => {
        const competitionType = route.data?.type;
        return competitionType === 'CUP' ? CupDetailComponent : LeagueDetailComponent;
      }
    },
    component: LeagueDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

/**
 * Module de routage pour l’application.
 */
