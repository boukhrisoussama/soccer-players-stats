package com.soccer.stats.model;

import jakarta.persistence.*;
import lombok.*;

public class League extends Competition {

  public League() {
    super();
    this.setType(CompetitionType.LEAGUE);
  }

}
