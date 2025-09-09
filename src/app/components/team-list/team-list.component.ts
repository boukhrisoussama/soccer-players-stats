import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Team } from '../../models/team.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.scss']
})
export class TeamListComponent implements OnInit {
  teams: Team[] = [];

  constructor(private apiService: ApiService, private router: Router) {}

  ngOnInit() {
    this.apiService.getTeamsByCountry('Tunisia').subscribe(data => this.teams = data);
  }

  viewPlayers(teamId: string) {
    this.router.navigate(['/teams', teamId, 'players']);
  }
}
