package dom.calificacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;


@DomainService(menuOrder = "100", repositoryFor = Calificaciones.class)
@Named("Calificaciones")
public class CalificacionRepositorio {
	
	
	@Named("Crear Calificacion")
	public Calificaciones createCalificacion(final @Named("Ingrese el ciclo: ") int inCiclo){
		
		final Calificaciones newCalificacion = container.newTransientInstance(Calificaciones.class);
		newCalificacion.setCicloCalificacion(inCiclo);
		
		container.persistIfNotAlready(newCalificacion);
		
		return newCalificacion;
	}	
	
	@Named("Buscar por Ciclo")
	public List<Calificaciones> listByCiclo(@Named("Ciclo: ")int ciclo){
		return container.allMatches(
				new QueryDefault<Calificaciones>(Calificaciones.class, "findByCiclo","ciclo", ciclo));
	}
	
	@Hidden
	@Named("Ciclo lectivo")
	public List<Calificaciones> listCalificaciones(){
		return container.allInstances(Calificaciones.class);
	}
	
		
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	dom.simple.CursoRepositorio cursoRepo;
}

