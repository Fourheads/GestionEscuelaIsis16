package dom.calificacion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

@Named("Reportes")
@DomainService(menuOrder = "90")
public class AlumnoMateriasService {
	
	 	@Hidden
		public String getId() {
	        return "alumnoMaterias";
	    }

	    public String title(){
	    	return "Calificaciones Por Alumno";
	    }
	    
	    public String iconName() {
	        return "SimpleObject";
	    }
	    
	    @Named("Calificaciones Por Alumno")
	    //@MemberOrder(name = "Alumnos", sequence = "7")
	    public AlumnoMateriasView seleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
	    											final @Named("Periodo") Periodo periodo,
	    											final @Named("Alumno") AlumnoCalificacion alumno){
	    	
	    	Memento newMemento = mementoService.create();
	    	
	    	//titulo, alumno, dni, ciclo, periodo, curso, division, turno
	    	newMemento.set("titulo", "Materias por periodo" );
	    	newMemento.set("alumno", alumno.getAlumno().title());
	    	newMemento.set("dni", alumno.getAlumno().getDni());
	    	newMemento.set("ciclo", ciclo.getCicloCalificacion());
	    	newMemento.set("periodo", periodo.getNombre());
	    	newMemento.set("curso", alumno.getAlumno().getCurso().getAnio().getAnioNumero());
	    	newMemento.set("division", alumno.getAlumno().getCurso().getDivision());
	    	newMemento.set("turno", alumno.getAlumno().getCurso().getTurno().toString());
	    	
	    	//final CargarNotaView newView = container.newViewModelInstance(CargarNotaView.class, newMemento.asString());
	    	final AlumnoMateriasView newView = container.newViewModelInstance(AlumnoMateriasView.class, newMemento.asString());
	    	return newView;
	    }
	    
	    //Choices CICLO
	    public List<Calificaciones> choices0SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo) {

			List<Calificaciones> listCalificaciones = califRepositorio.ciclosConPeriodo();
			if (listCalificaciones.isEmpty()) {
				
				return null;
			}
			return listCalificaciones;
		}  
	   
	    
	    //Choices PERIODO
	    public List<Periodo> choices1SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
				final @Named("Periodo") Periodo periodo,
				final @Named("Alumno") AlumnoCalificacion alumno){
	    	if(ciclo != null){	    		
	    		if(!ciclo.getPeriodos().isEmpty()){
		    		return ciclo.getPeriodos();
		    	}
	    	}
	    	return null;   	
	    	
	    }	   
	    
	    //Choices ALUMNOCALIFICACION
	    public List<AlumnoCalificacion> choices2SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
				final @Named("Periodo") Periodo periodo,
				final @Named("Alumno") AlumnoCalificacion alumno){
	    	if(periodo != null){
	    		if(!aluCal.listPorPeriodo(periodo).isEmpty()){
	    			return aluCal.listPorPeriodo(periodo);	
	    		}
	    	}
	    	return null;
	    }
	    
	    
	    
	    @javax.inject.Inject
	    MementoService mementoService;
	    
	    @javax.inject.Inject
		DomainObjectContainer container;
	    
	    @javax.inject.Inject
	    AlumnoCalificacionRepositorio	aluCal;
	    
	    @javax.inject.Inject
	    CalificacionRepositorio califRepositorio;
}
