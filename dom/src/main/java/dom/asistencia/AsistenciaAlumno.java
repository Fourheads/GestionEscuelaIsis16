package dom.asistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.simple.Alumno;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "asistenciaAlumno_asistenciaDiaCurso", language = "JDOQL", value = "SELECT FROM dom.asistencia.AsistenciaAlumno"
				+ " WHERE this.alumno.curso.anio.anioNumero == :anio "
				+ "&& this.alumno.curso.division == :division "
				+ "&& this.asistenciaDia.fecha == :fecha "
				+ "&& this.asistenciaDia.asistencia.descripcion == :asistencia "
				+ "order by this.alumno.apellido asc, this.alumno.nombre asc"),

		@javax.jdo.annotations.Query(name = "asistenciaAlumno_ContarAsistencias", language = "JDOQL", value = "SELECT FROM dom.asistencia.AsistenciaAlumno "
				+ "WHERE this.asistenciaDia.asistencia.descripcion == :asistencia "
				+ "&& this.alumno.curso.anio.anioNumero == :anio "
				+ "&& this.alumno.curso.division == :division "
				+ "&& this.asistenciaDia.fecha >= :desde "
				+ "&& this.asistenciaDia.fecha <= :hasta "
				+ "&& this.alumno.dni == :dni ")

})
@Bounded
@MemberGroupLayout(columnSpans = { 6, 0, 0, 0 })
public class AsistenciaAlumno {

	// {{ AsistenciaDia (property)
	private AsistenciaDia asistenciaDia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public AsistenciaDia getAsistenciaDia() {
		return asistenciaDia;
	}

	public void setAsistenciaDia(final AsistenciaDia asistenciaDia) {
		this.asistenciaDia = asistenciaDia;
	}

	// }}

	// {{ Alumno (property)
	private Alumno alumno;

	@Disabled
	@MemberOrder(sequence = "1.5")
	@javax.jdo.annotations.Column(allowsNull = "false")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}

	// }}

	// {{ EstaPresente (property)
	private boolean estaPresente;

	@Disabled
	@MemberOrder(sequence = "2")
	@javax.jdo.annotations.Column(allowsNull = "false", defaultValue = "false")
	public boolean getEstaPresente() {
		return estaPresente;
	}

	public void setEstaPresente(final boolean estaPresente) {
		this.estaPresente = estaPresente;
	}

	// }}

	// {{ LlegoTarde (property)
	private boolean llegoTarde;

	@Disabled
	@MemberOrder(sequence = "3")
	@javax.jdo.annotations.Column(allowsNull = "false", defaultValue = "false")
	public boolean getLlegoTarde() {
		return llegoTarde;
	}

	public void setLlegoTarde(final boolean llegoTarde) {
		this.llegoTarde = llegoTarde;
	}

	// }}

	public String title() {
		return getAlumno().getNombre() + " " + getAlumno().getApellido();
	}

	// {{ marcarPresente (action)
	@MemberOrder(sequence = "1", name = "llegoTarde")
	public AsistenciaAlumno marcarPresente() {
		setEstaPresente(true);

		return this;
	}

	// }}

	// {{ marcarTarde (action)
	@MemberOrder(sequence = "2", name = "llegoTarde")
	public AsistenciaAlumno marcarTarde() {
		setEstaPresente(true);
		setLlegoTarde(true);

		return this;
	}

	// {{ marcarAusente (action)
	@MemberOrder(sequence = "3", name = "llegoTarde")
	public AsistenciaAlumno marcarAusente() {
		setEstaPresente(false);
		setLlegoTarde(false);

		return this;
	}

	// }}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
