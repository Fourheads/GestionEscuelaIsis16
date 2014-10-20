package dom.calificacion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;

public class AlumnoCalificacionRepositorio {

	public List<AlumnoCalificacion> alumnoCalificacionPorAlumnoPorPeriodo(final int inDni, final String periodo){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorAlumnoPorPeriodo",
				"dni", inDni, "periodo", periodo));
	}
	
	public List<AlumnoCalificacion> listPorPeriodoPorCursoPorMateria(final String inPeriodo, final String inPlan, final String inDivision,
																	final String inMateria, final int inAnio){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodoPorCursoPorMateria",
				"periodo", inPeriodo, "plan", inPlan, "anio", inAnio, "division", inDivision, "materia", inMateria));
	}
	
	public List<AlumnoCalificacion> listPorPeriodoPorCurso(final String inPeriodo, final int inAnio, final String inDivision){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodoPorCurso",
				"periodo", inPeriodo, "anio", inAnio, "division", inDivision));
	}
	
	@javax.inject.Inject
	private DomainObjectContainer container;
}
