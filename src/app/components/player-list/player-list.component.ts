import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.scss']
})
export class PlayerListComponent implements OnInit {
  players: Player[] = [];
  teamId: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService,
    private router: Router
  ) {}

  ngOnInit() {
    this.teamId = this.route.snapshot.paramMap.get('teamId');
    if(this.teamId != null) {
      this.apiService.getPlayersByTeam(this.teamId).subscribe(data => this.players = data);
    }
  }

  viewPlayer(playerId: string) {
    this.router.navigate(['/players', playerId]);
  }

  backToTeamsList() {
      window.history.back();
      return false;
  }

}
