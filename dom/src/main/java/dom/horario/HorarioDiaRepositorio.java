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

import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotContributed;

import dom.escuela.Curso;

@Hidden
@DomainService
public class HorarioDiaRepositorio {
	
	@NotContributed
	public SortedSet<HorarioDia> crearHorarioDiaList(Curso curso) {

		SortedSet<HorarioDia> horarioDiaList = new TreeSet<HorarioDia>();
		horarioDiaList.add(crearUnDia(1, E_HorarioDiaSemana.LUNES, curso));
		horarioDiaList.add(crearUnDia(2, E_HorarioDiaSemana.MARTES, curso));
		horarioDiaList.add(crearUnDia(3, E_HorarioDiaSemana.MIERCOLES, curso));
		horarioDiaList.add(crearUnDia(4, E_HorarioDiaSemana.JUEVES, curso));
		horarioDiaList.add(crearUnDia(5, E_HorarioDiaSemana.VIERNES, curso));
		
		
		return horarioDiaList;
	}
	
	@NotContributed
	private HorarioDia crearUnDia(int ordenDia, E_HorarioDiaSemana dia, Curso curso){
		HorarioDia horarioDia = new HorarioDia();
		horarioDia.setOrdenDias(ordenDia);
		horarioDia.setDiaDeLaSemana(dia);
		
		List <HorarioHora> horarioHoraList = new ArrayList<HorarioHora>();
		
		List <HorarioPlanHora> horarioPlanHoraList = curso.getAnio().getPlan().getHorarioPlan().getHorarioPlanHoraList();
		for (HorarioPlanHora horarioPlanHora : horarioPlanHoraList){

			horarioHoraList.add(horaRepositorio.crearHorarioHora(horarioDia, horarioPlanHora));
		}
		horarioDia.setHorarioHoraList(horarioHoraList);
		
		return horarioDia;
	}
	
	@javax.inject.Inject
	HorarioHoraRepositorio horaRepositorio;
}
