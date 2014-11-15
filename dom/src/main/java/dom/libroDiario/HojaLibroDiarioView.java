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


import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

@Named("Hoja del libro diario")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 6, 0, 6, 12 })
public class HojaLibroDiarioView extends AbstractViewModel {

	@Hidden
	public String getId() {
		return viewModelMemento();
	}

	private String title="Hoja del libro dairio para el curso: ";

	// region > identification in the UI
	public String title() {
		return title;
	}

	String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	// {{ Listahojadellibro (property)
	private List<Hojadellibro> hojadellibro;

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "2")
	@Named("Registro de clases dictadas")
	public List<Hojadellibro> getListahojadellibro() {
		return hojadellibro;
	}

	public void setListahojadellibro(final List<Hojadellibro> hojadellibro) {
		this.hojadellibro = hojadellibro;
	}

	// }}

	// {{ Fecha (property)
	private LocalDate fecha;


	@MemberOrder(sequence = "1")
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(final LocalDate fecha) {
		this.fecha = fecha;
	}
	// }}

	// {{ Min (property)
	private LocalDate min;

	@Hidden
	public LocalDate getMin() {
		return min;
	}

	public void setMin(final LocalDate min) {
		this.min = min;
	}
	// }}

	// {{ Max (property)
	private LocalDate max;

	@Hidden
	public LocalDate getMax() {
		return max;
	}

	public void setMax(final LocalDate max) {
		this.max = max;
	}
	// }}


	// {{ Año (property)
	private int anio;

	@Hidden
	@MemberOrder(sequence = "1")
	public int getAño() {
		return anio;
	}

	public void setAño(final int anio) {
		this.anio = anio;
	}
	// }}

	// {{ Division (property)
	private String divicion;

	@Hidden
	@MemberOrder(sequence = "1")
	public String getDivision() {
		return divicion;
	}

	public void setDivision(final String divicion) {
		this.divicion = divicion;
	}
	// }}

	// {{ Plan (property)
	private String plan;

	@Hidden
	@MemberOrder(sequence = "1")
	public String getPlan() {
		return plan;
	}

	public void setPlan(final String plan) {
		this.plan = plan;
	}
	// }}
	
	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;


		Memento memento = mementoService.parse(mementoString);
		
		setAño(memento.get("Anio", Integer.class));
		setDivision(memento.get("Division", String.class));
		setFecha(memento.get("Fecha", LocalDate.class));
		setPlan(memento.get("Plan", String.class));
		
		title = title+getAño()+"º "+getDivision()+" Plan:"+getPlan();
	
		setListahojadellibro(this.hojalibrodiarioservice.Crearview(getPlan(), getAño(), getDivision(), getFecha()));
		setMax(this.hojalibrodiarioservice.maxvalue());
		setMin(this.hojalibrodiarioservice.minvalue());
	}

	
	
	@Named("Siguiente dia")
	@PublishedAction
	public HojaLibroDiarioView pasarAldiasiguiente() {

		return NuevoViewModel(DiaSiguente());
	}


	@Named("Dia anterior")
	@PublishedAction
	public HojaLibroDiarioView pasarAldiaanterior() {
						
		return NuevoViewModel(DiaAnterior());
	}
	
	
	@Programmatic
	private HojaLibroDiarioView NuevoViewModel(LocalDate newIndex) {
		
		
		
		Memento memento = mementoService.create();

		memento.set("Anio", getAño());
		memento.set("Division", getDivision());
		memento.set("Fecha", newIndex);
		memento.set("Plan", getPlan());

		return container.newViewModelInstance(HojaLibroDiarioView.class,
				memento.asString());
	}
	
	
	@Programmatic
	public LocalDate DiaSiguente() {
		
		LocalDate newIndex = getFecha();
		
		newIndex=newIndex.plusDays(+1);

		if (newIndex.isAfter(getMax())) {
			newIndex=min;
		}
		
		return newIndex;
	}
	
	@Programmatic
	public LocalDate DiaAnterior() {
		LocalDate newIndex = getFecha();
		
		newIndex=newIndex.plusDays(-1);
		
		newIndex.plusDays(-1);

		if (newIndex.isBefore(getMin())) {
			newIndex=max;
		}
		
		return newIndex;
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	HojaLibroDiarioSevice hojalibrodiarioservice;
}
