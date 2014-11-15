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

import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.joda.time.LocalDate;

@Hidden
public class ValoresHojadellibro {

	// {{ Min (property)
	private LocalDate min;

	
	public LocalDate getMin() {
		return min;
	}

	public void setMin(final LocalDate min) {
		this.min = min;
	}
	// }}

	// {{ Max (property)
	private LocalDate max;

	
	public LocalDate getMax() {
		return max;
	}

	public void setMax(final LocalDate max) {
		this.max = max;
	}
	// }}

	// {{ Unidaddisponibles (property)
	private List<Integer> Unidad=new ArrayList<Integer>();

	public List<Integer> getUnidaddisponibles() {
		return Unidad;
	}

	public void setUnidaddisponibles(final List<Integer> Unidad) {
		this.Unidad = Unidad;
	}
	
	public void addUnidaddisponibles(final int unidad)
	{
		this.Unidad.get(unidad);
	}
	// }}

	// {{ Horasdisponibles (property)
	private List<Integer> horas=new ArrayList<Integer>();;


	public List<Integer> getHorasdisponibles() {
		return horas;
	}

	public void setHorasdisponibles(final List<Integer> horas) {
		this.horas = horas;
	}
	
	public void addHorasdisponibles(final int hora)
	{
		this.horas.add(hora);
	}
	// }}



}
