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

package dom.escuela;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.util.ObjectContracts;

@PersistenceCapable
public class Funcion implements Comparable<Funcion>{
	
	// {{ Nombre (property)
	private E_funciones nombre;

	@Column(allowsNull = "false")
	@MemberOrder(sequence = "1")
	@Persistent
	public E_funciones getNombre() {
		return nombre;
	}

	public void setNombre(final E_funciones nombre) {
		this.nombre = nombre;
	}
	// }}
	
	// {{ Observaciones (property)
	private String observaciones;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.1")
	@Persistent
	@MultiLine
	@Optional
	final @MaxLength(2048)
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(final String observacion) {
		this.observaciones = observacion;
	}
	// }}

	public enum E_funciones{
		PROFESOR, PRECEPTOR, SECRETARIO, DIRECTOR;
	}
	
	@Override
	public int compareTo(Funcion o) {
		
		return ObjectContracts.compare(this, o, "nombre");
	}
}


