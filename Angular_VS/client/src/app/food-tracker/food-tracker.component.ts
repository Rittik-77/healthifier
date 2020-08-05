import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FoodService } from './../services/food/food.service';
import { FoodTracker } from './../models/food-tracker/food-tracker';
import { FoodTrackerService } from './../services/food-tracker/food-tracker.service';
import { Component, OnInit } from '@angular/core';
import { Food } from '../models/food/food';

@Component({
  selector: 'app-food-tracker',
  templateUrl: './food-tracker.component.html',
  styleUrls: ['./food-tracker.component.css']
})
export class FoodTrackerComponent implements OnInit {

  foodTrackerList: FoodTracker[]
  listExist: boolean
  uiMessage: string

  enableAddForm: boolean
  enableEditForm: boolean

  // Edit_Food_Tracker Form properties
  editFormFoodName: string
  editFormAmount: number
  editFormDate: string
  editFormId: number
  editFormQtyEnum: string
  
  constructor(
    private foodTrackerService: FoodTrackerService,
    private foodService: FoodService
  ) { }

  ngOnInit() {

    // Close all forms
    this.enableAddForm = false
    this.enableEditForm = false

    // Fetch food tracker wrt logged user
    this.foodTrackerService.getFoodTracker()
      .subscribe(response => {
        this.foodTrackerList = response
        this.listExist = true
      }, error => {
          if (error.error != null && error.error.exceptionLevel === 'INFO') {
            this.listExist = false
            this.uiMessage = error.error.uiMessage
          }
      })
    
  }

  onAdd() {
    this.enableEditForm = false
    this.enableAddForm = true
  }

  closeAddForm(eventObject: any) {
    // validate if form needs to be closed
    if (eventObject['closeAddForm']) {
      this.enableAddForm = false
    }

    // validate if page needs to be refreshed
    if (eventObject['addResponse']) {
      this.ngOnInit()
    }
  }

  closeEditForm(eventObject: any) {
    // validate if form needs to be closed
    if (eventObject['closeEditForm']) {
      this.enableEditForm = false
    }

    // validate if page needs to be refreshed
    if (eventObject['editResponse']) {
      this.ngOnInit()
    }
  }

  onEdit(foodTracker: FoodTracker) {
    // close add form
    this.enableAddForm = false

    // set variables to be sent to edit food tracker component
    this.editFormId = foodTracker.id
    this.editFormFoodName = foodTracker.foodName
    this.editFormAmount = foodTracker.amount

    // calculate date wrt IST
    let date = new Date(foodTracker.date)
    date.setMinutes(date.getMinutes() - date.getTimezoneOffset())
    this.editFormDate = date.toISOString().split("T")[0]

    // calculate qty enum
    this.foodService.getFoodByName(foodTracker.foodName)
      .subscribe(response => {
        let food: Food = response
        this.editFormQtyEnum = food.qtyEnum
      }, error => console.log(error))

    // enable edit form
    this.enableEditForm = true
  }

  onDelete(foodTracker: FoodTracker) {
    // Call API and refresh if successful
    this.foodTrackerService.deleteFoodFromTracker(foodTracker.id)
      .subscribe(response => {
        if (response as boolean)
          this.ngOnInit()
      }, error => console.error())
  }

}
