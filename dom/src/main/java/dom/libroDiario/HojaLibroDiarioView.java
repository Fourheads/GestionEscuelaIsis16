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
import java.util.List;

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
@MemberGroupLayout(columnSpans = { 6, 0, 6, 12 })

public class HojaLibroDiarioView extends AbstractViewModel {

	@Hidden
	public String getId() {
		return viewModelMemento();
	}

	private String title="Fecha: ";

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
		
		title = title+memento.get("Titulo", String.class);
		setLibrodiario(memento.get("Librodiario", LibroDiario.class));
		
		setListahojadellibro(this.hojalibrodiarioservice.returnListhojadellibro(this.getLibrodiario()));
		
	}

// {{ Listahojadellibro (property)
private List<Hojadellibro> hojadellibro;

@MemberOrder(sequence = "1")
public List<Hojadellibro> getListahojadellibro() {
	return hojadellibro;
}

public void setListahojadellibro(final List<Hojadellibro> hojadellibro) {
	this.hojadellibro = hojadellibro;
}

// {{ Librodiario (property)
private LibroDiario librodiarioe;

@MemberOrder(sequence = "1")
public LibroDiario getLibrodiario() {
	return librodiarioe;
}

public void setLibrodiario(final LibroDiario librodiarioe) {
	this.librodiarioe = librodiarioe;
}
// }}



// }}


	
	
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	HojaLibroDiarioSevice hojalibrodiarioservice;
}
