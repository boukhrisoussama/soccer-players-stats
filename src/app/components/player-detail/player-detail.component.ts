import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.scss']
})
export class PlayerDetailComponent implements OnInit {
  player: Player | null = null;

  constructor(private route: ActivatedRoute, private apiService: ApiService) {}

  ngOnInit() {
    const playerId = this.route.snapshot.paramMap.get('playerId');
    if(playerId != null) {
      this.apiService.getPlayer(playerId).subscribe(player => {
        this.player = player || null;
      });
    }
  }

  backToPlayerList() {
      window.history.back();
      return false;
  }
}
