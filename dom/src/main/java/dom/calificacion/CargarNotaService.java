package dom.calificacion;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;

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
	    	
	    	int anio = inCurso.getAnio().getAnioNumero();
	    	String division = inCurso.getDivision();
	    	String periodo = inPeriodo.getNombre();
	    	String titulo = "Cargar Nota";
	    	String materia = inMateria.getMateria().getNombre();
	    	String plan = inCurso.getAnio().getPlan().getDescripcion();
	    	
	    	//titulo, periodo, anio, division, materia, plan, alumnoIndex
	    	
	    	String memento = titulo + "," + periodo + "," + String.valueOf(anio) + "," + division +
	    					"," + materia + "," + plan +"," +"0";
	    	
	    	final CargarNotaView newView = container.newViewModelInstance(CargarNotaView.class, memento);
	    	
	    	return newView;
	    	
	    }
	    
	    
	    @javax.inject.Inject
		DomainObjectContainer container;
}
