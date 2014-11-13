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

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;


import dom.simple.Curso;

@Hidden
@DomainService()
public class HojaLibroDiarioSevice {

	@Hidden
	public String getId() {
		return "hojalibrodiario";
	}

	public String title() {
		return "Hoja del libro diario";
	}

	public String iconName() {//Ver que onda??
		return "SimpleObject";
	}
		
	
	//@MemberOrder(name = "Hoja del libro",sequence = "10")
	@PublishedAction
	public void Crearview(LibroDiario newlibro,LocalDate fecha) {

		Memento memento = mementoService.create();
		
		memento.set("Titulo", fecha);
		memento.set("Librodiario", newlibro);
		
		HojaLibroDiarioView newhoja=new HojaLibroDiarioView();
		
		newhoja.viewModelInit(memento.asString());
		newhoja.setListahojadellibro(this.returnListhojadellibro(newlibro));
		
		//return container.newViewModelInstance(
			//	HojaLibroDiarioView.class, memento.asString());
		 container.persist(newhoja);
	}


	public List<Hojadellibro> returnListhojadellibro(LibroDiario newlibro)
	{
		List<Hojadellibro> Listhojadellibro=new ArrayList<Hojadellibro>(); 
		
		for(MateriaDelLibroDiario matelibro:newlibro.getMateriaDelLibroDiarioList())
		{
			
			for(EntradaLibroDiario entralibro:matelibro.getEntradaLibroDiario())
			{
				for(HoraCatedra horacete:entralibro.getHoraCatedra())
				{
					Hojadellibro newhojalibro=new Hojadellibro();
					
					newhojalibro.setMateria(matelibro);
					newhojalibro.setFecha(entralibro.getFecha());
					newhojalibro.setActividades(horacete.getActividad());
					newhojalibro.setObservaciones(horacete.getObservaciones());
					newhojalibro.setUnidad(horacete.getUnidad());
					newhojalibro.setHora(horacete.getNumerodehora());
					
					Listhojadellibro.add(newhojalibro);
				}
			}
		}
		
		return Listhojadellibro;
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	LibroDiarioRepositorio librorepo;
}
