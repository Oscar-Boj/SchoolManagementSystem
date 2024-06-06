import Modelos.Estudiante;
import Modelos.Curso;
import Modelos.GestorAcademico;
import Excepciones.EstudianteYaInscritoException;
import Excepciones.EstudianteNoInscritoEnCursoException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws EstudianteYaInscritoException {
        Scanner scanner = new Scanner(System.in);
        GestorAcademico gestor = new GestorAcademico();

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante(1, "Juan", "Perez", "01/01/2000", Estudiante.Estado.MATRICULADO);
        Estudiante estudiante2 = new Estudiante(2, "Maria", "Gomez", "02/02/2001", Estudiante.Estado.MATRICULADO);

        // Crear cursos
        Curso curso1 = new Curso(1, "Matemáticas", "Curso de matemáticas básicas", 3, "1.0");
        Curso curso2 = new Curso(2, "Historia", "Curso de historia mundial", 4, "1.0");

        // Matricular estudiantes
        gestor.matricularEstudiante(estudiante1);
        gestor.matricularEstudiante(estudiante2);

        // Agregar cursos
        gestor.agregarCurso(curso1);
        gestor.agregarCurso(curso2);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Inscribir estudiante en curso");
            System.out.println("2. Desinscribir estudiante de curso");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.println("Ingrese el ID del estudiante:");
                int idEstudiante = scanner.nextInt();
                System.out.println("Ingrese el ID del curso:");
                int idCurso = scanner.nextInt();

                try {
                    Estudiante estudiante = gestor.getEstudianteById(idEstudiante);
                    gestor.inscribirEstudianteCurso(estudiante, idCurso);
                    System.out.println("Estudiante inscrito correctamente.");
                } catch (EstudianteYaInscritoException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 2) {
                System.out.println("Ingrese el ID del estudiante:");
                int idEstudiante = scanner.nextInt();
                System.out.println("Ingrese el ID del curso:");
                int idCurso = scanner.nextInt();

                try {
                    gestor.desinscribirEstudianteCurso(idEstudiante, idCurso);
                    System.out.println("Estudiante desinscrito correctamente.");
                } catch (EstudianteNoInscritoEnCursoException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 3) {
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}