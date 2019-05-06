import { Disc } from "./disc";
import { CartDto } from "./cart-dto";

export class CartItem {
    ID: number;
    disc: Disc;
    amount: number;

    constructor(id, disc, amount) {
        this.ID = id;
        this.disc = disc;
        this.amount = amount;
    }
}