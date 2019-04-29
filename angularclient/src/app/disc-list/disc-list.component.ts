import { Component, OnInit } from '@angular/core';
import { Disc } from '../model/disc';
import { DiscService } from '../service/disc.service';

@Component({
  selector: 'app-disc-list',
  templateUrl: './disc-list.component.html',
  styleUrls: ['./disc-list.component.css']
})
export class DiscListComponent implements OnInit {

	discs: Disc[];
	
  constructor(private discService: DiscService) { }

  ngOnInit() {
	  this.discService.findAll().subscribe(data => {
		  this.discs = data;
	  });
  }

}
