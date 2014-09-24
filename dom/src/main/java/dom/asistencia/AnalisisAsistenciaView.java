package dom.asistencia;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

public class AnalisisAsistenciaView extends AbstractViewModel {

	String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;

		String[] parametros = memento.split(",");

		// memento= nombre,apellido,cantidadAsistencia,presente,tarde,ausente,
		// porcTarde,porcAusente,totalInasistencias

		setNombre(parametros[0]);
		setApellido(parametros[1]);
		setAsistenciasRegistradas(parametros[2]);
		setPresente(parametros[3]);

		System.out.println("");
		System.out.println("");
		System.out.println(getPresente());
		System.out.println("");
		System.out.println("");

		setTarde((parametros[4]));
		setAusente((parametros[5]));
		setPorcentajeTarde((parametros[6]));
		setPorcentaje_ausente((parametros[7]));
		setTotalFaltas((parametros[8]));

	}

	public String title() {
		return memento;
	}

	// {{ Nombre (property)
	private String nombre;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	// }}

	// {{ Apellido (property)
	private String apellido;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(final String apellido) {
		this.apellido = apellido;
	}

	// }}

	// {{ AsistenciasRegistradas (property)
	private String asistenciasRegistradas;

	@Named("Registros")
	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getAsistenciasRegistradas() {
		return asistenciasRegistradas;
	}

	public void setAsistenciasRegistradas(final String totalAsistencias) {
		this.asistenciasRegistradas = totalAsistencias;
	}

	// }}

	// {{ Presente (property)
	private String presente;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getPresente() {
		return presente;
	}

	public void setPresente(final String presente) {
		this.presente = presente;
	}

	// }}

	// {{ Tarde (property)
	private String tarde;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public String getTarde() {
		return tarde;
	}

	public void setTarde(final String tarde) {
		this.tarde = tarde;
	}

	// }}

	// {{ Ausente (property)
	private String ausente;

	@MemberOrder(sequence = "6")
	@Column(allowsNull = "true")
	public String getAusente() {
		return ausente;
	}

	public void setAusente(final String ausente) {
		this.ausente = ausente;
	}

	// }}

	// {{ Porcentaje_tarde (property)
	private String porcentajeTarde;

	@Named("Tarde %")
	@MemberOrder(sequence = "7")
	@Column(allowsNull = "true")
	public String getPorcentajeTarde() {
		return porcentajeTarde;
	}

	public void setPorcentajeTarde(final String porcentajeTarde) {
		this.porcentajeTarde = porcentajeTarde;
	}

	// }}

	// {{ Porcentaje_ausente (property)
	private String porcentajeAusente;

	@Named("Ausente %")
	@MemberOrder(sequence = "8")
	@Column(allowsNull = "true")
	public String getPorcentaje_ausente() {
		return porcentajeAusente;
	}

	public void setPorcentaje_ausente(final String porcentajeAusente) {
		this.porcentajeAusente = porcentajeAusente;
	}

	// }}

	// {{ TotalFaltas (property)
	private String totalFaltas;

	@Named("Total Faltas")
	@MemberOrder(sequence = "9")
	@Column(allowsNull = "true")
	public String getTotalFaltas() {
		return totalFaltas;
	}

	public void setTotalFaltas(final String totalFaltas) {
		this.totalFaltas = totalFaltas;
	}
	// }}

}
