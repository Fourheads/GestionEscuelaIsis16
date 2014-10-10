package dom.libroDiario;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("MATERIA_LIBRO_DIARIO")
@Bookmarkable
@Bounded
public class MateriaDelLibroDiario {
	
	// {{ Librodiario (property)
	private LibroDiario libroDiario;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(final LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}
	// }}



}
