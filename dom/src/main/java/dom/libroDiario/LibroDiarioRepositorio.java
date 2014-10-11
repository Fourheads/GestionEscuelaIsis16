package dom.libroDiario;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.query.QueryDefault;

import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

@DomainService(menuOrder = "100")
public class LibroDiarioRepositorio {
	
	
	@Hidden
	public void crearLibroDiario(Curso curso) {
		
		LibroDiario libroDiario = container.newTransientInstance(LibroDiario.class);
		libroDiario.setCurso(curso);
		libroDiario.setMateriaDelLibroDiarioList(materiaDelLibroDiarioRepositorio.crearListaMateriaDelLibroDiario(curso));
		container.persistIfNotAlready(libroDiario);
		
	}

	// {{ mostrarLibroDiarioDelCurso (action)
	@MemberOrder(sequence = "1")
	public LibroDiario mostrarLibroDiarioDelCurso(final Curso curso) {
		
		return container.firstMatch(new QueryDefault<LibroDiario>(LibroDiario.class,
				"LibroDiarioDeUnCurso", 
				"plan", curso.getAnio().getPlan().getDescripcion(), 
				"anio", curso.getAnio().getAnioNumero(),
				"division", curso.getDivision()));
	}
	// }}


	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MateriaDelLibroDiarioRepositorio materiaDelLibroDiarioRepositorio;

}
