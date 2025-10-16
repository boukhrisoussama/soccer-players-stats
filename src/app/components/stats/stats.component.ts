import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { forkJoin, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { EChartsOption } from 'echarts';

interface KPI {
  label: string;
  value: number | string;
  trend?: 'up' | 'down' | 'neutral';
  percentage?: number;
  icon: string;
  description?: string;
}

interface StatsCategory {
  title: string;
  icon: string;
  color: string;
  kpis: KPI[];
}

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {
  isLoading = true;
  selectedSeason = '2023-2024';

  competitionStats: StatsCategory = {
    title: 'COMPETITION_STATS',
    icon: 'emoji_events',
    color: 'primary',
    kpis: [
      {
        label: 'TOTAL_MATCHES',
        value: 240,
        icon: 'sports_soccer',
        description: 'Total matches played this season'
      },
      {
        label: 'TOTAL_GOALS',
        value: 612,
        icon: 'sports_soccer',
        trend: 'up',
        percentage: 8.5,
        description: 'Total goals scored across all competitions'
      },
      {
        label: 'AVG_GOALS_PER_MATCH',
        value: 2.55,
        icon: 'assessment',
        description: 'Average goals scored per match'
      },
      {
        label: 'CLEAN_SHEETS',
        value: 89,
        icon: 'shield',
        description: 'Matches with no goals conceded'
      },
      {
        label: 'YELLOW_CARDS',
        value: 1247,
        icon: 'warning',
        description: 'Total yellow cards issued'
      },
      {
        label: 'RED_CARDS',
        value: 67,
        icon: 'block',
        trend: 'down',
        percentage: -12.3,
        description: 'Total red cards issued'
      }
    ]
  };

  teamStats: StatsCategory = {
    title: 'TEAM_STATS',
    icon: 'groups',
    color: 'accent',
    kpis: [
      {
        label: 'HIGHEST_SCORING_TEAM',
        value: 'Espérance ST',
        icon: 'leaderboard',
        description: '68 goals scored'
      },
      {
        label: 'BEST_DEFENSE',
        value: 'Club Africain',
        icon: 'security',
        description: 'Only 18 goals conceded'
      },
      {
        label: 'MOST_WINS',
        value: 'Espérance ST',
        icon: 'military_tech',
        description: '22 victories'
      },
      {
        label: 'BEST_HOME_RECORD',
        value: 'Étoile du Sahel',
        icon: 'home',
        description: '15 wins out of 16 home games'
      },
      {
        label: 'MOST_DRAWS',
        value: 'CS Sfaxien',
        icon: 'balance',
        description: '12 drawn matches'
      },
      {
        label: 'LONGEST_WIN_STREAK',
        value: 'Espérance ST',
        icon: 'trending_up',
        description: '8 consecutive wins'
      }
    ]
  };

  playerStats: StatsCategory = {
    title: 'PLAYER_STATS',
    icon: 'person',
    color: 'warn',
    kpis: [
      {
        label: 'TOP_SCORER',
        value: 'Anice Badri',
        icon: 'sports_soccer',
        description: '24 goals'
      },
      {
        label: 'MOST_ASSISTS',
        value: 'Saad Bguir',
        icon: 'front_hand',
        description: '16 assists'
      },
      {
        label: 'BEST_RATING',
        value: 'Wahbi Khazri',
        icon: 'star',
        description: '8.2 average rating'
      },
      {
        label: 'MOST_MINUTES',
        value: 'Mouez Hasan',
        icon: 'timer',
        description: '2,340 minutes played'
      },
      {
        label: 'CLEAN_SHEETS_GK',
        value: 'Farouk Ben Mustapha',
        icon: 'sports_handball',
        description: '15 clean sheets'
      },
      {
        label: 'MOST_CARDS',
        value: 'Ali Maaloul',
        icon: 'warning',
        description: '12 yellow cards'
      }
    ]
  };

  leagueTable = [
    { position: 1, team: 'Espérance ST', points: 68, played: 30, wins: 22, draws: 2, losses: 6 },
    { position: 2, team: 'Étoile du Sahel', points: 62, played: 30, wins: 19, draws: 5, losses: 6 },
    { position: 3, team: 'Club Africain', points: 58, played: 30, wins: 17, draws: 7, losses: 6 },
    { position: 4, team: 'CS Sfaxien', points: 52, played: 30, wins: 15, draws: 7, losses: 8 },
    { position: 5, team: 'US Monastir', points: 48, played: 30, wins: 14, draws: 6, losses: 10 }
  ];

  topScorers = [
    { rank: 1, player: 'Anice Badri', team: 'Espérance ST', goals: 24 },
    { rank: 2, player: 'Kingsley Eduwo', team: 'Étoile du Sahel', goals: 19 },
    { rank: 3, player: 'Saad Bguir', team: 'Club Africain', goals: 17 },
    { rank: 4, player: 'Hamdi Nagguez', team: 'CS Sfaxien', goals: 15 },
    { rank: 5, player: 'Youssef Msakni', team: 'Espérance ST', goals: 14 }
  ];

  // ECharts option for top scorers
  topScorersChartOption: EChartsOption = {};

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.loadStats();
    this.initTopScorersChart();
  }

  loadStats() {
    this.isLoading = true;

    // Try to load real data, but fall back to mock data if API fails
    forkJoin({
      matchStats: this.apiService.getMatchStats().pipe(catchError(() => of(null))),
      teamStats: this.apiService.getTeamStats().pipe(catchError(() => of(null))),
      playerStats: this.apiService.getPlayerStats().pipe(catchError(() => of(null))),
      seasonStats: this.apiService.getSeasonStats(this.selectedSeason).pipe(catchError(() => of(null)))
    }).subscribe({
      next: (data) => {
        // If real data is available, update the stats
        if (data.matchStats) {
          this.updateCompetitionStats(data.matchStats);
        }
        if (data.teamStats) {
          this.updateTeamStats(data.teamStats);
        }
        if (data.playerStats) {
          this.updatePlayerStats(data.playerStats);
        }
        this.isLoading = false;
      },
      error: (error) => {
        console.log('Using mock data for stats');
        this.isLoading = false;
      }
    });
  }

  private updateCompetitionStats(matchStats: any) {
    if (matchStats.totalMatches) this.competitionStats.kpis[0].value = matchStats.totalMatches;
    if (matchStats.totalGoals) this.competitionStats.kpis[1].value = matchStats.totalGoals;
    if (matchStats.averageGoalsPerMatch) this.competitionStats.kpis[2].value = matchStats.averageGoalsPerMatch.toFixed(2);
    if (matchStats.cleanSheets) this.competitionStats.kpis[3].value = matchStats.cleanSheets;
    if (matchStats.yellowCards) this.competitionStats.kpis[4].value = matchStats.yellowCards;
    if (matchStats.redCards) this.competitionStats.kpis[5].value = matchStats.redCards;
  }

  private updateTeamStats(teamStats: any) {
    if (teamStats.highestScoringTeam) {
      this.teamStats.kpis[0].value = teamStats.highestScoringTeam.name;
      this.teamStats.kpis[0].description = `${teamStats.highestScoringTeam.goals} goals scored`;
    }
    if (teamStats.bestDefense) {
      this.teamStats.kpis[1].value = teamStats.bestDefense.name;
      this.teamStats.kpis[1].description = `${teamStats.bestDefense.goalsAgainst} goals conceded`;
    }
  }

  private updatePlayerStats(playerStats: any) {
    if (playerStats.topScorer) {
      this.playerStats.kpis[0].value = playerStats.topScorer.name;
      this.playerStats.kpis[0].description = `${playerStats.topScorer.goals} goals`;
    }
    if (playerStats.mostAssists) {
      this.playerStats.kpis[1].value = playerStats.mostAssists.name;
      this.playerStats.kpis[1].description = `${playerStats.mostAssists.assists} assists`;
    }
    this.initTopScorersChart();
  }

  // Initialize ECharts option for top scorers
  private initTopScorersChart() {
    this.topScorersChartOption = {
      title: {
        text: 'Top Scorers',
        left: 'center'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: { type: 'shadow' }
      },
      xAxis: {
        type: 'category',
        data: this.topScorers.map(s => s.player),
        axisLabel: { rotate: 30 }
      },
      yAxis: {
        type: 'value',
        name: 'Goals'
      },
      series: [
        {
          data: this.topScorers.map(s => s.goals),
          type: 'bar',
          itemStyle: {
            color: '#1976d2'
          },
          label: {
            show: true,
            position: 'top'
          }
        }
      ]
    };
  }

  onSeasonChange() {
    this.loadStats();
  }

  getTrendIcon(trend?: string): string {
    switch (trend) {
      case 'up': return 'trending_up';
      case 'down': return 'trending_down';
      default: return '';
    }
  }

  getTrendClass(trend?: string): string {
    switch (trend) {
      case 'up': return 'trend-positive';
      case 'down': return 'trend-negative';
      default: return '';
    }
  }
}
