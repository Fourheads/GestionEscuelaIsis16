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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.TypicalLength;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.escuela.Curso;
import dom.escuela.MateriaDelCurso;

@MemberGroupLayout(columnSpans = {4,0,0,8}, left = "Detalles del Horario")
public class HorarioCursoView extends AbstractViewModel {

	String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoAsString) {
		this.memento = mementoAsString;

		Memento memento = mementoService.parse(mementoAsString);

		String plan = memento.get("plan", String.class);
		setPlan(plan);
		Integer anio = memento.get("anio", Integer.class);
		setAnio(anio);
		String division = memento.get("division", String.class);
		setDivision(division);
		List<HorarioHoraSemanaView> viewList = horarioHoraSemanaService
				.crearHorarioHoraSemanaViewList(plan, anio, division);
		setHorarioHoraSemanaViewList(viewList);

	}

	// Propiedades del ModelView
	// ///////////////////////////////////////////////////////////////////////

	// {{ Plan (property)
	private String plan;

	@MemberOrder(sequence = "1", name = "Detalles del Horario")
	@Column(allowsNull = "true")
	public String getPlan() {
		return plan;
	}

	public void setPlan(final String plan) {
		this.plan = plan;
	}

	// }}

	// {{ Anio (property)
	private Integer anio;

	@MemberOrder(sequence = "2", name = "Detalles del Horario")
	@Column(allowsNull = "true")
	@TypicalLength(value = 2)
	public int getAnio() {
		return anio;
	}

	public void setAnio(final Integer anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@TypicalLength(value = 2)
	@MemberOrder(sequence = "3", name = "Detalles del Horario")
	@Column(allowsNull = "true")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	// {{ HorarioHoraSemanaViewList (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "true")
	private List<HorarioHoraSemanaView> horarioHoraSemanaViewList = new ArrayList<HorarioHoraSemanaView>();

	@Named("Horario")
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<HorarioHoraSemanaView> getHorarioHoraSemanaViewList() {
		return horarioHoraSemanaViewList;
	}

	public void setHorarioHoraSemanaViewList(
			final List<HorarioHoraSemanaView> horarioHoraSemanaViewList) {
		this.horarioHoraSemanaViewList = horarioHoraSemanaViewList;
	}

	// {{ asignarMateria (action)
	// ////////////////////////////////////////////

	@MemberOrder(sequence = "1", name = "Horario")
	@Named("Asignar Materia a Hora Cátedra")
	public HorarioCursoView asignarMateria(final @Named("Día") HorarioDia dia,
			final @Named("Hora") HorarioHora hora,
			final @Named("Materia") MateriaDelCurso materia) {

		hora.setMateriaDelCurso(materia);
		return this;
	}

	public SortedSet<HorarioDia> choices0AsignarMateria() {

		Curso curso = container.firstMatch(new QueryDefault<Curso>(Curso.class,
				"buscarUnCurso", "plan", plan, "anio", anio, "division",
				division));

		return curso.getHorarioCurso().getHorarioDiaList();
	}

	public List<HorarioHora> choices1AsignarMateria(
			final @Named("Día") HorarioDia dia) {
		if (dia != null) {
			List<HorarioHora> listaHoras = dia.getHorarioHoraList();

			List<HorarioHora> listaHorasFiltrada = new ArrayList<HorarioHora>();
			for (HorarioHora horarioHora : listaHoras) {
				if (horarioHora.getHorarioHoraTipo() == E_HorarioHoraTipo.HORA_CATEDRA
						&& horarioHora.getMateriaDelCurso() == null) {
					listaHorasFiltrada.add(horarioHora);
				}
			}

			return listaHorasFiltrada;
		}
		return null;
	}

	public List<MateriaDelCurso> choices2AsignarMateria() {

		return container.allMatches(new QueryDefault<MateriaDelCurso>(
				MateriaDelCurso.class, "MateriaDelCursoDeUnCurso", "plan",
				getPlan(), "anio", getAnio(), "division", getDivision()));
	}

	// }} endregion> asignarMateria

	// {{ quitarMateria (action)
	// ////////////////////////////////////////////

	@MemberOrder(sequence = "2", name = "Horario")
	@Named("Quitar Materia de Hora Cátedra")
	public HorarioCursoView quitarMateria(final @Named("Día") HorarioDia dia,
			final @Named("Hora") HorarioHora hora) {

		hora.setMateriaDelCurso(null);
		return this;
	}

	public SortedSet<HorarioDia> choices0QuitarMateria() {

		Curso curso = container.firstMatch(new QueryDefault<Curso>(Curso.class,
				"buscarUnCurso", "plan", plan, "anio", anio, "division",
				division));

		return curso.getHorarioCurso().getHorarioDiaList();
	}

	public List<HorarioHora> choices1QuitarMateria(
			final @Named("Día") HorarioDia dia) {
		if (dia != null) {
			List<HorarioHora> listaHoras = dia.getHorarioHoraList();

			List<HorarioHora> listaHorasFiltrada = new ArrayList<HorarioHora>();
			for (HorarioHora horarioHora : listaHoras) {
				if (horarioHora.getHorarioHoraTipo() == E_HorarioHoraTipo.HORA_CATEDRA
						&& horarioHora.getMateriaDelCurso() != null) {
					listaHorasFiltrada.add(horarioHora);
				}
			}
			return listaHorasFiltrada;
		}
		return null;
	}

	// }} (end region)
	// //////////////////////////////////////

	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return "Horario de Clases " + getAnio() + "° '" + getDivision() + "'";
	}

	// end region Title (GUI)
	// //////////////////////////////////////////
	
	
	
	
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	HorarioHoraSemanaService horarioHoraSemanaService;
	@javax.inject.Inject
	DomainObjectContainer container;

}
