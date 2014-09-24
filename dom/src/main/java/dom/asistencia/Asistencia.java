package dom.asistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "todosLosEsquemasAlfabeticamente", 
			language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.Asistencia"
					+" order by this.descripcion asc")
				
})

public class Asistencia {

	// {{ Descripcion (property)
	private String descripcion;

	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	@Title
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	// }}

	// {{ AsistenciasDia (Collection)
	
	@Join
	@Element(mappedBy = "asistencia", dependent = "True")
	private List<AsistenciaDia> asistenciasDiaList = new ArrayList<AsistenciaDia>();
	
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<AsistenciaDia> getAsistenciasDiaList() {
		return asistenciasDiaList;
	}

	public void setAsistenciasDiaList(final List<AsistenciaDia> asistenciasDia) {
		this.asistenciasDiaList = asistenciasDia;
	}

	// }}

	

}
