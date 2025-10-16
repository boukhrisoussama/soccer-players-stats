export interface Stats {
  // Match stats
  totalMatches: number;
  averageGoalsPerMatch: number;
  totalGoals: number;

  // Team stats
  totalTeams: number;
  homeWins: number;
  awayWins: number;
  draws: number;

  // Player stats
  topScorers: {
    playerId: string;
    playerName: string;
    goals: number;
    team: string;
  }[];

  topAssists: {
    playerId: string;
    playerName: string;
    assists: number;
    team: string;
  }[];

  // Season specific stats
  season?: string;
  matchesPlayed?: number;
  matchesRemaining?: number;
  seasonProgress?: number;

  // Additional stats
  cleanSheets?: number;
  yellowCards?: number;
  redCards?: number;

  lastUpdated: Date;
}
