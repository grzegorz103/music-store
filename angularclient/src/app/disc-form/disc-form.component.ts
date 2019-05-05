import { Component, OnInit } from '@angular/core';
import { DiscService } from '../service/disc.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Disc } from '../model/disc';

@Component({
  selector: 'app-disc-form',
  templateUrl: './disc-form.component.html',
  styleUrls: ['./disc-form.component.css']
})
export class DiscFormComponent implements OnInit {

  disc: Disc;

  constructor(private route: ActivatedRoute, private router: Router, private discService: DiscService) { }

  ngOnInit() {
    this.disc = new Disc();
  }

  onSubmit() {
    this.discService.save(this.disc).subscribe(result => this.goToDiscList());
  }

  goToDiscList() {
    this.router.navigate(['/discs']);
  }
}
