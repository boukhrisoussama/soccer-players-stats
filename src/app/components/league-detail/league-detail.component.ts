import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Match } from '../../models/match.model';
import { Competition } from '../../models/competition.model';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';

@Component({
  selector: 'app-league-detail',
  templateUrl: './league-detail.component.html',
  styleUrls: ['./league-detail.component.scss']
})
export class LeagueDetailComponent implements OnInit {
  championshipId = '';
  championship?: Competition;
  matches: Match[] = [];
  filteredMatches: Match[] = [];
  loading = false;
  selectedDate = new Date();
  selectedMatchday = 1;
  currentSeason = '2023-2024';
  matchdays: number[] = [];

  constructor(
    private route: ActivatedRoute,
    private api: ApiService
  ) {}

  ngOnInit(): void {
    this.championshipId = this.route.snapshot.paramMap.get('championshipId') || '';
    this.loading = true;
    this.loadChampionshipData();
  }

  private loadChampionshipData(): void {
    this.api.getCompetitions().subscribe({
      next: (competitions: Competition[]) => {
        this.championship = competitions.find(c => c.id === this.championshipId);
        this.loadMatches();
      },
      error: () => this.loading = false
    });
  }

  private loadMatches(): void {
    this.api.getMatches().subscribe({
      next: (allMatches: Match[]) => {
        this.matches = allMatches.filter(match => match.competitionId === this.championshipId);
        this.initializeMatchdays();
        this.filterMatchesByMatchday(this.selectedMatchday);
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  private initializeMatchdays(): void {
    // Encapsulate boundary: only use matches with defined matchday and number type
    const matchdayList = this.matches
      .map(m => ('matchday' in m && typeof m.matchday === 'number') ? m.matchday : 1);
    const maxMatchday = matchdayList.length ? Math.max(...matchdayList) : 1;
    this.matchdays = Array.from({length: maxMatchday}, (_, i) => i + 1);
  }

  onMatchdayChange(matchday: number): void {
    this.selectedMatchday = matchday;
    this.filterMatchesByMatchday(matchday);
  }

  handleDateSelected(date: any): void {
    this.selectedDate = date;
    this.filterMatchesByDate(date);
  }

  getMatchTime(dateArray: number[] | undefined): string {
    // Encapsulate boundary: dateArray must be number[]
    if (!Array.isArray(dateArray) || dateArray.length < 5) return '';
    const [year, month, day, hour, minute] = dateArray;
    return `${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}`;
  }

  private filterMatchesByMatchday(matchday: number): void {
    this.filteredMatches = this.matches.filter(m =>
      ('matchday' in m && typeof m.matchday === 'number' ? m.matchday : 1) === matchday
    );
  }

  private filterMatchesByDate(date: Date): void {
    this.filteredMatches = this.matches.filter(m => {
      if (!Array.isArray(m.date) || m.date.length < 3) return false;
      const matchDate = new Date(m.date[0], m.date[1] - 1, m.date[2]);
      return this.isSameDay(matchDate, date);
    });
  }

  private isSameDay(date1: Date, date2: Date): boolean {
    return date1.getFullYear() === date2.getFullYear() &&
           date1.getMonth() === date2.getMonth() &&
           date1.getDate() === date2.getDate();
  }

  dateClassCallback: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    if (view === 'month') {
      return this.matches.some(m => {
        if (!Array.isArray(m.date) || m.date.length < 3) return false;
        const matchDate = new Date(m.date[0], m.date[1] - 1, m.date[2]);
        return this.isSameDay(matchDate, cellDate);
      }) ? 'match-date-highlight' : '';
    }
    return '';
  }

  isArray(val: any): val is any[] {
    return Array.isArray(val);
  }

  isDateArray(value: any): value is number[] {
    return Array.isArray(value) && value.every(item => typeof item === 'number');
  }
}
