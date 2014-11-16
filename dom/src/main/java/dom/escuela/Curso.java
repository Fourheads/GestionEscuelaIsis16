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

package dom.escuela;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dom.asistencia.AnalisisAsistenciaView;
import dom.asistencia.Asistencia;
import dom.asistencia.TomarAsistenciaService;
import dom.asistencia.TomarAsistenciaView;
import dom.escuela.Funcion.E_funciones;
import dom.horario.HorarioCurso;
import dom.horario.HorarioCursoService;
import dom.horario.HorarioCursoView;
import dom.planEstudio.Anio;

//import org.apache.isis.applib.annotation.Title;
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "todosLosCursos", language = "JDOQL", value = "SELECT FROM dom.simple.Curso"
				+ " order by anio.anioNumero asc, division asc"),
		@javax.jdo.annotations.Query(name = "listarCursosDeUnPlan", language = "JDOQL", value = "SELECT "
				+ "FROM dom.simple.Curso "
				+ "WHERE this.anio.plan.descripcion == :plan "
				+ "ORDER BY this.anio.anioNumero asc, this.division asc"),
		@javax.jdo.annotations.Query(name = "listarCursosDeUnAnio", language = "JDOQL", value = "SELECT "
				+ "FROM dom.simple.Curso "
				+ "WHERE this.anio.plan.descripcion == :plan "
				+ "&& this.anio.anioNumero == :anio "
				+ "ORDER BY this.division asc"),
		@javax.jdo.annotations.Query(name = "buscarUnCurso", language = "JDOQL", value = "SELECT "
				+ "FROM dom.simple.Curso "
				+ "WHERE this.anio.plan.descripcion == :plan "
				+ "&& this.anio.anioNumero == :anio "
				+ "&& this.division == :division"),
		@javax.jdo.annotations.Query(name = "buscarCursoConAlumnos", language = "JDOQL", value = "SELECT " +
				"FROM dom.simple.Curso " +
				"WHERE this.ListaAlumno != null")
})
@Bounded
@PersistenceCapable
@MemberGroupLayout(columnSpans = { 4, 0, 0, 8 }, left = {"Datos Del Curso", "Asistencia"} )
public class Curso {

	// {{ Division (property)
	private String division;

	@Persistent
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.2", name = "Datos Del Curso")
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	// }}
	public String title() {
		String titulo = getAnio().getAnioNumero() + "° " + getDivision()
				+ " - " + getAnio().getPlan().getDescripcion() + " T."
				+ getTurno().toString().substring(0, 1) + ".";
		return titulo;
	}

	// {{ Turno (property)
	private Turno turno;

	@Persistent
	// @Title
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.3", name = "Datos Del Curso")
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	// }}

	// {{ Anio (property)
	private Anio anio;

	@MemberOrder(sequence = "1", name = "Datos Del Curso")
	@Column(allowsNull = "true")
	public Anio getAnio() {
		return anio;
	}

	public void setAnio(final Anio anio) {
		this.anio = anio;
	}

	// {{ HorarioCurso (property)
	private HorarioCurso horarioCurso;

	@Hidden
	@MemberOrder(sequence = "1", name = "Asistencia")
	@Column(allowsNull = "true")
	public HorarioCurso getHorarioCurso() {
		return horarioCurso;
	}

	public void setHorarioCurso(final HorarioCurso horarioCurso) {
		this.horarioCurso = horarioCurso;
	}

	// }}

	// {{ Alumnos (Property)
	@Join
	@Persistent(mappedBy = "curso", dependentElement = "false")
	private SortedSet<Alumno> ListaAlumno = new TreeSet<Alumno>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1.5")
	@Named("Listado de Alumnos")
	public SortedSet<Alumno> getAlumnos() {
		return ListaAlumno;
	}

	public void setAlumnos(final SortedSet<Alumno> listaAlumno) {
		this.ListaAlumno = listaAlumno;
	}

	// MateriaDelCursoList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "curso", dependentElement = "true")
	@Join
	private List<MateriaDelCurso> materiaDelCursoList = new ArrayList<MateriaDelCurso>();

	@MemberOrder(sequence = "2")
	@Render(Type.EAGERLY)
	@Named("Listado de Materias y Profesores")
	public List<MateriaDelCurso> getMateriaDelCursoList() {
		return materiaDelCursoList;
	}

