package dom.libroDiario;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;

import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

@DomainService
public class LibroDiarioRepositorio {

	public void crearLibroDiario(Curso curso) {
		
		LibroDiario libroDiario = container.newTransientInstance(LibroDiario.class);
		libroDiario.setCurso(curso);
		
		for(MateriaDelCurso materiadelcurso: curso.getMateriaDelCursoList())
		{
			
		}
		
		container.persistIfNotAlready(libroDiario);
	}

	
	@javax.inject.Inject
	DomainObjectContainer container;

}
