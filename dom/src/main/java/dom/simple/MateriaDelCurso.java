package dom.simple;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Where;

import dom.planEstudio.Materia;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "MateriaDelCursoDeUnCurso", language = "JDOQL", value = "SELECT FROM dom.simple.MateriaDelCurso "
			+ "WHERE this.curso.anio.plan.descripcion == :plan "
			+ "&& this.curso.anio.anioNumero == :anio"
			+ "&& this.curso.division == :division")})

@Bookmarkable
@Bounded
public class MateriaDelCurso {
	
	// {{ Materia (property)
	private Materia materia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Materia getMateria() {
		return materia;
	}

	public void setMateria(final Materia materia) {
		this.materia = materia;
	}
	// }}


	
	// {{ Curso (property)
	private Curso curso;
	@Hidden(where = Where.ALL_TABLES)
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}

	// {{ Profesor (property)
	private Personal profesor;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public Personal getProfesor() {
		return profesor;
	}

	public void setProfesor(final Personal profesor) {
		this.profesor = profesor;
	}
	// }}

	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return getMateria().getNombre() + " de " 
				+ getMateria().getAnio().getAnioNumero() + "° " 
				+ "'" + getCurso().getDivision() + "' "
				+ "(" + getMateria().getAnio().getPlan().getDescripcion() + ")";
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

}
