package dom.horario;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;

import dom.simple.Curso;

@DomainService(repositoryFor = HorarioCurso.class)
public class HorarioCursoRepositorio {
	
	// {{ actionName (action)
	@Hidden
	public HorarioCurso crearHorarioCurso (final Curso curso) {
		
		HorarioCurso horarioCurso = new HorarioCurso();
		horarioCurso.setCurso(curso);
		horarioCurso.setHorarioDiaList(horarioDiaRepositorio.crearHorarioDiaList());
				
		return horarioCurso;
	}
	// }}


	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	HorarioDiaRepositorio horarioDiaRepositorio;
}
