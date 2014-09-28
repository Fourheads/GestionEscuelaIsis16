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

import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Bulk;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.query.QueryDefault;

import dom.simple.Funcion.E_funciones;

@Bounded
@PersistenceCapable
public class Materia_old {

	private int año;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	@Persistent
	public int getAño() {
		return año;
	}

	public void setAño(int anio) {
		this.año = anio;
	}

	private String nombre;

	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.1")
	@Persistent
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private String programa;

	final @MaxLength(2048) @MultiLine @Column(allowsNull = "true") @MemberOrder(sequence = "1.2") @Persistent public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	private Personal profesor;

	@Persistent
	// @Title
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.3")
	public Personal getProfesor() {
		return profesor;
	}

	public void setProfesor(Personal profesor) {
		this.profesor = profesor;
	}

	@MemberOrder(sequence = "1.4")
	@Named("Asinganar profesor")
	public void asignarProfesor(final @Named("Profesor") Personal profesor) {
		this.profesor = profesor;
	}

	public List<Personal> choices0AsignarProfesor() {
		return container.allMatches(new QueryDefault<Personal>(Personal.class,
				"findByFuncion", "nombre", E_funciones.PROFESOR.toString()));
	}

	public String title() {
		String terminacion = "to";

		if ((getAño() == 1) || (getAño() == 3)) {
			terminacion = "er";
		} else {
			if (getAño() == 2) {
				terminacion = "do";
			}
		}

		return getNombre() + " de " + getAño() + terminacion +" Año";
	}

		

	// efective java programing

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	/*
	 * @PublishedAction
	 * 
	 * @Bulk public String eliminar(){ container.removeIfNotAlready(this);
	 * container.informUser("blalballa"); return "La materia se elimino"; }
	 */

}
