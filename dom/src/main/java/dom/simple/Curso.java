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

package dom.simple;

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
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;

import dom.planEstudio.Anio;
import dom.simple.Funcion.E_funciones;

//import org.apache.isis.applib.annotation.Title;
@javax.jdo.annotations.Queries({
		@javax.jdo.annotations.Query(name = "todosLosCursos", language = "JDOQL", value = "SELECT FROM dom.simple.Curso"
				+ " order by anio.anioNumero asc, division asc"),
		@javax.jdo.annotations.Query(name = "listarCursosDeUnPlan", language = "JDOQL", value = "SELECT "
				+ "FROM dom.simple.Curso "
				+ "WHERE this.anio.plan.descripcion == :plan"),
		@javax.jdo.annotations.Query(name = "listarCursosDeUnAnio", language = "JDOQL", value = "SELECT "
				+ "FROM dom.simple.Curso "
				+ "WHERE this.anio.plan.descripcion == :plan "
				+ "&& this.anio.anioNumero == :anio") })
@Bounded
@PersistenceCapable
public class Curso {

	// {{ Division (property)
	private String division;

	@Persistent
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.2")
	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	// }}
	public String title() {
		String titulo = getAnio().getAnioNumero() + "° " + getDivision() 
				+ " (" + getAnio().getPlan().getDescripcion() + ") Turno " + getTurno();
		return titulo;
	}

	// {{ Turno (property)
	private Turno turno;

	@Persistent
	// @Title
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.3")
	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	// }}

	// {{ Anio (property)
	private Anio anio;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Anio getAnio() {
		return anio;
	}

	public void setAnio(final Anio anio) {
		this.anio = anio;
	}

	// }}

	// }}

	// // {{ Materias (Property)
	// @Join
	// @Element(dependent = "false")
	// private SortedSet<Materia> ListaMateria = new TreeSet<Materia>();
	//
	// @Render(Type.EAGERLY)
	// @MemberOrder(sequence = "1.4")
	// public SortedSet<Materia> getListaMateria() {
	// return ListaMateria;
	// }
	//
	// public void setListaMateria(SortedSet<Materia> ListaMateria) {
	// this.ListaMateria = ListaMateria;
	// }
	//
	// @MemberOrder(sequence = "2")
	// @Named("Asinganar materias")
	// public void asignarMateria(@Named("Materia") Materia materia) {
	// this.ListaMateria.add(materia);
	// }
	//
	// public List<Materia> choices0AsignarMateria() {
	// return container.allInstances(Materia.class);
	// }
	//
	// @MemberOrder(sequence = "4")
	// @Named("Quitar materias del curso")
	// public Curso removeMateria(final @Named("Materia") Materia materia) {
	//
	// getListaMateria().remove(materia);
	// return this;
	// }
	//
	// public SortedSet<Materia> choices0RemoveMateria() {
	// return getListaMateria();
	// }

	// }}

	// {{ Alumnos (Property)
	@Join
	@Persistent(mappedBy = "curso", dependentElement = "false")
	private SortedSet<Alumno> ListaAlumno = new TreeSet<Alumno>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1.5")
	public SortedSet<Alumno> getAlumnos() {
		return ListaAlumno;
	}

	public void setAlumnos(final SortedSet<Alumno> listaalumno) {
		this.ListaAlumno = listaalumno;
	}

	// MateriaDelCursoList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "curso", dependentElement = "true")
	@Join
	private List<MateriaDelCurso> materiaDelCursoList = new ArrayList<MateriaDelCurso>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<MateriaDelCurso> getMateriaDelCursoList() {
		return materiaDelCursoList;
	}

	public void setMateriaDelCursoList(
			final List<MateriaDelCurso> materiaDelCursoList) {
		this.materiaDelCursoList = materiaDelCursoList;
	}

	// end region MateriaDelCursoList (Collection)

	@MemberOrder(sequence = "3")
	@Named("Asinganar alumnos")
	public Curso asignarAlumnos(@Named("Alumno") Alumno alumno) {
		this.ListaAlumno.add(alumno);
		return this;
	}

	public List<Alumno> choices0AsignarAlumnos() {
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosSinCurso"));
	}

	public Alumno default0AsignarAlumnos() {
		return choices0AsignarAlumnos().get(0);
	}

	@MemberOrder(sequence = "3.4")
	@Named("Quitar alumnos del curso")
	public Curso removeAlumno(final @Named("Alumno") Alumno alumno) {

		getAlumnos().remove(alumno);
		return this;
	}

	public SortedSet<Alumno> choices0RemoveAlumno() {
		return getAlumnos();
	}

	// }}

	/*
	 * private List<Alumno> alumnos;
	 * 
	 * //@Title
	 * 
	 * @Column(allowsNull = "true")
	 * 
	 * @Element(column="Alumno_id")
	 * 
	 * @MemberOrder(sequence = "1.5") public List<Alumno> getAlumnos() { return
	 * alumnos; } public void setAlumnos(final List<Alumno> Alumnos) {
	 * this.alumnos = Alumnos; } public void addAlumno(Alumno NuevaAlumno){
	 * this.alumnos.add(NuevaAlumno); } //}}
	 */

	// {{ Preceptor (property)
	private Personal preceptor;

	@Persistent
	// @Title
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.6")
	public Personal getPreceptor() {
		return preceptor;
	}

	public void setPreceptor(Personal Preceptor) {
		this.preceptor = Preceptor;
	}

	@MemberOrder(sequence = "1.7")
	@Named("Asinganar preceptor")
	public void asignarPreceptor(final @Named("Preceptor") Personal prece) {
		this.preceptor = prece;
	}

	public List<Personal> choices0AsignarPreceptor() {
		return container.allMatches(new QueryDefault<Personal>(Personal.class,
				"findByFuncion", "nombre", E_funciones.PRECEPTOR.toString()));
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
