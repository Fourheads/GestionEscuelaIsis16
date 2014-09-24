package dom.asistencia;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "BuscarAsistenciDiaPorFechaParaUnEsquema", 
			language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.AsistenciaDia"
					+" WHERE this.fecha == :fecha "
					+" && this.asistencia.descripcion == :descripcion")
				
})

@Bookmarkable
@MemberGroupLayout(columnSpans = {12,0,0,12})
public class AsistenciaDia {

	// {{ Fecha (property)
	private Date fecha;
	@Disabled
	@MemberOrder(sequence = "1")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	// }}

	// {{ Asistencia (property)
	private Asistencia asistencia;

	@Named("Pertenece a:")
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Asistencia getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(final Asistencia asistencia) {
		this.asistencia = asistencia;
	}
	// }}


	
	// {{ AsistenciaAlumnos (Collection)
	@Join
	@Persistent(mappedBy = "asistenciaDia", dependentElement = "false")
	private List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();
	@ActionSemantics(Of.SAFE)
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	@Disabled
	public List<AsistenciaAlumno> getAsistenciaAlumnoList() {
		return asistenciaAlumnoList;
	}

	public void setAsistenciaAlumnoList(final List<AsistenciaAlumno> alumnoList) {
		this.asistenciaAlumnoList = alumnoList;
	}

	// }}


	public String title() {

		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String titulo = formatter.format(getFecha());

		return titulo;
	}

	
	
	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

}
