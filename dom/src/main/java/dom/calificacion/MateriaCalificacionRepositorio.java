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
	
	@javax.inject.Inject
	private DomainObjectContainer container;
}
