export interface Transfer {
  id: string;
  playerId?: string;
  fromTeamId?: string;
  toTeamId?: string;
  date?: string;
  fee?: number;
  contractYears?: number;
}