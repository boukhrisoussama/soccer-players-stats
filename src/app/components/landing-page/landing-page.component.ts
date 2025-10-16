// landing-page.component.ts
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { ApiService } from '../../services/api.service';
import { Article } from '../../models/article.model';
import { Player } from '../../models/player.model';
import { Team } from '../../models/team.model';


@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {
  searchControl = new FormControl('');
  searchType = 'player';
  filteredOptions: Observable<any[]>;
  latestArticles: Article[] = [];
  leagueStandings: Team[] = [];
  playerPerformances: Player[] = [];

  constructor(private apiService: ApiService) {
    this.filteredOptions = this.searchControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || ''))
    );
  }

  ngOnInit() {
    this.loadLatestArticles();
    this.loadLeagueStandings();
    this.loadPlayerPerformances();
  }

  getOptionType(option: any): string {
    if (option.firstName && option.lastName) return 'player';
    if (option.name && !option.firstName) return 'team';
    if (option.title) return 'article';
    return '';
  }

  onSearch() {
    const value = this.searchControl.value;
    if (!value) return;

    switch (this.searchType) {
      case 'player':
        // Handle player search
        break;
      case 'team':
        // Handle team search
        break;
      case 'article':
        // Handle article search
        break;
    }
  }

  onOptionSelected(option: any) {
    const type = this.getOptionType(option);
    // Handle selection based on type
  }

  private _filter(value: string): any[] {
    const filterValue = value.toLowerCase();
    // Implement filtering logic based on searchType
    return [];
  }

  private loadLatestArticles(): void {
    this.apiService.getLatestArticles().subscribe(
      (articles: Article[]) => this.latestArticles = articles
    );
  }

  private loadLeagueStandings(): void {
    this.apiService.getLeagueStandings().subscribe(
      (standings: Team[]) => this.leagueStandings = standings
    );
  }

  private loadPlayerPerformances(): void {
    this.apiService.getPlayerPerformances().subscribe(
      (performances: Player[]) => this.playerPerformances = performances
    );
  }

  readArticle(article: Article) {
    // Implement article reading logic
  }
}
