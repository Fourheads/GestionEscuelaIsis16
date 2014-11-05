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

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;

@DomainService
@Hidden
public class HorarioHoraRepositorio {

	public HorarioHora crearHorarioHora(HorarioDia horarioDia,
			HorarioPlanHora horarioPlanHora) {

		HorarioHora horarioHora = new HorarioHora();
		horarioHora.setHorarioDia(horarioDia);
		horarioHora.setHorarioPlanHora(horarioPlanHora);
		horarioHora.setHorarioHoraTipo(horarioPlanHora.getTipoHoraPlan());

		return horarioHora;
	}

	public void eliminarUltimaHorarioHora(HorarioDia horarioDia) {

		int ultimaHoraIngresada = horarioDia.getHorarioHoraList().size();
		horarioDia.getHorarioHoraList().remove(ultimaHoraIngresada - 1);
		
	}

}
