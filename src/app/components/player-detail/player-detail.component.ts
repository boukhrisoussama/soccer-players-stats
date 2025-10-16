import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';
import { Transfer } from '../../models/transfer.model';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.scss']
})
export class PlayerDetailComponent implements OnInit {
  player: Player | null = null;
  transfers: Transfer[] = [];
  loading = false;

  constructor(private route: ActivatedRoute, private api: ApiService) {}

  ngOnInit() {
    const playerId = this.route.snapshot.paramMap.get('id');
    if (playerId) {
      this.loading = true;
      this.api.getPlayer(playerId).subscribe({
        next: (player: Player) => {
          this.player = player;
          this.loading = false;
        },
        error: (error: Error) => {
          console.error(error);
          this.loading = false;
        }
      });

      this.api.getTransfersByPlayer(playerId).subscribe({
        next: (transfers: Transfer[]) => this.transfers = transfers,
        error: (error: Error) => console.error(error)
      });
    }
  }

  backToPlayerList() {
      window.history.back();
      return false;
  }
}
