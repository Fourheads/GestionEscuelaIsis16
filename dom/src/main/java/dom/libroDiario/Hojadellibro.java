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

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.MemberOrder;
import org.joda.time.LocalDate;

public class Hojadellibro {
	
	// {{ Hora (property)
		private int Hora;

		@MemberOrder(sequence = "1")
		@Column(allowsNull = "false")
		public int getHora() {
			return Hora;
		}

		public void setHora(final int Hora) {
			this.Hora = Hora;
		}
		// }}

		// {{ Materia (property)
		private MateriaDelLibroDiario Materia;

		@Column(allowsNull = "true")
		@MemberOrder(sequence = "2")
		public MateriaDelLibroDiario getMateria() {
			return Materia;
		}

		public void setMateria(final MateriaDelLibroDiario Materia) {
			this.Materia = Materia;
		}
		// }}


		// {{ Unidad (property)
		private int Unidad;

		@Column(allowsNull = "true")
		@MemberOrder(sequence = "3")
		public int getUnidad() {
			return Unidad;
		}

		public void setUnidad(final int Unidad) {
			this.Unidad = Unidad;
		}
		// }}

		// {{ Actividades (property)
		private String Actividades;

		@Column(allowsNull = "true")
		@MemberOrder(sequence = "4")
		public String getActividades() {
			return Actividades;
		}

		public void setActividades(final String Actividades) {
			this.Actividades = Actividades;
		}
		// }}

		// {{ Observaciones (property)
		private String Observacion;
		@Column(allowsNull = "false")
		@MemberOrder(sequence = "5")
		public String getObservaciones() {
			return Observacion;
		}

		public void setObservaciones(final String Observacion) {
			this.Observacion = Observacion;
		}
		// }}
		
		// {{ Fehca (property)
		private LocalDate Fecha;

		@MemberOrder(sequence = "9")
		public LocalDate getFecha() {
			return Fecha;
		}

		public void setFecha(final LocalDate Fecha) {
			this.Fecha = Fecha;
		}
		// }}

}
