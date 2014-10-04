package dom.horario;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.simple.Curso;

@DomainService
@Hidden
public class HorarioCursoService {
	
	// {{ verHorarioDeCurso (action)
	@MemberOrder(sequence = "1")
	public HorarioCursoView verHorarioDeCurso(final Curso curso) {
		
		String plan = curso.getAnio().getPlan().getDescripcion();
		int anio = curso.getAnio().getAnioNumero();
		String division = curso.getDivision();
		
		Memento memento = mementoService.create();
		
		memento.set("plan", plan);
		memento.set("anio", anio);
		memento.set("division", division);
				
		return container.newViewModelInstance(HorarioCursoView.class, memento.asString()); 
		}
	// }}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;

}
