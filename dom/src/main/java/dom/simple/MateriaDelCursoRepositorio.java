package dom.simple;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Programmatic;

import dom.planEstudio.Materia;
@DomainService
public class MateriaDelCursoRepositorio {
	
	@Programmatic
	public void crearMateriaDelCurso (Curso curso, Materia materia){
		MateriaDelCurso materiaDelCurso = new MateriaDelCurso();
		materiaDelCurso.setMateria(materia);
		curso.getMateriaDelCursoList().add(materiaDelCurso);
	}

}
