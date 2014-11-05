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

package dom.asistencia;

import java.math.BigDecimal;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

public class AnalisisAsistenciaView extends AbstractViewModel {

	String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;

		// memento= nombre,apellido,cantidadAsistencia,presente,tarde,ausente,
		// porcTarde,porcAusente,totalInasistencias

		Memento memento = mementoService.parse(mementoString);

		setNombre(memento.get("nombre", String.class));
		setApellido(memento.get("apellido", String.class));
		setAsistenciasRegistradas(memento.get("cantidadAsistencia",	Integer.class).toString());
		setPresente(memento.get("presente", Integer.class).toString());
		setTarde(memento.get("tarde", Integer.class).toString());
		setAusente(memento.get("ausente", Integer.class).toString());
		setPorcentajeTarde(memento.get("porcentajeTarde", BigDecimal.class).toString());
		setPorcentaje_ausente(memento.get("porcentajeAusente", BigDecimal.class).toString());
		setTotalFaltas(memento.get("totalInasistencias", BigDecimal.class).toString());

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

	@javax.inject.Inject
	MementoService mementoService;
}
