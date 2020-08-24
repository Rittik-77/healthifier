import { Summary } from './../models/summary/summary';
import { SummaryService } from './../services/summary/summary.service';
import { Component, OnInit } from '@angular/core';
import { saveAs } from 'file-saver/dist/fileSaver'

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

  onDownload() {
    this.summaryService.getReport()
      .subscribe(response => {
        const blob = new Blob([response], { type: 'application/pdf;charset=utf-8' })
        const fileName = 'Report.pdf'
        saveAs(blob, fileName)
        console.log(response)
      }, error => console.log(error))
  }

}
