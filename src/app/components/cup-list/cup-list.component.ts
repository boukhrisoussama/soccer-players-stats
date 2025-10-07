import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
selector: 'cup-list',
templateUrl: './cup-list.component.html',
styleUrls: ['./cup-list.component.scss']
})
export class CupListComponent implements OnInit {

constructor() {}

 ngOnInit(): void {  }

}
