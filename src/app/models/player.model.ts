export interface Player {
  id: string;
  firstName: string;
  lastName: string;
  birthDate: string;
  nationality: string;
  position: string;
  photoUrl?: string;
  teamId: string;
  matchesPlayed?: number;
  goalsScored?: number;
  assistsMade?: number;
  yellowCards?: number;
  redCards?: number;
  minutesPlayed?: number;
}
