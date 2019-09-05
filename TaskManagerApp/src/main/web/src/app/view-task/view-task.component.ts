import {Component, OnInit, PipeTransform} from '@angular/core';
import {Task} from '../model/task.model';
import {TaskManagerService} from '../service/task-manager.service';
import {ParentTask} from '../model/parent-task.model';
import {Router} from '@angular/router';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {
  public taskDto: Task;
  public tasks: Task[];
  public parentTasks: ParentTask[];
  public searchByTask: string;
  public searchByParentTask: number;
  public searchByFromPriority: number;
  public searchByToPriority: number;
  public searchByStartDate: Date;
  public searchByEndDate: Date;

  constructor(private router: Router, private taskManagerService: TaskManagerService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getTasks();
    this.getAllParentTasks();
  }

  getTasks() {
    this.taskManagerService.getAllTasks()
      .subscribe(taskDto => this.tasks = taskDto);
  }

  getAllParentTasks() {
    this.taskManagerService.getAllParentTasks()
      .subscribe(parentTaskDto => this.parentTasks = parentTaskDto);
  }

  onClickEndTask(task) {
    this.taskDto = task;
    this.taskManagerService.endTask(this.taskDto)
      .subscribe(
        response => {
          this.getTasks();
          this.router.navigate(['/viewtask']);
        });
  }

  openUpdateModel(content, task) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
    this.taskDto = task;
  }

  onSubmit(formData) {
    this.taskDto = formData;

    this.taskManagerService.addTask(this.taskDto)
      .subscribe(
        response => {
          this.modalService.dismissAll();
          /*Reset Task Model*/
          this.router.navigate(['/viewtask']);
          this.taskDto = new Task();
        }
      );
  }
}
