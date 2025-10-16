package com.soccer.stats.repository;

import com.soccer.stats.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findByPlayers_Id(String playerId);
    List<Article> findByTeams_Id(String teamId);
    List<Article> findByTitleContainingIgnoreCase(String title);
}

