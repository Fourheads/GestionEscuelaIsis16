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

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dom.simple.Alumno;
import dom.simple.Curso;

@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "PeriodosPorCiclo", 
			language = "JDOQL", 
			value = "SELECT FROM dom.calificacion.Periodo"
					+" WHERE this.calificaciones.cicloCalificacion == :ciclo &&" +
					" this.habilitado == 'S'"),
	@javax.jdo.annotations.Query(name = "listarTodos", 
			language = "JDOQL", 
			value = "SELECT FROM dom.calificacion.Periodo"
					+" WHERE this.habilitado == 'S'")
})

@PersistenceCapable
@Bounded
public class Periodo {	
	
	// {{ Habilitado (property)
	private Character habilitado;
	
	@Hidden
	@Column(allowsNull = "false")
	@MemberOrder(sequence = "1")
	public Character getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(final Character habilitado) {
		this.habilitado = habilitado;
	}
	// }}

	
	// {{ Nombre (property)
	private String nombre;
	
	@Named("Periodo")	
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
	// }}

	// {{ FechaInicio (property)
	private LocalDate fechaInicio;

	@Named("Inicio")	
	@MemberOrder(sequence = "1.1")
	@Column(allowsNull = "true")
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(final LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	// }}


	// {{ FechaFinal (property)
	private LocalDate fechaFinal;

	@Named("Cierre")	
	@MemberOrder(sequence = "1.2")
	@Column(allowsNull = "true")
	public LocalDate getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(final LocalDate fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	// }}

	// {{ Calificaciones (property)
	private Calificaciones calificaciones;
	
	@Hidden
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Calificaciones getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(final Calificaciones calificaciones) {
		this.calificaciones = calificaciones;
	}
	// }}
	
	public String title(){
		return getNombre();
	}

	@javax.inject.Inject
	DomainObjectContainer container;


}
