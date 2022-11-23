import { Component, OnInit } from '@angular/core';

import { Team } from '../team';
import { LeagueService } from '../league.service';
import { MessageService } from '../message.service';
import { Route, RouterOutlet } from '@angular/router';
import { Player } from '../player';

@Component({
  selector: 'app-league',
  templateUrl: './league.component.html',
  styleUrls: ['./league.component.css']
})
export class LeagueComponent implements OnInit {
  title: any;

  constructor(
    private leagueService: LeagueService, 
    private messageService: MessageService) { }

  selectedTeam?: Team;
  selectedPlayer?: Player;
  json = require('../teams.json')

  ngOnInit(): void {
    this.getTeams();
  }

  teams: Team[] = [];


  onSelect(team: Team): void {
    this.selectedTeam = team;
    this.messageService.add(`LeagueComponent: Selected team id=${team.id}`);
  }

  onSelectPlayer(player: Player): void {
    this.selectedPlayer = player;
    this.messageService.add(`LeagueComponent: Selected player name=${player.name}`)
  }

  getTeams(): void {
    this.leagueService.getLeague().subscribe(teams => this.teams = teams);
  }

  add(team: Player[], id: number): void {
    if (!team) { return; }
    const newTeam = this.leagueService.createTeam(team, id);;
    this.leagueService.addTeam(newTeam)
      .subscribe(newTeam => {
        this.teams.push(newTeam);
      });
  }

  delete(team: Team): void {
    this.teams = this.teams.filter(i => i !== team);
    this.leagueService.deleteTeam(team.id).subscribe();
  }

  parseInt(string: string): number {
    return parseInt(string);
  }
}