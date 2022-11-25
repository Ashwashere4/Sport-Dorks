import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeamService } from '../team.service';
import { Location } from '@angular/common';
import { Player } from '../player';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {

  player: Player | undefined;

  constructor(
    private route: ActivatedRoute,
    private teamService: TeamService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getPlayer();
  }

  getPlayer(): void {
    const name = this.route.snapshot.paramMap.get(`name`)!;
    this.teamService.getPlayer(name)
      .subscribe(player => this.player = player);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    if (this.player) {
      this.teamService.updatePlayer(this.player)
        .subscribe(() => this.goBack());
    }
  }

}
