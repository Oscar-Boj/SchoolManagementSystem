package Interfaces;

import Modelos.Estudiante;
import Modelos.Curso;
import Excepciones.EstudianteYaInscritoException;
import Excepciones.EstudianteNoInscritoEnCursoException;

public interface ServiciosAcademicosI {
    void matricularEstudiante(Estudiante estudiante);
    void agregarCurso(Curso curso);
    void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException;
    void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException;
}
