package Modelos;

import Interfaces.ServiciosAcademicosI;
import Excepciones.EstudianteYaInscritoException;
import Excepciones.EstudianteNoInscritoEnCursoException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GestorAcademico implements ServiciosAcademicosI{
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones;

    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        if (!cursos.stream().anyMatch(curso -> curso.getId() == idCurso)) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        if (!inscripciones.containsKey(idCurso)) {
            inscripciones.put(idCurso, new ArrayList<>());
        }
        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en este curso.");
        }
        inscritos.add(estudiante);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        if (!cursos.stream().anyMatch(curso -> curso.getId() == idCurso)) {
            throw new IllegalArgumentException("Curso no encontrado");
        }
        if (!inscripciones.containsKey(idCurso)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en este curso.");
        }
        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        Estudiante estudiante = estudiantes.stream().filter(e -> e.getId() == idEstudiante).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        if (!inscritos.contains(estudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en este curso.");
        }
        inscritos.remove(estudiante);
    }

    public Estudiante getEstudianteById(int idEstudiante) {
        return estudiantes.stream().filter(e -> e.getId() == idEstudiante).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
    }
}
