import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Competition } from '../../models/competition.model';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-cup-detail',
  templateUrl: './cup-detail.component.html',
  styleUrls: ['./cup-detail.component.scss']
})
export class CupDetailComponent implements OnInit {
  cupId = '';
  cup?: Competition;
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private api: ApiService) {}

  ngOnInit(): void {
    this.cupId = this.route.snapshot.paramMap.get('id') || '';
    this.loading = true;

    this.api.getCompetitions().subscribe({
      next: (competitions: Competition[]) => {
        this.cup = competitions.find(c => c.id === this.cupId && c.type === 'CUP');
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }
}

