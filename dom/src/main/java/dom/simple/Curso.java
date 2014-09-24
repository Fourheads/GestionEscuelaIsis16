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

import dom.simple.Funcion.E_funciones;

//import org.apache.isis.applib.annotation.Title;
@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "todosLosCursos", language = "JDOQL", 
			value = "SELECT FROM dom.asistencia.Curso"
			+ " order by anio asc, division asc"
			) })

@Bounded
@PersistenceCapable
public class Curso {

	// {{ Division (property)
	private String division;

	@Persistent
	// @Title
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
		String titulo = String.valueOf(getAnio()) + "° " + getDivision()
				+ " Turno:" + getTurno();
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
	private int anio;

	@Persistent
	// @Title
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1.1")
	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	// }}

	// {{ Materias (Property)
	@Join
	@Element(dependent = "false")
	private SortedSet<Materia> ListaMateria = new TreeSet<Materia>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1.4")
	public SortedSet<Materia> getListaMateria() {
		return ListaMateria;
	}

	public void setListaMateria(SortedSet<Materia> ListaMateria) {
		this.ListaMateria = ListaMateria;
	}

	@MemberOrder(sequence = "2")
	@Named("Asinganar materias")
	public void asignarMateria(@Named("Materia") Materia materia) {
		this.ListaMateria.add(materia);
	}

	public List<Materia> choices0AsignarMateria() {
		return container.allInstances(Materia.class);
	}

	@MemberOrder(sequence = "4")
	@Named("Quitar materias del curso")
	public Curso removeMateria(final @Named("Materia") Materia materia) {

		getListaMateria().remove(materia);
		return this;
	}

	public SortedSet<Materia> choices0RemoveMateria() {
		return getListaMateria();
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ListaAlumno == null) ? 0 : ListaAlumno.hashCode());
		result = prime * result
				+ ((ListaMateria == null) ? 0 : ListaMateria.hashCode());
		result = prime * result + anio;
		result = prime * result
				+ ((division == null) ? 0 : division.hashCode());
		result = prime * result
				+ ((preceptor == null) ? 0 : preceptor.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (ListaAlumno == null) {
			if (other.ListaAlumno != null)
				return false;
		} else if (!ListaAlumno.equals(other.ListaAlumno))
			return false;
		if (ListaMateria == null) {
			if (other.ListaMateria != null)
				return false;
		} else if (!ListaMateria.equals(other.ListaMateria))
			return false;
		if (anio != other.anio)
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (preceptor == null) {
			if (other.preceptor != null)
				return false;
		} else if (!preceptor.equals(other.preceptor))
			return false;
		if (turno != other.turno)
			return false;
		return true;
	}

	@javax.inject.Inject
	DomainObjectContainer container;

}
