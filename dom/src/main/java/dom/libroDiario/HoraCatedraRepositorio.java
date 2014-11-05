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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;

//@Named("Hora catedra")
@Hidden
@DomainService(repositoryFor = HoraCatedra.class)
public class HoraCatedraRepositorio {

	//@Named("Crear hora")
	//@MemberOrder(sequence = "1")
	public HoraCatedra crearhoracatedra(@Named("Numero de hora") int horas, @Named("Unidad") int unidad,final @MaxLength(2048)
	  @MultiLine @Named("Actividad") String actividad,
	  final @MaxLength(2048)
  	  @MultiLine@Named("Observaciones") String Observaciones ){
		
		HoraCatedra horacatedra=container.newTransientInstance(HoraCatedra.class);
		
		horacatedra.setUnidad(unidad);
		horacatedra.setNumerodehora(horas);
		horacatedra.setObservaciones(Observaciones);
		horacatedra.setActividad(actividad);
		
		container.persistIfNotAlready(horacatedra);
		
		
		return horacatedra;
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
}
