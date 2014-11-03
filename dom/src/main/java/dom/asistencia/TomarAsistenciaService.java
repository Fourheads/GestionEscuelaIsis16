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

package dom.asistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import dom.simple.Alumno;
import dom.simple.AlumnoRepositorio;
import dom.simple.Curso;


@Named("Tomar Asistencia")
@DomainService
public class TomarAsistenciaService {

	// region > identification in the UI
	@Hidden
	public String getId() {
		return "tomarAsistencia";
	}

	public String title() {
		return "Tomar Asistencia";
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	@Named("Tomar Asistencia")
	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(name = "Asistencias", sequence = "12")
	public TomarAsistenciaView porCurso(
			@Named("Esquema") final Asistencia asistencia,
			@Named("Curso") Curso curso, @Named("Fecha") LocalDate fecha) {
		
		Memento memento = mementoService.create();
		
		memento.set("titulo", "Tomar asistencia");
		memento.set("asistencia", asistencia.getDescripcion());
		memento.set("fecha", fecha);
		memento.set("anio", curso.getAnio().getAnioNumero());		
		memento.set("division", curso.getDivision());
		memento.set("alumnoActivo", 0);
		
		final TomarAsistenciaView tomarAsistenciaView = container
				.newViewModelInstance(TomarAsistenciaView.class, memento.asString());

		return tomarAsistenciaView;

	}

	public List<Curso> choices1PorCurso() {
		return container.allMatches(new QueryDefault<Curso>(Curso.class,
				"todosLosCursos"));
	}

	public Curso default1PorCurso() {
		if (choices1PorCurso().isEmpty()) {
			return null;
		}
		return choices1PorCurso().get(0);
	}

	public List<Asistencia> choices0PorCurso() {
		return container.allInstances(Asistencia.class);
	}

	public Asistencia default0PorCurso() {
		if (choices0PorCurso().isEmpty()) {
			return null;
		}
		return choices0PorCurso().get(0);
	}

	public String validatePorCurso(Asistencia asistencia, Curso curso,
			LocalDate fecha) {
		List<AsistenciaDia> asistenciaDiaList = asistenciaDiaRepositorio.porFechaParaUnEsquema(fecha, asistencia);

		List<Alumno> listadoAlumnos = alumnoRepositorio.queryListarAlumnosDeUnCurso(curso.getAnio().getAnioNumero(), curso.getDivision());

		if (asistenciaDiaList.isEmpty()) {
			return "No existe asistencia creada para ese dia en este esquema de asistencia";
		}

		if (listadoAlumnos.isEmpty()) {
			return "El curso seleccionado no posee alumnos";
		}

		return null;
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	AsistenciaDiaRepositorio asistenciaDiaRepositorio;
	@javax.inject.Inject
	AlumnoRepositorio alumnoRepositorio;
	
	// endregion
}
