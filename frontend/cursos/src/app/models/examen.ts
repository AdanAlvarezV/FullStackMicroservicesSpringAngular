import { Asignatura } from "./asignatura";
import { Pregunta } from "./pregunta";

export class Examen {
    id: number;
    nombre: string;
    createAt: string;
    preguntas: Pregunta[] = [];
    asugnatura: Asignatura;
    respondido: boolean;
}