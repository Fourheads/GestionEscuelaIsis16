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

import java.io.ObjectInputStream.GetField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.PublishedObject;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.TypicalLength;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

@Named("Contabilizar Asistencias View")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 6, 0, 6, 12 }, right = "Intervalo", left = "Curso")
public class ContabilizarAsistenciasView extends AbstractViewModel {

	String memento;
	String title;

	public String title() {
		return title;
	}

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;
		
		Memento memento = mementoService.parse(mementoString);

		title = memento.get("titulo", String.class);
		setAsistencia(memento.get("asistencia", String.class));
		setDesde(memento.get("desde", LocalDate.class));
		setHasta(memento.get("hasta", LocalDate.class));
		setAnio(memento.get("anio", Integer.class));
		setDivision(memento.get("division", String.class));
				
		
		 setAnalisisAsistenciaViewList(analisisAsistenciaService.analizarIntervaloAsistenciaCurso(
				 getAsistencia(), getAnio(), getDivision(), getDesde(), getHasta()));

	}

	// {{ Desde (property)
	private LocalDate desde;

	@MemberOrder(sequence = "1", name = "Intervalo")
	@Column(allowsNull = "true")
	public LocalDate getDesde() {
		return desde;
	}

	public void setDesde(final LocalDate localDate) {
		this.desde = localDate;
	}

	// }}

	// {{ Hasta (property)
	private LocalDate hasta;

	@MemberOrder(sequence = "2", name = "Intervalo")
	@Column(allowsNull = "true")
	public LocalDate getHasta() {
		return hasta;
	}

	public void setHasta(final LocalDate hasta) {
		this.hasta = hasta;
	}

	// }}

	// {{ Anio (property)
	private int anio;

	@MemberOrder(sequence = "2", name = "Curso")
	@Column(allowsNull = "true")
	@TypicalLength(value = 5)
	@Named("Año")
	public int getAnio() {
		return anio;
	}

	public void setAnio(final int anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "3", name = "Curso")
	@Column(allowsNull = "true")
	@TypicalLength(value = 5)
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	
	// {{ Asistencia (property)
	private String asistencia;

	@MemberOrder(sequence = "1", name = "Curso")
	@Column(allowsNull = "true")
	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(final String asistencia) {
		this.asistencia = asistencia;
	}

	// }}

	// {{ AnalisisAsistenciaView (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "true")
	private List<AnalisisAsistenciaView> analisisAsistenciaViewList = new ArrayList<AnalisisAsistenciaView>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	@Named("Analisis de Asistencia por Alumno")
	public List<AnalisisAsistenciaView> getAnalisisAsistenciaViewList() {
		return analisisAsistenciaViewList;
	}

	public void setAnalisisAsistenciaViewList(
			final List<AnalisisAsistenciaView> analisisAsistenciaViewList) {
		this.analisisAsistenciaViewList = analisisAsistenciaViewList;
	}

	// }} (end region)
	// //////////////////////////////////////

	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	AnalisisAsistenciaService analisisAsistenciaService;
}
