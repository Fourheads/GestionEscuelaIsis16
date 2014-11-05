package dom.calificacion;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
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
	    public List<Calificaciones> choices0SeleccionarAlumno() {
	    	List<Calificaciones> listCalificaciones = container.allInstances(Calificaciones.class);
	    	if(listCalificaciones.isEmpty()){
	    		container.warnUser("Debe crear al menos un ciclo lectivo con sus períodos de evaluación");
	    		return listCalificaciones;
	    	}
	    	return listCalificaciones;
		}
	    
	   /* public Calificaciones default0SeleccionarAlumno() {
			if (choices0SeleccionarAlumno().isEmpty()) {
				return null;
			}
			return choices0SeleccionarAlumno().get(0);
		}*/
	    
	    //Choices PERIODO
	    /*public List<Periodo> choices1SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo){
	    	if(ciclo.getPeriodos().isEmpty()){
	    		return null;
	    	}
	    	return ciclo.getPeriodos();
	    }*/
	    
	   /* public Periodo default1SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
				final @Named("Periodo") Periodo periodo,
				final @Named(" ") AlumnoCalificacion alumno) {
			if (choices1SeleccionarAlumno(ciclo, periodo, alumno).isEmpty()) {
				container.warnUser("En periodo");
				return null;
			}
			return choices1SeleccionarAlumno(ciclo, periodo, alumno).get(0);
		}*/
	    
	    //Choices ALUMNOCALIFICACION
	   /* public List<AlumnoCalificacion> choices2SeleccionarAlumno(final @Named("Periodo") Periodo periodo){
	    	return aluCal.listPorPeriodo(periodo);
	    }*/
	    
	    
	    
	    @javax.inject.Inject
	    MementoService mementoService;
	    
	    @javax.inject.Inject
		DomainObjectContainer container;
	    
	    @javax.inject.Inject
	    AlumnoCalificacionRepositorio	aluCal;
}
