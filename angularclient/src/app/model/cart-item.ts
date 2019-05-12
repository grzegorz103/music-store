import { Disc } from "./disc";

export class CartItem {
    ID: number;
    disc: Disc;
    amount: number;

    constructor(id: number, disc: Disc, amount: number) {
        this.ID = id;
        this.disc = disc;
        this.amount = amount;
    }
}