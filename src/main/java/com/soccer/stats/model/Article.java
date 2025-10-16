package com.soccer.stats.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "MEDIA_ARTICLES")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String author;
  private String publicationDate;
  private String url;
  private String excerpt;
  private String source;
  private String imageUrl;
  private String videoUrl;
  private String type;
  private String tags; // Store as JSON string or comma-separated
  private String players; // Store as JSON string or comma-separated
  private String teams; // Store as JSON string or comma-separated

}
