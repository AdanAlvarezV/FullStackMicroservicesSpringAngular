import { Component, OnInit, ViewChild } from '@angular/core';
import Swal from 'sweetalert2';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

export abstract class CommonListarComponent<E, S> implements OnInit {

  titulo:string = 'Listado de alumnos';
  alumnos: Alumno[] = [];

  totalRegistros: number = 0;
  paginaActual: number = 0;
  totalPorPagina: number = 4;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: AlumnoService) { }

  ngOnInit(): void {
    this.calcularRangos();
  }
  
  paginar(event:PageEvent):void{
    this.paginaActual = event.pageIndex;
    this.totalPorPagina = event.pageSize;
    this.calcularRangos();
  }

  private calcularRangos(){    
    this.service.listarPaginas(this.paginaActual.toString(), this.totalPorPagina.toString())
      .subscribe(p => {
        this.alumnos = p.content as Alumno[];
        this.totalRegistros = p.totlaElements as number;
        this.paginator._intl.itemsPerPageLabel = "Regitros porpágina:";
      });
  }

  public eliminar(alumno: Alumno): void{
    Swal.fire({
      title: 'Cuidado:',
      text: `¿Seguro que desea eliminar a ${alumno.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.eliminar(alumno.id).subscribe(() => {
          //this.alumnos = this.alumnos.filter(a => a !== alumno);
          this.calcularRangos();
          Swal.fire('Eliminado:', `Alumno ${alumno.nombre} eliminado con éxito`, 'success');
        });
      }
    });
  }

}
