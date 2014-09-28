package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;

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

import dom.simple.SimpleObject;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
		+ "FROM dom.planEstudio.Anio "
		+ "WHERE this.plan.descripcion == :descripcion") })
@ObjectType("ANIO")
@Bookmarkable
@Bounded
public class Anio {

	// {{ AnioNumero (property)
	private int anioNumero;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public int getAnioNumero() {
		return anioNumero;
	}

	public void setAnioNumero(final int anioNumero) {
		this.anioNumero = anioNumero;
	}

	// }}

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

	// MateriaList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "anio", dependentElement = "true")
	@Join
	private List<Materia> collectionName = new ArrayList<Materia>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<Materia> getMateriaList() {
		return collectionName;
	}

	public void setMateriaList(final List<Materia> collectionName) {
		this.collectionName = collectionName;
	}

	// end region MateriaList (Collection)
	
	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return getAnioNumero() + "°";
	}

	// end region Title (GUI)
	// //////////////////////////////////////////
	

	
	// region > compareTo
	// //////////////////////////////////////

	
	// endregion

}
