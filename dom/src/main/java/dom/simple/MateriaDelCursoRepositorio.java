package dom.simple;

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Programmatic;

import dom.planEstudio.Materia;
@DomainService
public class MateriaDelCursoRepositorio {
	
	@Hidden
	public void crearMateriaDelCurso (Curso curso, Materia materia){
		MateriaDelCurso materiaDelCurso = new MateriaDelCurso();
		materiaDelCurso.setMateria(materia);
		curso.getMateriaDelCursoList().add(materiaDelCurso);
	}
	
	@Hidden
	public void asignarProfesorAMateriaDelCurso(MateriaDelCurso materia, Personal profesor){
		
		materia.setProfesor(profesor);
	}
	
	@Hidden
	public List<MateriaDelCurso> listarMateriaDelCursoParaUnCurso(){
		
		return null;
	}

}
