import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
selector: 'team-detail',
templateUrl: './team-detail.component.html',
styleUrls: ['./team-detail.component.scss']
})
export class TeamDetailComponent implements OnInit {

constructor() {}

 ngOnInit(): void {  }

}
