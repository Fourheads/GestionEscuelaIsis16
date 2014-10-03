package dom.horario;

import dom.simple.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
// @javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name =
// "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
// + "FROM dom.planEstudio.Anio "
// + "WHERE this.plan.descripcion == :descripcion") })
@ObjectType("HORARIO_CURSO")
@Bookmarkable
@Bounded
public class HorarioCurso implements Comparable<HorarioCurso> {

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

	// HorarioDiaList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "horarioCurso", dependentElement = "true")
	@Join
	private SortedSet<HorarioDia> horarioDiaList = new TreeSet<HorarioDia>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public SortedSet<HorarioDia> getHorarioDiaList() {
		return horarioDiaList;
	}

	public void setHorarioDiaList(final SortedSet<HorarioDia> horarioDiaList) {
		this.horarioDiaList = horarioDiaList;
	}

	// end region HorarioDiaList (Collection)

	@Override
	public int compareTo(HorarioCurso o) {
		return ObjectContracts.compare(this, o, "curso");
	}

	public String title() {
		return "Horario de " + getCurso().title();
	}

}
