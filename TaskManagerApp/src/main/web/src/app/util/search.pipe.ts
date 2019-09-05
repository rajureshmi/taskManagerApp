import {Pipe, PipeTransform} from '@angular/core';
import {Task} from '../model/task.model';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {
  private filterFlag: boolean;

  transform(tasks: Task[], searchByTask: string, searchByParentTask: number, searchByFromPriority: number,
            searchByToPriority: number, searchByStartDate: Date, searchByEndDate: Date): Task[] {
    if (tasks && (searchByTask || searchByParentTask || searchByFromPriority || searchByToPriority
        || searchByStartDate || searchByEndDate)) {
      return tasks.filter(task => {
        this.filterFlag = true;
        if (searchByTask && this.filterFlag && task.task.toLowerCase().indexOf(searchByTask.toLowerCase()) !== -1) {
          this.filterFlag = true;
        } else if (searchByTask) {
          this.filterFlag = false;
        }
        if (searchByParentTask && this.filterFlag && task.parentId === searchByParentTask) {
          this.filterFlag = true;
        } else if (searchByParentTask) {
          this.filterFlag = false;
        }
        if (searchByFromPriority && this.filterFlag && searchByToPriority && task.priority >= searchByFromPriority
          && task.priority <= searchByToPriority) {
          this.filterFlag = true;
        } else if (searchByFromPriority && searchByToPriority) {
          this.filterFlag = false;
        }
        if (!searchByToPriority && this.filterFlag && searchByFromPriority && task.priority >= searchByFromPriority) {
          this.filterFlag = true;
        } else if (!searchByToPriority && searchByFromPriority) {
          this.filterFlag = false;
        }
        if (!searchByFromPriority && this.filterFlag && searchByToPriority && task.priority <= searchByToPriority) {
          this.filterFlag = true;
        } else if (!searchByFromPriority && searchByToPriority) {
          this.filterFlag = false;
        }
        if (searchByStartDate && this.filterFlag && task.startDate === searchByStartDate) {
          this.filterFlag = true;
        } else if (searchByStartDate) {
          this.filterFlag = false;
        }
        if (searchByEndDate && this.filterFlag && task.endDate === searchByEndDate) {
          this.filterFlag = true;
        } else if (searchByEndDate) {
          this.filterFlag = false;
        }
        return this.filterFlag;
      });
    } else {
      return tasks;
    }
  }

}
