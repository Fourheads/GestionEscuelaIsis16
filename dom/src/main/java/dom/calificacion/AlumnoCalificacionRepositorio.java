package dom.calificacion;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;

public class AlumnoCalificacionRepositorio {

	public AlumnoCalificacion alumnoCalificacionPorAlumno(final int inDni){
		return container.firstMatch(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorAlumno",
				"dni", inDni));
	}
	
	@javax.inject.Inject
	private DomainObjectContainer container;
}
