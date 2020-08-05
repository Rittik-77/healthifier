import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Validators, FormGroup, FormControl } from '@angular/forms';
import { FoodService } from '../services/food/food.service';
import { Food } from '../models/food/food';
import { FoodTracker } from '../models/food-tracker/food-tracker';
import { FoodTrackerService } from '../services/food-tracker/food-tracker.service';

@Component({
  selector: 'app-food-tracker-add-form',
  templateUrl: './food-tracker-add-form.component.html',
  styleUrls: ['./food-tracker-add-form.component.css']
})
export class FoodTrackerAddFormComponent implements OnInit {

  @Output() close = new EventEmitter()
  foodList: Food[]
  calculatedQtyEnum: string = 'grams'
  today = new Date().toISOString().split("T")[0]

  constructor(
    private foodTrackerService: FoodTrackerService,
    private foodService: FoodService
  ) { }

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
  }

  // On submit button
  onSubmitAddToTracker() {
    let foodTrackerVO = new FoodTracker(this.date.value, this.foodName.value, this.amount.value)
    this.foodTrackerService.addFoodToTracker(foodTrackerVO)
      .subscribe(response => {
        if (response as boolean) {
          this.close.emit(
            {
              'closeAddForm': true,
              'addResponse': true
            });
        }
      }, error => console.log(error))
  }

  // Whenever user changes the Food Name, this function gets called to find the qtyEnum 
  addFormFoodNameChanged($event: any) {
    if (event.target['value'] == '') {
      this.calculatedQtyEnum = 'grams'
    }

    this.foodService.getFoodByName(event.target['value'])
      .subscribe(response => {
        let food: Food = response
        this.calculatedQtyEnum = food.qtyEnum
      }, error => console.log(error))
  }

  closeForm() {
    this.close.emit({'closeAddForm': true});
  }

}