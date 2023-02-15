import { Injectable } from '@angular/core';
import { Curso } from '../models/curso';

@Injectable({
  providedIn: 'root'
})
export class CursoService extends CommonService<Curso>{

  protected baseEndpoint: string = 'http://localhost:8090/api/cursos';
}
