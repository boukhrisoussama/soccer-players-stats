import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Competition } from '../../models/competition.model';

@Component({
  selector: 'league-list',
  templateUrl: './league-list.component.html',
  styleUrls: ['./league-list.component.scss']
  })
export class LeagueListComponent implements OnInit {

  leagues: Competition[] = [];
  loading = false;

  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.loading = true;
    this.api.getCompetitions().subscribe({
      next: (data: Competition[]) => {
        this.leagues = data.filter((c: Competition) => c.type === 'LEAGUE');
        this.loading = false;
      },
      error: (error: Error) => {
        console.error(error);
        this.loading = false;
      }
    });
  }
}
