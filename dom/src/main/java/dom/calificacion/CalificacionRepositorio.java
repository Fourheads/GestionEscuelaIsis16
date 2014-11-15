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

package dom.calificacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;


@DomainService(menuOrder = "100", repositoryFor = Calificaciones.class)
@Named("Ciclo Lectivo")
@MemberGroupLayout(columnSpans = { 2, 0, 0, 10 })
public class CalificacionRepositorio {
	
	
	@Named("Crear Ciclo")
	public Calificaciones createCalificacion(final @Named("Ingrese el año: ") int inCiclo){
		
		final Calificaciones newCalificacion = container.newTransientInstance(Calificaciones.class);
		newCalificacion.setCicloCalificacion(inCiclo);
		
		container.persistIfNotAlready(newCalificacion);
		
		return newCalificacion;
	}	
	
	@NotContributed
	@Named("Seleccionar ciclo")
	public List<Calificaciones> listByCiclo(final @Named("Ciclo Lectivo: ")Calificaciones ciclo){
		return container.allMatches(
				new QueryDefault<Calificaciones>(Calificaciones.class, "findByCiclo","ciclo", ciclo.getCicloCalificacion()));
	}
	
	
	@Named("Listar Ciclos")
	public List<Calificaciones> listCalificaciones(){
		return container.allInstances(Calificaciones.class);
	}
	
	public List<Calificaciones> ciclosConPeriodo(){
		return container.allMatches(new QueryDefault<Calificaciones>(Calificaciones.class, "findCicloConPeriodos"));
	}
	
		
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	dom.simple.CursoRepositorio cursoRepo;
}

