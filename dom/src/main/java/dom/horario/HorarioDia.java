package dom.horario;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

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
import org.omg.CORBA.Object;

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
@ObjectType("HORARIO_DIA")
@Bookmarkable
@Bounded
public class HorarioDia implements Comparable<HorarioDia>{
	// {{ DiaDeLaSemana (property)
	private E_HorarioDiaSemana diaDeLaSemana;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public E_HorarioDiaSemana getDiaDeLaSemana() {
		return diaDeLaSemana;
	}

	public void setDiaDeLaSemana(final E_HorarioDiaSemana diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
	}

	// }}

	// HorarioHoraList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "HorarioHora", dependentElement = "true")
	@Join
	private List<HorarioHora> horarioHoraList = new ArrayList<HorarioHora>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<HorarioHora> getHorarioHoraList() {
		return horarioHoraList;
	}

	public void setHorarioHoraList(final List<HorarioHora> horarioHoraList) {
		this.horarioHoraList = horarioHoraList;
	}
	// end region HorarioHoraList (Collection)

	
	// {{ HorarioCurso (property)
	private HorarioCurso horarioCurso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioCurso getHorarioCurso() {
		return horarioCurso;
	}

	public void setHorarioCurso(final HorarioCurso horarioCurso) {
		this.horarioCurso = horarioCurso;
	}
	// }}

	@Override
	public int compareTo(HorarioDia o) {
		return ObjectContracts.compare(this, o, "diaDeLaSemana");
	}


}
