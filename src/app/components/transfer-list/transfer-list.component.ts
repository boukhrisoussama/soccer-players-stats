import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
selector: 'transfer-list',
templateUrl: './transfer-list.component.html',
styleUrls: ['./transfer-list.component.scss']
})
export class TransferListComponent implements OnInit {

constructor() {}

 ngOnInit(): void {  }

}
