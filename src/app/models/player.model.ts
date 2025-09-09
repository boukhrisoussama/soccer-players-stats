export interface Player {
    id: string;
    firstName: string;
    lastName: string;
    birthDate: Date;
    nationality: string;
    position: string;
    matchesPlayed: number;
    goalsScored: number;
    assistsMade: number;
    yellowCards: number;
    redCards: number;
    minutesPlayed: number;
    teamId: string;
}
