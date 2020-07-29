export class User {
    
    constructor(

        public email: string,
        public password: string,
        public username?: string,
        public weight?: number,
        public role?: string

    ) {}
}
