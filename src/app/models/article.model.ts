export interface Article {
  id: string; // Changed to string for compatibility
  title: string;
  excerpt: string;
  content?: string;
  source: string;
  date: string; // ISO string format
  imageUrl?: string;
  videoUrl?: string;
  tags: { name: string; color: 'primary' | 'accent' | 'warn' }[];
  url: string;
  type: 'article' | 'video';
  players: string[];
  teams: string[];
}
