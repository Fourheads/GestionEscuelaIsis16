package dom.simple;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

import dom.planEstudio.Materia;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

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

//	// {{ Profesor (property)
//	private Profesor profesor;
//
//	@MemberOrder(sequence = "1")
//	@Column(allowsNull = "true")
//	public Profesor getProfesor() {
//		return profesor;
//	}
//
//	public void setProfesor(final Profesor profesor) {
//		this.profesor = profesor;
//	}
//	// }}
	
	// {{ Curso (property)
	private Curso curso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}



	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return getMateria().getNombre() + " de " + getMateria().getAnio() + "°";
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

}
