package dom.calificacion;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.query.QueryDefault;

import dom.simple.Curso;

//@DomainService
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
	
	public List<AlumnoCalificacion> listAll(){
		return container.allInstances(AlumnoCalificacion.class);
	}
	
	public List<AlumnoCalificacion> listPorAlumno(int dni){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorAlumno", "dni", dni));
	}
	
	public List<AlumnoCalificacion> listPorPeriodo(Periodo inPeriodo){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodo", "periodo", inPeriodo.getNombre()));
	}
	
	public List<Curso> listCursoPorPeriodo(Periodo inPeriodo){
		List<Curso> listCurso = new ArrayList<Curso>();
		
		for(AlumnoCalificacion a: container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodo", "periodo", inPeriodo.getNombre()))){
			if(listCurso.isEmpty()){
				listCurso.add(a.getAlumno().getCurso());
			}else{				
				if(!(listCurso.contains(a.getAlumno().getCurso()))){
					listCurso.add(a.getAlumno().getCurso());				
				}
			}
		}
		
		return listCurso;
	}
	@javax.inject.Inject
	private DomainObjectContainer container;
}
