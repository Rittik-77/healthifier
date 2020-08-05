import { FoodTracker } from './../models/food-tracker/food-tracker';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FoodTrackerService } from '../services/food-tracker/food-tracker.service';
import { FoodService } from '../services/food/food.service';
import { Food } from '../models/food/food';
import { Validators, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-food-tracker-edit-form',
  templateUrl: './food-tracker-edit-form.component.html',
  styleUrls: ['./food-tracker-edit-form.component.css']
})
export class FoodTrackerEditFormComponent implements OnInit {

  @Input() currentFoodTrackerName: string
  @Input() currentFoodTrackerAmount: number
  @Input() currentFoodTrackerDate: string
  @Input() currentFoodTrackerId: number
  @Input() currentFoodTrackerQtyEnum: string

  @Output() close = new EventEmitter()

  foodList: Food[]
  today = new Date().toISOString().split("T")[0]

  constructor(
    private foodTrackerService: FoodTrackerService,
    private foodService: FoodService
  ) {
   }

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

  ngOnInit() {

    this.foodService.getAllFoods()
      .subscribe(response => {
        this.foodList = response
      }, error => console.log(error))

    this.foodTrackerForm.get('foodName').setValue(this.currentFoodTrackerName)
    this.foodTrackerForm.get('amount').setValue(this.currentFoodTrackerAmount)
    this.foodTrackerForm.get('date').setValue(this.currentFoodTrackerDate)

  }

  closeForm() {
    this.close.emit({'closeEditForm': true});
  }

  // Whenever user changes the Food Name, this function gets called to find the qtyEnum 
  addFormFoodNameChanged($event: any) {
    if (event.target['value'] == '') {
      this.currentFoodTrackerQtyEnum = 'grams'
    }

    this.foodService.getFoodByName(event.target['value'])
      .subscribe(response => {
        let food: Food = response
        this.currentFoodTrackerQtyEnum = food.qtyEnum
      }, error => console.log(error))
    
  }

  // On submit button
  onSubmitUpdateToTracker() {
    let updatedFoodTracker = new FoodTracker(this.date.value, this.foodName.value, this.amount.value)
    updatedFoodTracker.id = this.currentFoodTrackerId

    this.foodTrackerService.updateFoodToTracker(this.currentFoodTrackerId, updatedFoodTracker)
      .subscribe(response => {
        if (response as boolean) {
          this.close.emit(
            {
              'closeEditForm': true,
              'editResponse': true
            }
          )
        }
      }, error => console.log(error)
      )
  }

}
