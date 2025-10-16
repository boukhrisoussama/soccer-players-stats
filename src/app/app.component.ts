import { Component, ChangeDetectorRef } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  selectedLang = localStorage.getItem('lang') || 'fr';

  constructor(
                  private router: Router,
                  private translate: TranslateService,
                  private cdr: ChangeDetectorRef
             ) {
    const lang = localStorage.getItem('lang') || 'fr';
    this.translate.setDefaultLang(lang);
    this.translate.use(lang);
  }

  changeLang(selectedLang: string) {
    this.selectedLang = selectedLang;
    localStorage.setItem('lang', selectedLang);
    this.translate.use(selectedLang);
    this.cdr.markForCheck();
  }

  goToTeams() {
    this.router.navigate(['/teams']);
  }
  goToPlayers() {
    this.router.navigate(['/players']);
  }
  goToChampionship() {
    this.router.navigate(['/championship/COMP0001']);
  }
  goToCup() {
    this.router.navigate(['/cup/COMP0002']);
  }
  goToTransfers() {
    this.router.navigate(['/transfers']);
  }
  goToStats() {
    this.router.navigate(['/stats']);
  }
  goToPress() {
    this.router.navigate(['/articles']);
  }

}
