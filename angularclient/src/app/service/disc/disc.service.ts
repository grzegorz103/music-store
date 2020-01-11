import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Disc } from '../../model/disc';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class DiscService {

	private discsUrls: string;

	constructor(private http: HttpClient) {
		this.discsUrls =  environment.apiUrl +'/api/disc';
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

	public update(id: number, disc: Disc) {
		return this.http.put<Disc>(this.discsUrls + '/' + id, disc);
	}
}
