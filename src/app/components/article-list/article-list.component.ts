import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ApiService } from '../../services/api.service';
import { MatDialog } from '@angular/material/dialog';
import { TranslateService } from '@ngx-translate/core';
import { Article } from '../../models/article.model';
import { VideoPlayerDialogComponent } from './video-player-dialog.component';

@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {
  articles: Article[] = [];
  filteredArticles: Article[] = [];
  isLoading = true;
  searchTerm = '';
  selectedType: 'all' | 'article' | 'video' = 'all';
  selectedTags: string[] = [];
  selectedSource: string = '';
  sources: string[] = [];
  availableTags: string[] = [];

  constructor(
    private apiService: ApiService,
    private dialog: MatDialog,
    private translate: TranslateService
  ) {}

  ngOnInit() {
    // In a real app, this would come from the API
    this.loadArticles();
  }

  loadArticles() {
    this.articles = [
      {
        id: '1',
        title: 'Wahbi Khazri marque un but spectaculaire',
        excerpt: 'Un but exceptionnel du capitaine tunisien...',
        content: 'Article complet sur la performance...',
        source: 'Foot Tunisien',
        date: '2024-01-15T00:00:00Z',
        imageUrl: '/assets/articles/khazri-goal.jpg',
        tags: [
          { name: 'Équipe Nationale', color: 'primary' },
          { name: 'But du mois', color: 'accent' }
        ],
        url: 'https://example.com/article1',
        type: 'article',
        players: ['Wahbi Khazri'],
        teams: ['Équipe Nationale']
      },
      {
        id: '2',
        title: 'Les meilleurs moments de Hannibal Mejbri',
        excerpt: 'Compilation des actions du jeune prodige...',
        source: 'Tunisia Sports',
        date: '2024-01-14T00:00:00Z',
        videoUrl: 'https://youtube.com/watch?v=xyz',
        tags: [
          { name: 'Premier League', color: 'primary' },
          { name: 'Jeunes talents', color: 'warn' }
        ],
        url: 'https://example.com/video1',
        type: 'video',
        players: ['Hannibal Mejbri'],
        teams: ['Manchester United']
      }
    ];

    // Extract unique sources and tags
    this.sources = [...new Set(this.articles.map(a => a.source))];
    this.availableTags = [...new Set(this.articles.flatMap(a => a.tags.map(t => t.name)))];

    this.applyFilters();
    this.isLoading = false;
  }

  applyFilters() {
    this.filteredArticles = this.articles.filter(article => {
      const matchesSearch = this.searchTerm ?
        article.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        article.excerpt.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        article.players.some(p => p.toLowerCase().includes(this.searchTerm.toLowerCase())) ||
        article.teams.some(t => t.toLowerCase().includes(this.searchTerm.toLowerCase()))
        : true;

      const matchesType = this.selectedType === 'all' || article.type === this.selectedType;

      const matchesSource = !this.selectedSource || article.source === this.selectedSource;

      const matchesTags = this.selectedTags.length === 0 ||
        this.selectedTags.every(tag => article.tags.some(t => t.name === tag));

      return matchesSearch && matchesType && matchesSource && matchesTags;
    });
  }

  onSearch(event: Event) {
    this.searchTerm = (event.target as HTMLInputElement).value;
    this.applyFilters();
  }

  onTypeChange() {
    this.applyFilters();
  }

  onSourceChange() {
    this.applyFilters();
  }

  onTagsChange() {
    this.applyFilters();
  }

  openArticle(article: Article) {
    if (article.type === 'video') {
      // Open video in dialog
      this.dialog.open(VideoPlayerDialogComponent, {
        data: { videoUrl: article.videoUrl },
        width: '800px',
        height: '500px'
      });
    } else {
      // Open article in new tab
      window.open(article.url, '_blank');
    }
  }

  getFormattedDate(dateStr: string): string {
    return new Date(dateStr).toLocaleDateString(this.translate.currentLang);
  }
}
