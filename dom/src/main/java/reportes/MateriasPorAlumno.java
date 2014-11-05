package reportes;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

import dom.calificacion.AlumnoCalificacion;
import dom.calificacion.AlumnoCalificacionRepositorio;
import dom.calificacion.MateriaCalificacion;


public class MateriasPorAlumno {
	
	// {{ Profesor (property)
	private String profesor;

	@MemberOrder(sequence = "1")
	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(final String profesor) {
		this.profesor = profesor;
	}
	// }}

		
	// {{ Alumno (property)
	private String alumno;

	@MemberOrder(sequence = "1")
	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(final String alumno) {
		this.alumno = alumno;
	}
	// }}

	// {{ Dni (property)
	private String dni;

	@MemberOrder(sequence = "1")
	public String getDni() {
		return dni;
	}

	public void setDni(final String dni) {
		this.dni = dni;
	}
	// }}

	// {{ Ciclo (property)
	private String ciclo;

	@MemberOrder(sequence = "1")
	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(final String ciclo) {
		this.ciclo = ciclo;
	}
	// }}

	// {{ Periodo (property)
	private String periodo;

	@MemberOrder(sequence = "1")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(final String periodo) {
		this.periodo = periodo;
	}
	// }}

	// {{ Curso (property)
	private String curso;

	@MemberOrder(sequence = "1")
	public String getCurso() {
		return curso;
	}

	public void setCurso(final String curso) {
		this.curso = curso;
	}
	// }}

	// {{ Turno (property)
	private String turno;

	@MemberOrder(sequence = "1")
	public String getTurno() {
		return turno;
	}

	public void setTurno(final String turno) {
		this.turno = turno;
	}
	// }}

	// {{ Materia (property)
	private String materia;

	@MemberOrder(sequence = "1")
	public String getMateria() {
		return materia;
	}

	public void setMateria(final String materia) {
		this.materia = materia;
	}
	// }}

	// {{ Nota (property)
	private String nota;

	@MemberOrder(sequence = "1")
	public String getNota() {
		return nota;
	}

	public void setNota(final String nota) {
		this.nota = nota;
	}
	// }}

	// {{ Observacion (property)
	private String observacion;

	@MemberOrder(sequence = "1")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(final String observacion) {
		this.observacion = observacion;
	}
	// }}

	
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluRepositorio;
	
	@javax.inject.Inject
	GenerarReporte reporte;

	
}
