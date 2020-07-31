export class FoodTracker {

    public id: number
    public email: string

    constructor(
        public date: string,
        public foodName: string,
        public amount: number,
    ) {}
}
