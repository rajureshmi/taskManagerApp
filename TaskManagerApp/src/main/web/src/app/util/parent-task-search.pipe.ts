import {Pipe, PipeTransform} from '@angular/core';
import {ParentTask} from '../model/parent-task.model';

@Pipe({
  name: 'parentTaskSearch'
})
export class ParentTaskSearchPipe implements PipeTransform {

  transform(parentTasks: ParentTask[], filter: number): ParentTask {
    return parentTasks.filter(parentTask => parentTask.parentId === filter)[0];
  }

}
