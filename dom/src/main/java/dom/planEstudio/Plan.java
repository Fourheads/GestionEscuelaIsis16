package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

import dom.horario.HorarioPlan;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listarPlanes", language = "JDOQL", value = "SELECT "
		+ "FROM dom.planEstudio.Plan "
		+ "order by this.descripcion asc") })


@ObjectType("PLAN")
@Bookmarkable
@Bounded
public class Plan implements Comparable<Plan>{

	// {{ Descripcion (property)
	private String descripcion;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	// AnioList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "plan", dependentElement = "true")
	@Join
	private SortedSet<Anio> aniolist = new TreeSet<Anio>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public SortedSet<Anio> getAnioList() {
		return aniolist;
	}

	public void setAnioList(final SortedSet<Anio> aniolist) {
		this.aniolist = aniolist;
	}

	// end region AnioList (Collection)
	// //////////////////////////////////////////
	
	// {{ HorarioPlan (property)
	private HorarioPlan horarioPlan;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioPlan getHorarioPlan() {
		return horarioPlan;
	}

	public void setHorarioPlan(final HorarioPlan horarioPlan) {
		this.horarioPlan = horarioPlan;
	}
	// }}


	
	
	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return getDescripcion();
	}

	@Override
	public int compareTo(Plan other) {
		// TODO Auto-generated method stub
		return ObjectContracts.compare(this, other, "descripcion");
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

}
