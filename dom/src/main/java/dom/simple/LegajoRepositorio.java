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


package dom.simple;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.joda.time.LocalDate;

import dom.simple.Tarjeta.ECategoria;

@DomainService
@Hidden
public class LegajoRepositorio {

	@NotInServiceMenu
	@MemberOrder(sequence = "1.1", name = "Nueva Tarjeta")
	@Named("Nueva Tarjeta")
	public Legajo create (Legajo legajo,
			final @Named("Titulo") String titulo,
			final @MaxLength(2048)
		    	  @MultiLine 
		    	  @Named("Comentarios") String comentario,
		    final @Named("Categoria de Tarjeta") ECategoria categoria	  
			){
		
		final Tarjeta tarjeta = new Tarjeta();
		LocalDate fecha = LocalDate.now();
		
		tarjeta.setTitulo(titulo);
		tarjeta.setComentarios(comentario);
		tarjeta.setFecha(fecha);
		tarjeta.setCategoria(categoria);
		legajo.addTarjeta(tarjeta);
		
		//container.persistIfNotAlready(tarjeta);
		legajo.addTarjeta(tarjeta);
		return legajo;
	}
}
