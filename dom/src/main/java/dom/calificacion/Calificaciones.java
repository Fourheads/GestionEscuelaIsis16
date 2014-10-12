package dom.calificacion;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.Where;
import org.joda.time.LocalDate;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "findByCiclo", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.Calificaciones"
					+" WHERE this.cicloCalificacion == :ciclo ")				
})

@Bookmarkable
@Bounded
public class Calificaciones {
	
	//Crear Período
//	@MemberOrder(sequence = "1")
//	@Named("Nueva instancia de Calificacion")
//	public Calificaciones createPeriodo(final @Named("Nombre: ") String inNombre,
//								 final @Named("Inicio: ") LocalDate inFechaI,
//								 final @Named("Cierre: ") LocalDate inFechaF){
//				
//		final Periodo newPeriodo = new Periodo();
//		List<AlumnoCalificacion> alumnosCalificacion = new ArrayList<AlumnoCalificacion>();
//		
//		newPeriodo.setNombre(inNombre.toUpperCase());
//		newPeriodo.setFechaInicio(inFechaI);
//		newPeriodo.setFechaFinal(inFechaF);
//		newPeriodo.setListAlumnoCalificacion(alumnosCalificacion);
//		
//		this.getPeriodos().add(newPeriodo);
//		//agregarPeriodo(newPeriodo);		
//		return this;
//	}

	// Periodos (Collection)
	// //////////////////////////////////////////
	
	@Persistent(mappedBy = "calificaciones", dependentElement = "true")
	@Join
	private List<Periodo> periodos = new ArrayList<Periodo>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(final List<Periodo> periodos) {
		this.periodos = periodos;
	}
	// end region Periodos (Collection)

	
	// {{ CicloCalificacion (property)
	private int cicloCalificacion;
	
	@Hidden(where = Where.OBJECT_FORMS)
	@Named("Ciclo")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public int getCicloCalificacion() {
		return cicloCalificacion;
	}

	public void setCicloCalificacion(final int cicloCalificacion) {
		this.cicloCalificacion = cicloCalificacion;
	}
	// }}
	
	
	public String title(){
		return "Ciclo Lectivo " + String.valueOf(this.cicloCalificacion);
	}
		
	@Hidden
	public void agregarPeriodo(Periodo inPeriodo){
		this.periodos.add(inPeriodo);
	}

	@javax.inject.Inject
	DomainObjectContainer container;
		
}
