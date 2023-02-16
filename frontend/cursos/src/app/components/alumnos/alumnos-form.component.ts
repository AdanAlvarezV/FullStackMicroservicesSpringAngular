import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { CommonFormComponent } from '../common-form.component';
import { Generic } from 'src/app/models/generic';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent extends CommonFormComponent<Alumno, AlumnoService> implements OnInit {

  private fotoSeleccinada: File;

  constructor(service: AlumnoService,
              router: Router,
              route: ActivatedRoute) {

    super(service, router, route);
    this.titulo = "Crear Alumnos";
    this.model = new Alumno();
    this.redirect = "/alumnos";
    this.nombreModel = Alumno.name;
    }

    public seleccionarFoto(event): void{
      this.fotoSeleccinada = event.target.files[0];
      console.info(this.fotoSeleccinada);

      if(this.fotoSeleccinada.type.indexOf('image') < 0){
        this.fotoSeleccinada = null;
        Swal.fire("Error al seleccionar la foto:", "El archivo debe de ser del tipo imagen", "error");
      }
    }

    public crear(): void{
      if(!this.fotoSeleccinada){
        super.crear();
      } else {
        this.service.crearConFoto(this.model, this.fotoSeleccinada)
          .subscribe(alumno => {
            console.log(alumno);
            Swal.fire('Nuevo:', `${this.nombreModel} ${alumno.nombre} creado con éxito`, 'success');
            this.router.navigate([this.redirect]);
          },
          err => {
            if(err.status === 400){
              this.error = err.error;
              console.log(this.error);
            }
          });
      }
    }

    public editar(): void{
      if(!this.fotoSeleccinada){
        super.editar();
      } else {
        this.service.editarConFoto(this.model, this.fotoSeleccinada)
          .subscribe(alumno => {
            console.log(alumno);
            Swal.fire('Modificado:', `${this.nombreModel} ${alumno.nombre} actualizado con éxito`, 'success');
            this.router.navigate([this.redirect]);
          },
          err => {
            if(err.status === 400){
              this.error = err.error;
              console.log(this.error);
            }
          });
      }
    }

}
