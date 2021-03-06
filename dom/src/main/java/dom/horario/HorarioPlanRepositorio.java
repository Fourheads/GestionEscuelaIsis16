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

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.annotation.Programmatic;

import dom.escuela.Curso;
import dom.escuela.CursoRepositorio;
import dom.planEstudio.Plan;

@DomainService
@Hidden
public class HorarioPlanRepositorio {
	
	@NotContributed
	public HorarioPlan crearHorarioPlan(Plan plan) {

		HorarioPlan horarioPlan = container
				.newTransientInstance(HorarioPlan.class);
		horarioPlan.setPlan(plan);
		return horarioPlan;
	}

	@MemberOrder(sequence = "2")
	@Named("Crear Nueva Hora")
	@NotInServiceMenu
	public HorarioPlan crearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Duración (minutos)") Integer duracion) {

		Hora inicio = new Hora();
		Hora fin = new Hora();

		int tamañoListaHoras = horarioPlan.getHorarioPlanHoraList().size();

		if (tamañoListaHoras == 0) {
			inicio = horarioPlan.getInicioClases();
		} else {

			inicio = horarioPlan.getHorarioPlanHoraList()
					.get(tamañoListaHoras - 1).getHoraFin();
		}

		int minutosFin = inicio.getMinutos() + duracion;
		int horaFin = inicio.getHora();
		if (minutosFin > 59) {
			minutosFin = minutosFin - 60;
			horaFin++;
		}

		if (horaFin > 23) {
			container
					.warnUser("La hora de finalizacion no puede ser superior a las 23:59 hs");
			return horarioPlan;
		}

		fin.setHora(horaFin);
		fin.setMinutos(minutosFin);

		HorarioPlanHora horarioPlanHora = horarioPlanHoraRepositorio
				.crearHorarioPlanHora(horarioPlan);

		horarioPlanHora.setHoraInicio(inicio);
		horarioPlanHora.setHoraFin(fin);
		horarioPlanHora.setTipoHoraPlan(e_HorarioHoraTipo);
		horarioPlan.getHorarioPlanHoraList().add(horarioPlanHora);
		
		agregarHoraACursosYaCreados(horarioPlan, horarioPlanHora);
		
		return horarioPlan;
	}

	@Programmatic
	private void agregarHoraACursosYaCreados(HorarioPlan horarioPlan,
			HorarioPlanHora horarioPlanHora) {
		List<Curso> cursoList = cursoRepositorio.listarCursosDeUnPlan(horarioPlan.getPlan());
		
		for (Curso curso : cursoList){
			SortedSet<HorarioDia> horarioDiaList = curso.getHorarioCurso().getHorarioDiaList();
			for (HorarioDia horarioDia : horarioDiaList){
							
				horarioDia.getHorarioHoraList().add(horarioHoraRepositorio.crearHorarioHora(horarioDia, horarioPlanHora));
			}
		}
		
	}

	public List<Integer> choices2CrearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Duración (minutos)") Integer duracion) {

		List<Integer> minutos = new ArrayList<Integer>();

		if (e_HorarioHoraTipo == E_HorarioHoraTipo.HORA_CATEDRA) {
			minutos.add(40);
		} else {
			for (Integer i = 5; i < 61; i = i + 5) {
				minutos.add(i);
			}
		}

		return minutos;
	}

	public String validateCrearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Duración (minutos)") Integer duracion) {

		if (horarioPlan.getInicioClases() == null) {
			return "Antes de agregar una hora debe indicar la hora de inicio de clases";
		}
		return null;
	}

	// {{ ingresarHoraInicio (action)
	@MemberOrder(sequence = "1")
	public HorarioPlan ingresarHoraInicio(final HorarioPlan horarioPlan,
			@Named("Hora") int hora, @Named("Minutos") int minutos) {

		if (horarioPlan.getHorarioPlanHoraList().isEmpty()) {

			Hora horaInicio = new Hora();
			horaInicio.setHora(hora);
			horaInicio.setMinutos(minutos);
			horarioPlan.setInicioClases(horaInicio);
		} else {
			container
					.warnUser("Para poder modificar la hora de inicio no deben haber horas ya creadas. Elimine las horas e intente nuevamente.");
		}

		return horarioPlan;
	}

	public String validateIngresarHoraInicio(final HorarioPlan horarioPlan,
			int hora, int minutos) {

		if (hora < 0 || hora > 23) {
			return "ERROR. Ingrese por favor una hora entre 0 y 23";
		}
		if (minutos < 0 || minutos > 59) {
			return "ERROR. Ingrese por favor minutos entre 0 y 59";
		}
		return null;
	}

	// }}

	// {{ eliminarUltimaHora (action)
	@MemberOrder(sequence = "1")
	public HorarioPlan eliminarUltimaHora(final HorarioPlan horarioPlan) {
		if (horarioPlan.getHorarioPlanHoraList().isEmpty()) {
			container.warnUser("No Existen horas para eliminar");
		} else {
			int ultimaHoraIngresada = horarioPlan.getHorarioPlanHoraList()
					.size();
			horarioPlan.getHorarioPlanHoraList()
					.remove(ultimaHoraIngresada - 1);
			eliminarUltimaHoraDeCursosYaCreados(horarioPlan.getPlan());
		}

		return horarioPlan;
	}

	// }}

	@Programmatic
	private void eliminarUltimaHoraDeCursosYaCreados(Plan plan) {
		List<Curso> cursoList = cursoRepositorio.listarCursosDeUnPlan(plan);
		
		for (Curso curso : cursoList){
			SortedSet<HorarioDia> horarioDiaList = curso.getHorarioCurso().getHorarioDiaList();
			for (HorarioDia horarioDia : horarioDiaList){
							
				horarioHoraRepositorio.eliminarUltimaHorarioHora(horarioDia);
			}
		}
		
	}

	@Inject
	DomainObjectContainer container;
	@Inject
	HorarioPlanHoraRepositorio horarioPlanHoraRepositorio;
	@Inject
	CursoRepositorio cursoRepositorio;
	@Inject
	HorarioHoraRepositorio horarioHoraRepositorio;
}
