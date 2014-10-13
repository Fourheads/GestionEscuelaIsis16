package dom.calificacion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.planEstudio.Materia;
import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

@Named("Cargar Notas")
@DomainService(menuOrder = "80")
public class CargarNotaService {
	
	  @Hidden
		public String getId() {
	        return "cargarNotas";
	    }

	    public String title(){
	    	return "Cargar Notas";
	    }
	    
	    public String iconName() {
	        return "SimpleObject";
	    }
	    @Named("Por Curso")
	    public CargarNotaView calificarPorCurso(@Named("Periodo: ") Periodo inPeriodo,
	    										@Named("Ciclo: ") Calificaciones inCalificacion,												 
	    										@Named("Curso: ") Curso inCurso,
	    										@Named("Materia: ") MateriaDelCurso inMateria){
	    	
	    	Memento newMemento = mementoService.create();
	    	//titulo, periodo, anio, division, materia, plan, alumnoIndex
	    	newMemento.set("titulo", "Cargar Nota");
	    	newMemento.set("periodo", inPeriodo.getNombre());
	    	newMemento.set("anio", inCurso.getAnio().getAnioNumero());
	    	newMemento.set("division", inCurso.getDivision());
	    	newMemento.set("materia", inMateria.getMateria().getNombre());
	    	newMemento.set("plan", inCurso.getAnio().getPlan().getDescripcion());
	    	newMemento.set("indiceAlumno", 0);
	    	
	    	//titulo, periodo, anio, division, materia, plan, alumnoIndex
	    		    	
	    	final CargarNotaView newView = container.newViewModelInstance(CargarNotaView.class, newMemento.asString());
	    	
	    	return newView;
	    	
	    }
	    
	    public List<MateriaCalificacion> listMatCal(){
	    	return container.allInstances(MateriaCalificacion.class);
	    }
	    
	    
	    @javax.inject.Inject
		DomainObjectContainer container;
	    
	    @javax.inject.Inject
	    MateriaCalificacionRepositorio mcRepositorio;
	    @javax.inject.Inject
	    MementoService mementoService;
}
