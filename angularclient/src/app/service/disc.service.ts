import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Disc } from '../model/disc';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DiscService {

	private discsUrls: string;

	constructor(private http: HttpClient) {
		this.discsUrls = 'http://localhost:8080/api/disc';
	}

	public findAll(): Observable<Disc[]> {

		return this.http.get<Disc[]>(this.discsUrls);
	}

	public save(disc: Disc) {
		return this.http.post<Disc>(this.discsUrls, disc);
	}

	public remove(id: number) {
		return this.http.delete(this.discsUrls + '/' + id);
	}

	public findById(id: number): Observable<Disc> {
		return this.http.get<Disc>(this.discsUrls + '/' + id);
	}
}
