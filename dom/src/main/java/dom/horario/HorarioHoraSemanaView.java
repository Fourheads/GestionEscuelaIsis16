/*
 * This is a software made for highschool management 
 * 
 * Copyright (C) 2014, Fourheads
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * 
 * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package dom.horario;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.w3c.dom.views.AbstractView;

public class HorarioHoraSemanaView extends AbstractViewModel{

	String memento;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;
		
		Memento memento = mementoService.parse(mementoString);
		
		setInicioFin(memento.get("inicioFin", String.class));
		setLunes(memento.get("lunes", String.class));
		setMartes(memento.get("martes", String.class));
		setMiercoles(memento.get("miercoles", String.class));
		setJueves(memento.get("jueves", String.class));
		setViernes(memento.get("viernes", String.class));
		
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

	@javax.inject.Inject
	MementoService mementoService;
	
}
