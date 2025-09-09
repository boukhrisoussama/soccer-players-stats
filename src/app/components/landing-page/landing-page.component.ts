import { Component, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { TranslateService } from '@ngx-translate/core';
import { FormControl } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { debounceTime, switchMap, startWith } from 'rxjs/operators';
import { Player } from '../../models/player.model';
import { Team } from '../../models/team.model';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent {
  searchControl = new FormControl('');
  searchType: 'player' | 'team' = 'player';
  selectedLang = localStorage.getItem('lang') || 'fr';
  filteredOptions: Observable<(Player | Team)[]> = of([]);
  searchQuery = '';

  constructor(
    private apiService: ApiService,
    private router: Router,
    private translate: TranslateService,
    private cdr: ChangeDetectorRef
  ) {
    this.filteredOptions = this.searchControl.valueChanges.pipe(
      startWith(''),
      debounceTime(300),
      switchMap(value => this.fetchSuggestions(value))
    );
  }

  fetchSuggestions(value: string | null): Observable<(Player | Team)[]> {
    if (!value || value.length < 2) return of([]);
    if (this.searchType === 'player') {
      return this.apiService.searchPlayers(value);
    } else {
      return this.apiService.searchTeams(value);
    }
  }

  onOptionSelected(option: Player | Team) {
    if (this.searchType === 'player' && option && 'id' in option) {
      this.router.navigate(['/players', option.id]);
    } else if (this.searchType === 'team' && option && 'id' in option) {
      this.router.navigate(['/teams', option.id]);
    }
  }

  onSearch() {
    if (this.searchType === 'player') {
      this.router.navigate(['/players'], { queryParams: { search: this.searchControl.value } });
    } else {
      this.router.navigate(['/teams'], { queryParams: { search: this.searchControl.value } });
    }
  }

  goToTeams() {
    this.router.navigate(['/teams']);
  }

  changeLang(selectedLang: string) {
    this.selectedLang = selectedLang;
    localStorage.setItem('lang', selectedLang);
    this.translate.use(selectedLang);
    this.cdr.markForCheck();
  }

  isPlayer(option: Player | Team): option is Player {
    return (option as Player).firstName !== undefined && (option as Player).lastName !== undefined;
  }

  isTeam(option: Player | Team): option is Team {
    return (option as Team).name !== undefined;
  }
}
