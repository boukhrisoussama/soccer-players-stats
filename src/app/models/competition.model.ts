import { Match } from './match.model';
import { Team } from './team.model';

export interface Competition {
  id: string;
  name: string;
  federation?: any;
  country?: string;
  type?: string;
  teams: Array<Team>;
  matches: Array<Match>;
}
