import { Summary } from './../models/summary/summary';
import { SummaryService } from './../services/summary/summary.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {

  summaryList: Summary[]
  listExist: boolean
  enableDownloadButton: boolean

  constructor(private summaryService: SummaryService) { }

  ngOnInit() {

    this.summaryService.getSummary()
      .subscribe(response => {
        this.summaryList = response
        this.listExist = true
        console.log(this.summaryList)
      }, error => console.log(error))
  }

}