	public void setMateriaDelCursoList(
			final List<MateriaDelCurso> materiaDelCursoList) {
		this.materiaDelCursoList = materiaDelCursoList;
	}

	// end region MateriaDelCursoList (Collection)

	@MemberOrder(sequence = "3", name = "Listado de Alumnos")
	@Named("Asignar alumnos al Curso")
	public Curso asignarAlumnos(@Named("Alumno") Alumno alumno) {
		this.ListaAlumno.add(alumno);
		return this;
	}

	public List<Alumno> choices0AsignarAlumnos() {
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosSinCurso"));
	}

	public Alumno default0AsignarAlumnos() {
		if (!choices0AsignarAlumnos().isEmpty()) {
			return choices0AsignarAlumnos().get(0);
		}
		return null;
	}

	@MemberOrder(sequence = "3.4", name = "Listado de Alumnos")
	@Named("Quitar alumnos del curso")
	public Curso removeAlumno(final @Named("Alumno") Alumno alumno) {

		getAlumnos().remove(alumno);
		return this;
	}

	public SortedSet<Alumno> choices0RemoveAlumno() {
		return getAlumnos();
	}

	// {{ Preceptor (property)
	private Personal preceptor;

	@Persistent
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.6", name = "Datos Del Curso")
	public Personal getPreceptor() {
		return preceptor;
	}

	public void setPreceptor(Personal Preceptor) {
		this.preceptor = Preceptor;
	}

	// {{ asignarPreceptor (action) Desde un curso
	@MemberOrder(sequence = "1", name = "preceptor")
	public Curso asignarPreceptor(final Personal preceptor) {
		this.setPreceptor(preceptor);
		return this;
	}

	public List<Personal> choices0AsignarPreceptor() {
		return personalRepositorio
				.listarPersonalSegunFuncion(E_funciones.PRECEPTOR);
	}

	// }}

	// {{ quitarPreceptor (action)
	@MemberOrder(sequence = "2", name = "preceptor")
	public Curso quitarPreceptor(@Named("¿Está seguro?") Boolean seguro) {
		this.setPreceptor(null);
		return this;
	}

	// }}

	// {{ asignarProfesorAMateria (action)
	@MemberOrder(sequence = "1", name = "Listado de Materias y Profesores")
	public Curso asignarProfesorAMateria(
			@Named("Materia") final MateriaDelCurso materia,
			@Named("Profesor") final Personal profesor) {
		materia.setProfesor(profesor);
		return this;
	}

	public List<MateriaDelCurso> choices0AsignarProfesorAMateria() {
		return this.getMateriaDelCursoList();
	}

	public List<Personal> choices1AsignarProfesorAMateria() {

		return personalRepositorio
				.listarPersonalSegunFuncion(E_funciones.PROFESOR);
	}

	
	private char habilitado;
	
	@Persistent
	@javax.jdo.annotations.Column(allowsNull = "true")
	@Hidden
	public char getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(char habilitado) {
		this.habilitado = habilitado;
	}

	
	public String validateAsignarProfesorAMateria(
			@Named("Materia") final MateriaDelCurso materia,
			@Named("Profesor") final Personal profesor) {
		if (materia.getProfesor() != null) {
			return "La materia " + materia.getMateria().getNombre()
					+ " ya tiene asignado un profesor";
		}
		return null;
	}

	// }}

	// {{ asignarProfesorAMateria (action)
	@MemberOrder(sequence = "2", name = "Listado de Materias y Profesores")
	public Curso quitarProfesorDeMateria(
			@Named("Materia") final MateriaDelCurso materia,
			@Named("¿Está seguro?") final Boolean seguro) {

		materia.setProfesor(null);
		return this;
	}

	@MemberOrder(sequence = "5", name = "Datos Del Curso")
	public HorarioCursoView verHorarioDeCurso() {
		return horarioCursoService.verHorarioDeCurso(this);
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	CursoRepositorio cursoRepositorio;
	@javax.inject.Inject
	PersonalRepositorio personalRepositorio;
	@javax.inject.Inject
	HorarioCursoService horarioCursoService;

}
