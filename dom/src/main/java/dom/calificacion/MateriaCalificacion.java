package dom.calificacion;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.util.ObjectContracts;

import dom.planEstudio.Materia;
import dom.simple.Alumno;
import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorAlumno", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.MateriaCalificacion"
					+" WHERE this.alumno.dni == :dni "),
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorCursoPorMateria", 
			language = "JDOQL", 
			value = "SELECT FROM dom.calificacion.MateriaCalificacion " 
					+ "WHERE "
					+ "this.materiaDelCurso.curso.anio.anioNumero == :anio " 
					+ "&& this.materiaDelCurso.curso.anio.plan.descripcion == :plan " 
					+ "&& this.materiaDelCurso.curso.division == :division " 
					+ "&& this.materiaDelCurso.materia.nombre == :materia"
					),
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorMateria", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.MateriaCalificacion" +
					" WHERE this.materia.materia.nombre == :materia "),
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorCursoPorMateriaPorPeriodo", 
			language = "JDOQL", 
			value = "SELECT FROM dom.calificacion.MateriaCalificacion " 
					+ "WHERE "
					+ "this.materiaDelCurso.curso.anio.anioNumero == :anio " 
					+ "&& this.materiaDelCurso.curso.anio.plan.descripcion == :plan " 
					+ "&& this.materiaDelCurso.curso.division == :division " 
					+ "&& this.materiaDelCurso.materia.nombre == :materia " 
					+ "&& this.alumnoCalificacion.periodo.nombre == :periodo"),
				
})

@Bookmarkable
@MemberGroupLayout(columnSpans = {12,0,0,12})
public class MateriaCalificacion implements Comparable<MateriaCalificacion>{
	
	
	// {{ AlumnoCalificacion (property)
	private AlumnoCalificacion alumnoCalificacion;
	
	@Hidden(where = Where.ALL_TABLES)
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public AlumnoCalificacion getAlumnoCalificacion() {
		return alumnoCalificacion;
	}

	public void setAlumnoCalificacion(final AlumnoCalificacion alumnoCalificacion) {
		this.alumnoCalificacion = alumnoCalificacion;
	}
	// }}


	
	// {{ Materia (property)
	private MateriaDelCurso materiaDelCurso;
	
	//@Hidden(where = Where.ALL_EXCEPT_STANDALONE_TABLES)
	@Named("Materia")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public MateriaDelCurso getMateriaDelCurso() {
		return materiaDelCurso;
	}

	public void setMateriaDelCurso(final MateriaDelCurso materia) {
		this.materiaDelCurso = materia;		
	}
	// }}
	
	// {{ Nota (property)
	private int nota;

	@Named("Nota")
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public int getNota() {
		return nota;
	}

	public void setNota(final int nota) {
		this.nota = nota;
	}
	// }}
	
	// {{ Alumno (property)
	private Alumno alumno;

	@Named("Alumno")
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}
	// }}
	
	// {{ Observacion (property)
	private String observacion;

	@Named("Observación")
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(final String observacion) {
		this.observacion = observacion;
	}
	// }}

	
	public String title(){
		//return alumno.title() + "- Materia: " + materiaDelCurso.getMateria().getNombre() + "- Nota: " + String.valueOf(nota);
		return getAlumno().title();
	}

	@Override
	public int compareTo(MateriaCalificacion o) {
		return ObjectContracts.compare(this, o, "materia");
	}


	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	Curso curso;
}
