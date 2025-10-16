export interface PlayerMatchStats {
  id: string;
  playerId?: string;
  matchId?: string;
  minutesPlayed?: number;
  goals?: number;
  assists?: number;
  yellowCards?: number;
  redCards?: number;
  rating?: number;
}

