import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AddTaskComponent} from './add-task/add-task.component';
import {ViewTaskComponent} from './view-task/view-task.component';


[
  {path: '', redirectTo: 'addtask', pathMatch: 'full'},
  {path: 'addtask', component: AddTaskComponent},
  {path: 'viewtask', component: ViewTaskComponent},

];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
