import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../models/article.model';
import { Team } from '../models/team.model';
import { Player } from '../models/player.model';
import { Competition } from '../models/competition.model';
import { Match } from '../models/match.model';
import { Transfer } from '../models/transfer.model';
import { Stats } from '../models/stats.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly API_URL = '/api'; // Replace with your actual API URL

  constructor(private http: HttpClient) {}

  // Competition endpoints
  getCompetitions(): Observable<Competition[]> {
    return this.http.get<Competition[]>(`${this.API_URL}/competitions`);
  }

  // Player endpoints
  getPlayers(): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.API_URL}/players`);
  }

  getPlayer(id: string): Observable<Player> {
    return this.http.get<Player>(`${this.API_URL}/players/${id}`);
  }

  // Team endpoints
  getTeamsByCountry(country: string): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.API_URL}/teams`, { params: { country } });
  }

  // Match endpoints
  getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(`${this.API_URL}/matches`);
  }

  // Transfer endpoints
  getTransfersByPlayer(playerId: string): Observable<Transfer[]> {
    return this.http.get<Transfer[]>(`${this.API_URL}/transfers`, { params: { playerId } });
  }

  // Stats endpoints
  getMatchStats(): Observable<Stats> {
    return this.http.get<Stats>(`${this.API_URL}/stats/matches`);
  }

  getTeamStats(): Observable<Stats> {
    return this.http.get<Stats>(`${this.API_URL}/stats/teams`);
  }

  getPlayerStats(): Observable<Stats> {
    return this.http.get<Stats>(`${this.API_URL}/stats/players`);
  }

  getSeasonStats(season: string): Observable<Stats> {
    return this.http.get<Stats>(`${this.API_URL}/stats/season/${season}`);
  }

  // Latest articles
  getLatestArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(`${this.API_URL}/articles/latest`);
  }

  // League standings
  getLeagueStandings(): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.API_URL}/teams/standings`);
  }

  // Player performances
  getPlayerPerformances(): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.API_URL}/players/performances`);
  }
}
