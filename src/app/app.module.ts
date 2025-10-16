import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatRadioModule } from '@angular/material/radio';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialogModule } from '@angular/material/dialog';
import { NgxEchartsModule } from 'ngx-echarts';
import * as echarts from 'echarts/core';
import { BarChart } from 'echarts/charts';
import { TitleComponent, TooltipComponent, GridComponent } from 'echarts/components';
import { CanvasRenderer } from 'echarts/renderers';

// Register only required ECharts modules
echarts.use([BarChart, TitleComponent, TooltipComponent, GridComponent, CanvasRenderer]);

// Composants de l'application

import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';
import { TransferListComponent } from './components/transfer-list/transfer-list.component';
import { TransferDetailComponent } from './components/transfer-detail/transfer-detail.component';
import { LeagueListComponent } from './components/league-list/league-list.component';
import { LeagueDetailComponent } from './components/league-detail/league-detail.component';
import { CupListComponent } from './components/cup-list/cup-list.component';
import { CupDetailComponent } from './components/cup-detail/cup-detail.component';
import { ArticleListComponent } from './components/article-list/article-list.component';
import { VideoPlayerDialogComponent } from './components/article-list/video-player-dialog.component';
import { StatsComponent } from './components/stats/stats.component';

// Fonction de factory pour le loader
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    TeamListComponent,
    TeamDetailComponent,
    PlayerListComponent,
    PlayerDetailComponent,
    TransferListComponent,
    TransferDetailComponent,
    LeagueListComponent,
    LeagueDetailComponent,
    CupListComponent,
    CupDetailComponent,
    ArticleListComponent,
    VideoPlayerDialogComponent,
    StatsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatToolbarModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    MatSelectModule,
    MatListModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatGridListModule,
    MatAutocompleteModule,
    MatButtonToggleModule,
    MatDialogModule,
    MatChipsModule,
    NgxEchartsModule.forRoot({ echarts }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
