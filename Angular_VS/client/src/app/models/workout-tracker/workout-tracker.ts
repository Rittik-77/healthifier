export class WorkoutTracker {

    public id: number
    public email: string

    constructor(
        public date: string,
        public workoutName: string,
        public duration: number
    ) {}
}
