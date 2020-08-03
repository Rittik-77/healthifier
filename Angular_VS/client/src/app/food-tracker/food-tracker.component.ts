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
  foodList: Food[]
  listExist: boolean
  uiMessage: string
  enableAddForm: boolean
  calculatedQtyEnum: string = 'grams'
  today = new Date().toISOString().split("T")[0]

  foodTrackerForm = new FormGroup({
    foodName: new FormControl('', Validators.required),
    amount: new FormControl('', [Validators.required, Validators.pattern('[0-9]+([.][0-9]+)*')]),
    date: new FormControl('', Validators.required)
  })

  get foodName() {
    return this.foodTrackerForm.get('foodName')
  }

  get amount() {
    return this.foodTrackerForm.get('amount')
  }

  get date() {
    return this.foodTrackerForm.get('date')
  }

  constructor(
    private foodTrackerService: FoodTrackerService,
    private foodService: FoodService
  ) { }

  ngOnInit() {

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
    
    this.foodService.getAllFoods()
      .subscribe(response => {
        this.foodList = response
      }, error => console.log(error))
  }

  onAdd() {
    this.enableAddForm = true
  }

  closeAddForm() {
    this.enableAddForm = false
  }

  addFormFoodNameChanged($event: any) {
    if (event.target['value'] == '') {
      this.calculatedQtyEnum = 'grams'
    }

    this.foodService.getFoodByName(event.target['value'])
      .subscribe(response => {
        let food: Food = response
        this.calculatedQtyEnum = food.qtyEnum
        if (this.calculatedQtyEnum === 'NUMBER')
          this.calculatedQtyEnum = 'N'
        else
          this.calculatedQtyEnum = this.calculatedQtyEnum.toLowerCase()
      }, error => console.log(error))
  }

  onSubmitAddToTracker() {
    let foodTrackerVO = new FoodTracker(this.date.value, this.foodName.value, this.amount.value)
    this.foodTrackerService.addFoodToTracker(foodTrackerVO)
      .subscribe(response => {
        if (response as boolean) {
          this.enableAddForm = false
          this.ngOnInit()
        }
      }, error => console.log(error))
  }

  onDelete(foodTracker: FoodTracker) {
    console.log(foodTracker)
  }

  onEdit(foodTracker: FoodTracker) {
    console.log(foodTracker)
  }
}
