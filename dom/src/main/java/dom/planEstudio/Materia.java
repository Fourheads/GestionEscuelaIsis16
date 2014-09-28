package dom.planEstudio;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Title;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listarMateriasDeUnAnio", language = "JDOQL", value = "SELECT "
		+ "FROM dom.planEstudio.Materia "
		+ "WHERE this.anio.anioNumero == :anio " 
		+ "&& this.anio.plan.descripcion == :plan") 
})
@ObjectType("MATERIA")
@Bookmarkable
public class Materia {
	
	// {{ Anio (property)
	private Anio anio;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Anio getAnio() {
		return anio;
	}

	public void setAnio(final Anio anio) {
		this.anio = anio;
	}
	// }}


	// {{ Nombre (property)
	private String nombre;
	@Title
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	// }}


	// {{ Programa (property)
	private String programa;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getPrograma() {
		return programa;
	}

	public void setPrograma(final String programa) {
		this.programa = programa;
	}
	// }}



}
