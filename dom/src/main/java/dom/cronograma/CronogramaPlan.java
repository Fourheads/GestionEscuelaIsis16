package dom.cronograma;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

import dom.planEstudio.Plan;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "x", language = "JDOQL", value = "SELECT "
		+ "FROM dom.cronograma.CronogramaPlan "
		+ "WHERE x == :x "
		+ "&& x == :x") })
@ObjectType("CRONOGRAMA_PLAN")
@Bookmarkable
/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class CronogramaPlan implements Comparable<CronogramaPlan> {

	
	
	// {{ Plan (property)
	private Plan plan;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(final Plan plan) {
		this.plan = plan;
	}

	// }}

	// {{ CronogramaAnioList (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "true")
	private SortedSet<CronogramaAnio> cronogramaAnioList = new TreeSet<CronogramaAnio>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "2")
	public SortedSet<CronogramaAnio> getCronogramaAnioList() {
		return cronogramaAnioList;
	}

	public void setCronogramaAnioList(
			final SortedSet<CronogramaAnio> cronogramaAnioList) {
		this.cronogramaAnioList = cronogramaAnioList;
	}

	// }} (end region)
	// //////////////////////////////////////

	@Override
	public int compareTo(CronogramaPlan other) {
		return ObjectContracts.compare(this, other, "plan");

	}

}
