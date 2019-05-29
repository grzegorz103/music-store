import { Category } from "./category";

export class Disc {
	id: string;
	band: string;
	releaseDate: string;
	price: number;
	amount: string;
	deleted: string;
	title: string;
	description: string;
	images: string[];
	category: Category;
}
