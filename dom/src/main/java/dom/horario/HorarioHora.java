package dom.horario;

import dom.simple.MateriaDelCurso;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

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
@ObjectType("HORARIO_HORA")
@Bookmarkable
@Bounded
public class HorarioHora {
	// {{ HorarioDia (property)
	private HorarioDia horarioDia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioDia getHorarioDia() {
		return horarioDia;
	}

	public void setHorarioDia(final HorarioDia horarioDia) {
		this.horarioDia = horarioDia;
	}

	// }}

	// {{ MateriaDelCurso (property)
	private MateriaDelCurso materiaDelCurso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public MateriaDelCurso getMateriaDelCurso() {
		return materiaDelCurso;
	}

	public void setMateriaDelCurso(final MateriaDelCurso materiaDelCurso) {
		this.materiaDelCurso = materiaDelCurso;
	}
	// }}
	
	// {{ HorarioHoraTipo (property)
	private E_HorarioHoraTipo horarioHoraTipo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public E_HorarioHoraTipo getHorarioHoraTipo() {
		return horarioHoraTipo;
	}

	public void setHorarioHoraTipo(final E_HorarioHoraTipo horarioHoraTipo) {
		this.horarioHoraTipo = horarioHoraTipo;
	}
	// }}

	// {{ HorarioPlanHora (property)
	private HorarioPlanHora horarioPlanHora;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioPlanHora getHorarioPlanHora() {
		return horarioPlanHora;
	}

	public void setHorarioPlanHora(final HorarioPlanHora horarioPlanHora) {
		this.horarioPlanHora = horarioPlanHora;
	}
	// }}



	
}
