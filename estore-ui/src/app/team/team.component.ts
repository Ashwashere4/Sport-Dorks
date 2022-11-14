import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';
import { Player } from '../player';
import { TeamService } from '../team.service';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {
  title: any;

  constructor(
    private teamService: TeamService, 
    private messageService: MessageService) { }

  selectedPlayer?: Player;
  json = require('../players.json')

  ngOnInit(): void {
    this.getPlayers();
  }

  players: Player[] = [];


  onSelect(player: Player): void {
    this.selectedPlayer = player;
    this.messageService.add(`TeamComponent: Selected player name=${player.name}`);
  }

  getPlayers(): void {
    this.teamService.getTeam().subscribe(players => this.players = players);
  }

  add(name: string, age: number, rating: number): void {
    name = name.trim();
    if (!name) { return; }
    const newPlayer = this.teamService.createPlayer(name, age, rating);;
    this.teamService.addPlayer(newPlayer)
      .subscribe(newPlayer => {
        this.players.push(newPlayer);
      });
  }

  delete(player: Player): void {
    this.players = this.players.filter(i => i !== player);
    this.teamService.deletePlayer(player.name).subscribe();
  }

  parseInt(string: string): number {
    return parseInt(string);
  }
}
