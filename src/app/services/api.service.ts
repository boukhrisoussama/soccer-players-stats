import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../models/player.model';
import { Team } from '../models/team.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api'; // À adapter

  constructor(private http: HttpClient) {}

  getTeamsByCountry(country: string): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.baseUrl}/teams?country=${country}`);
  }

  getPlayersByTeam(teamId: string): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.baseUrl}/players?teamId=${teamId}`);
  }

  getPlayer(id: string): Observable<Player> {
    return this.http.get<Player>(`${this.baseUrl}/players/${id}`);
  }

  searchPlayers(query: string): Observable<Player[]> {
    return this.http.get<Player[]>(`${this.baseUrl}/players/search?name=${query}`);
  }

  searchTeams(query: string): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.baseUrl}/teams/search?name=${query}`);
  }
}
