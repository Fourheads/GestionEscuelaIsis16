package dom.simple;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;

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
	public List<MateriaDelCurso> listarMateriaDelCursoParaUnCurso(Curso curso){
		
		return container.allMatches(new QueryDefault<MateriaDelCurso>(MateriaDelCurso.class,
				"MateriaDelCursoDeUnCurso", 
				"plan", curso.getAnio().getPlan().getDescripcion(), 
				"anio", curso.getAnio().getAnioNumero(),
				"division", curso.getDivision()));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
}
