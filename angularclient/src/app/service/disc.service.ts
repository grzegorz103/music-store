import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
}
