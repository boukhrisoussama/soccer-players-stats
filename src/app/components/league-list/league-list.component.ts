import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
selector: 'league-list',
templateUrl: './league-list.component.html',
styleUrls: ['./league-list.component.scss']
})
export class LeagueListComponent implements OnInit {

constructor() {}

 ngOnInit(): void {  }

}
