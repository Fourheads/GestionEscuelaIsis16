package dom.calificacion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;

public class MateriaCalificacionRepositorio {
	
	//Hacer query de materias por curso
		
	public List<MateriaCalificacion> materiaCalificacionPorAlumno(final int inDni){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorAlumno",
				"dni", inDni));
	}
	
	public List<MateriaCalificacion> listAll(){
		return container.allInstances(MateriaCalificacion.class);
	}
	
	public List<MateriaCalificacion> listMateriaCalificacionPorCursoPorMateria(int inAnio, 
										String inDivision, String inPlan, String inMateria){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorCursoPorMateria",
				"anio", inAnio, "plan", inPlan, "division", inDivision, "materia", inMateria));
	}
	
	public List<MateriaCalificacion> listMateriCalificacionPorMateria(String materia){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorMateria",
				"materia", materia));
	}
	
	@javax.inject.Inject
	private DomainObjectContainer container;
}
