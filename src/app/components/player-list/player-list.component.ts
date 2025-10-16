import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ApiService } from '../../services/api.service';
import { Player } from '../../models/player.model';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.scss']
})
export class PlayerListComponent implements OnInit {
  displayedColumns: string[] = ['photo', 'name', 'position', 'club', 'goals', 'assists', 'minutes'];
  dataSource = new MatTableDataSource<Player>([]);
  loading = false;
  search = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.loading = true;
    this.api.getPlayers().subscribe({
      next: data => {
        this.dataSource.data = data;
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
        this.loading = false;
      },
      error: err => { console.error(err); this.loading = false; }
    });
  }

  applyFilter() {
    const filterValue = this.search.trim().toLowerCase();
    this.dataSource.filter = filterValue;
  }

  viewPlayer(id: string) {
    window.location.href = '/players/' + id;
  }
}
