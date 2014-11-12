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

package dom.libroDiario;

import java.math.BigDecimal;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

@Named("Hoja del libro diario")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class HojaLibroDiarioView extends AbstractViewModel {

	@Hidden
	public String getId() {
		return viewModelMemento();
	}

	private String title;

	// region > identification in the UI
	public String title() {
		return title;
	}

	public String iconName() {
		return "SimpleObject";
	}
	
	String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;


		Memento memento = mementoService.parse(mementoString);
		
		
		title = memento.get("Titulo", String.class);
		
		setHora(memento.get("Hora", Integer.class).toString());
		setMateria(memento.get("Materia", MateriaDelLibroDiario.class).toString());
		setUnidad(memento.get("Unidad", Integer.class).toString());
		setActividades(memento.get("Actividad", String.class));
		setObservaciones(memento.get("Observaciones", String.class));
		setFecha(memento.get("Fecha", LocalDate.class).toString());

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(getFecha());
	}


	// {{ Hora (property)
	private String Hora;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getHora() {
		return Hora;
	}

	public void setHora(final String Hora) {
		this.Hora = Hora;
	}
	// }}

	// {{ Materia (property)
	private String Materia;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "2")
	public String getMateria() {
		return Materia;
	}

	public void setMateria(final String Materia) {
		this.Materia = Materia;
	}
	// }}


	// {{ Unidad (property)
	private String Unidad;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "3")
	public String getUnidad() {
		return Unidad;
	}

	public void setUnidad(final String Unidad) {
		this.Unidad = Unidad;
	}
	// }}

	// {{ Actividades (property)
	private String Actividades;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "4")
	public String getActividades() {
		return Actividades;
	}

	public void setActividades(final String Actividades) {
		this.Actividades = Actividades;
	}
	// }}

	// {{ Observaciones (property)
	private String Observacion;
	@Column(allowsNull = "false")
	@MemberOrder(sequence = "5")
	public String getObservaciones() {
		return Observacion;
	}

	public void setObservaciones(final String Observacion) {
		this.Observacion = Observacion;
	}
	// }}
	
	// {{ Fehca (property)
	private String Fecha;

	@MemberOrder(sequence = "9")
	public String getFecha() {
		return Fecha;
	}

	public void setFecha(final String Fecha) {
		this.Fecha = Fecha;
	}
	// }}




	
	@javax.inject.Inject
	MementoService mementoService;
}
