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

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.escuela.Curso;
import dom.escuela.CursoRepositorio;
import dom.escuela.MateriaDelCurso;
import dom.planEstudio.Materia;

@Named("Cargar Notas")
@DomainService(menuOrder = "80")
public class CargarNotaService {

	@Hidden
	public String getId() {
		return "cargarNotas";
	}

	public String title() {
		return "Cargar Notas";
	}

	public String iconName() {
		return "SimpleObject";
	}

	@Named("Cargar Notas")
	@MemberOrder(name = "Cursos", sequence = "5")
	@NotContributed
	public CargarNotaView calificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion,
			final @Named("Periodo: ") Periodo inPeriodo,
			final @Named("Curso: ") Curso inCurso,
			final @Named("Materia: ") MateriaDelCurso inMateria) {

		Memento newMemento = mementoService.create();
		// titulo, periodo, anio, division, materia, plan, alumnoIndex
		newMemento.set("titulo", "Cargar Nota");
		newMemento.set("periodo", inPeriodo.getNombre());
		newMemento.set("anio", inCurso.getAnio().getAnioNumero());
		newMemento.set("division", inCurso.getDivision());
		newMemento.set("materia", inMateria.getMateria().getNombre());
		newMemento.set("plan", inCurso.getAnio().getPlan().getDescripcion());
		newMemento.set("indiceAlumno", 0);

		// titulo, periodo, anio, division, materia, plan, alumnoIndex

		final CargarNotaView newView = container.newViewModelInstance(
				CargarNotaView.class, newMemento.asString());

		return newView;

	}
	
	public String validateCalificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion,
			final @Named("Periodo: ") Periodo inPeriodo,
			final @Named("Curso: ") Curso inCurso,
			final @Named("Materia: ") MateriaDelCurso inMateria){
		List<Calificaciones> listCalificaciones = califRepositorio.ciclosConPeriodo();
		if(listCalificaciones.isEmpty()){
			return "Debe crear al menos un ciclo lectivo con sus períodos de evaluación";
		}
		return null;
	}
	
	public List<Calificaciones> choices0CalificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion) {

		List<Calificaciones> listCalificaciones = califRepositorio
				.ciclosConPeriodo();
		if (listCalificaciones.isEmpty()) {
			
			return null;
		}
		return listCalificaciones;
	}

	/*public Calificaciones default0CalificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion) {
		if (choices0CalificarPorCurso(inCalificacion).isEmpty()) {
			return null;
		}
		return choices0CalificarPorCurso(inCalificacion).get(0);
	}*/

	public List<Periodo> choices1CalificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion
	/* final @Named("Periodo: ") Periodo inPeriodo */) {
		List<Periodo> periodos = new ArrayList<Periodo>();
		
		if(inCalificacion != null){
			periodos = perRepo.periodoPorCiclo(inCalificacion.getCicloCalificacion());
			if (!periodos.isEmpty()) {
				return periodos;	
			}	
		}		
		return null;
		
	}

	/*
	 * public Periodo default1CalificarPorCurso(final @Named("Ciclo: ")
	 * Calificaciones inCalificacion, final @Named("Periodo: ") Periodo
	 * inPeriodo) { if (choices1CalificarPorCurso(inCalificacion,
	 * inPeriodo).isEmpty()) { return null; } return
	 * choices1CalificarPorCurso(inCalificacion, inPeriodo).get(0); }
	 */

	// VERIFICAR FUNCIONAMIENTO DE CHOICES
	public List<Curso> choices2CalificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion,
			final @Named("Periodo: ") Periodo inPeriodo,
			final @Named("Curso: ") Curso inCurso,
			final @Named("Materia: ") MateriaDelCurso inMateria) {
		List<Curso> cursos = new ArrayList<Curso>();
		if (inPeriodo != null) {
			cursos = aluCalRepositorio.listCursoPorPeriodo(inPeriodo);
		}

		if (cursos.isEmpty()) {
			return null;
		}

		return cursos;
	}

	/*
	 * public Curso default2CalificarPorCurso(final @Named("Periodo: ") Periodo
	 * inPeriodo){ if(choices2CalificarPorCurso(inPeriodo).isEmpty()){ return
	 * null; } return choices2CalificarPorCurso(inPeriodo).get(0); }
	 */

	public List<MateriaDelCurso> choices3CalificarPorCurso(
			final @Named("Ciclo: ") Calificaciones inCalificacion,
			final @Named("Periodo: ") Periodo inPeriodo,
			final @Named("Curso: ") Curso inCurso,
			final @Named("Materia: ") MateriaDelCurso inMateria) {

		if (inCurso != null) {
			if (!inCurso.getMateriaDelCursoList().isEmpty()) {
				return inCurso.getMateriaDelCursoList();
			}
		}
		return null;

	}

	/*
	 * public MateriaDelCurso default3CalificarPorCurso(Curso curso){
	 * if(choices3CalificarPorCurso(curso).isEmpty()){ return null; } return
	 * choices3CalificarPorCurso(curso).get(0); }
	 */

	@javax.inject.Inject
	DomainObjectContainer container;

	@javax.inject.Inject
	CursoRepositorio cursoRepositorio;
	MateriaCalificacionRepositorio mcRepositorio;
	@javax.inject.Inject
	MementoService mementoService;

	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluCalRepositorio;

	@javax.inject.Inject
	CalificacionRepositorio califRepositorio;

	@javax.inject.Inject
	PeriodoRepositorio perRepo;
}
