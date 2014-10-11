package dom.libroDiario;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.joda.time.LocalDate;

import dom.libroDiario.HoraCatedra;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("ENTRADA_LIBRO_DIARIO")
@Bookmarkable
@Bounded
public class EntradaLibroDiario {
	
	// {{ Fecha (property)
	private LocalDate Fecha;

	@MemberOrder(sequence = "1")
	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(final LocalDate Fecha) {
		this.Fecha = Fecha;
	}
	// }}

	// {{ HoraCatedra (Collection)
	@Join
	@Element(dependent = "true")
	private SortedSet<HoraCatedra> horacatedra = new TreeSet<HoraCatedra>();

	@MemberOrder(sequence = "1")
	public SortedSet<HoraCatedra> getHoraCatedra() {
		return horacatedra;
	}

	public void setHoraCatedra(final SortedSet<HoraCatedra> horacatedra) {
		this.horacatedra = horacatedra;
	}

	public void asignarHoracatedra(HoraCatedra horacate) {
		this.horacatedra.add(horacate);
		
	}
	
	
	// }}


	
	
}
