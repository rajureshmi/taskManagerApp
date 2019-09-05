import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Task} from '../model/task.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskManagerService {
  private baseUrl = 'http://localhost:8080/taskmanager/api';

  constructor(private http: HttpClient) {
  }

  addTask(task: Task): Observable<any> {
    return this.http.post(`${this.baseUrl}/task/add`, task);
  }

  getAllTasks(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/task`);
  }

  getAllParentTasks(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/parenttask`);
  }


  updateTask(task: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/task/update/`, task);
  }

  endTask(task: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/task/end/`, task);
  }
}
