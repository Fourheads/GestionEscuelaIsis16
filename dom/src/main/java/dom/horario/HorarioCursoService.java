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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.simple.Curso;

@DomainService
@Hidden
public class HorarioCursoService {
	
	// {{ verHorarioDeCurso (action)
	@MemberOrder(sequence = "1")
	@NotContributed
	public HorarioCursoView verHorarioDeCurso(final Curso curso) {
		
		String plan = curso.getAnio().getPlan().getDescripcion();
		int anio = curso.getAnio().getAnioNumero();
		String division = curso.getDivision();
		
		Memento memento = mementoService.create();
		
		memento.set("plan", plan);
		memento.set("anio", anio);
		memento.set("division", division);
				
		return container.newViewModelInstance(HorarioCursoView.class, memento.asString()); 
		}
	// }}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;

}
