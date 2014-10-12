package dom.calificacion;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dom.simple.Alumno;
import dom.simple.Curso;

@PersistenceCapable
@Bounded
public class Periodo {	
	
	/*		
	// {{ AlumnoCalificaciones (property)
	private SortedSet<AlumnoCalificacion> alumnoCalificaciones = new TreeSet<AlumnoCalificacion>();

	@MemberOrder(sequence = "1")
	public SortedSet<AlumnoCalificacion> getAlumnoCalificaciones() {
		return alumnoCalificaciones;
	}

	public void setAlumnoCalificaciones(final SortedSet<AlumnoCalificacion> alumnoCalificaciones) {
		this.alumnoCalificaciones = alumnoCalificaciones;
	}
	// }}
	 */
	
	// {{ Nombre (property)
	private String nombre;
	
	@Named("Periodo")	
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	// }}

	// {{ FechaInicio (property)
	private LocalDate fechaInicio;

	@Named("Inicio")	
	@MemberOrder(sequence = "1.1")
	@Column(allowsNull = "true")
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(final LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	// }}


	// {{ FechaFinal (property)
	private LocalDate fechaFinal;

	@Named("Cierre")	
	@MemberOrder(sequence = "1.2")
	@Column(allowsNull = "true")
	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(final LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	// }}

	// {{ Calificaciones (property)
	private Calificaciones calificaciones;
	
	@Hidden
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Calificaciones getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(final Calificaciones calificaciones) {
		this.calificaciones = calificaciones;
	}
	// }}
	
	public String title(){
		return getNombre();
	}

	@javax.inject.Inject
	DomainObjectContainer container;


}
