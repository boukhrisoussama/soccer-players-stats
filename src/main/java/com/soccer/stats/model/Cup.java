package com.soccer.stats.model;

import jakarta.persistence.*;
import lombok.*;

public class Cup extends Competition {

  public Cup() {
    super();
    this.setType(CompetitionType.CUP);
  }

}
