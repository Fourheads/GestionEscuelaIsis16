package dom.horario;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.w3c.dom.views.AbstractView;

public class HorarioHoraSemanaView extends AbstractViewModel{

	String memento;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
	}

	
	// {{ InicioFin (property)
	private String inicioFin;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getInicioFin() {
		return inicioFin;
	}

	public void setInicioFin(final String inicioFin) {
		this.inicioFin = inicioFin;
	}
	// }}

	// {{ Lunes (property)
	private String lunes;

	@MemberOrder(sequence = "1.5")
	@Column(allowsNull = "true")
	public String getLunes() {
		return lunes;
	}

	public void setLunes(final String lunes) {
		this.lunes = lunes;
	}
	// }}

	// {{ Martes (property)
	private String martes;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public String getMartes() {
		return martes;
	}

	public void setMartes(final String martes) {
		this.martes = martes;
	}
	// }}

	// {{ Miercoles (property)
	private String Miercoles;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getMiercoles() {
		return Miercoles;
	}

	public void setMiercoles(final String Miercoles) {
		this.Miercoles = Miercoles;
	}
	// }}

	// {{ Jueves (property)
	private String jueves;

	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getJueves() {
		return jueves;
	}

	public void setJueves(final String jueves) {
		this.jueves = jueves;
	}
	// }}

	// {{ Viernes (property)
	private String viernes;

	@MemberOrder(sequence = "5")
	@Column(allowsNull = "true")
	public String getViernes() {
		return viernes;
	}

	public void setViernes(final String viernes) {
		this.viernes = viernes;
	}
	// }}


	
}
