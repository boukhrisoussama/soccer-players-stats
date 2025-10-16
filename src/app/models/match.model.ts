export interface Match {
  id: string;
  competitionId?: string;
  homeTeamId?: string;
  awayTeamId?: string;
  date?: string;
  stadium?: string;
  scoreHome?: number;
  scoreAway?: number;
}