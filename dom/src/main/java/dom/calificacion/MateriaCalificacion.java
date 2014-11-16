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

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.util.ObjectContracts;

import dom.escuela.Alumno;
import dom.escuela.Curso;
import dom.escuela.MateriaDelCurso;
import dom.planEstudio.Materia;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorAlumno", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.MateriaCalificacion"
					+" WHERE this.alumno.dni == :dni &&" +
					" this.habilitado == 'S'"),
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorCursoPorMateria", 
			language = "JDOQL", 
			value = "SELECT FROM dom.calificacion.MateriaCalificacion " 
					+ "WHERE "
					+ "this.materiaDelCurso.curso.anio.anioNumero == :anio " 
					+ "&& this.materiaDelCurso.curso.anio.plan.descripcion == :plan " 
					+ "&& this.materiaDelCurso.curso.division == :division " 
					+ "&& this.materiaDelCurso.materia.nombre == :materia &&" +
					" this.habilitado == 'S'"
					),
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorMateria", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.MateriaCalificacion" +
					" WHERE this.materia.materia.nombre == :materia && this.habilitado == 'S'"),
	@javax.jdo.annotations.Query(name = "findMateriaCalificacionPorCursoPorMateriaPorPeriodo", 
			language = "JDOQL", 
			value = "SELECT FROM dom.calificacion.MateriaCalificacion " 
					+ "WHERE "
					+ "this.materiaDelCurso.curso.anio.anioNumero == :anio " 
					+ "&& this.materiaDelCurso.curso.anio.plan.descripcion == :plan " 
					+ "&& this.materiaDelCurso.curso.division == :division " 
					+ "&& this.materiaDelCurso.materia.nombre == :materia " 
					+ "&& this.alumnoCalificacion.periodo.nombre == :periodo" +
					" && this.habilitado == 'S' " +
					"order by this.alumno.apellido dsc"),
				
})

@Bookmarkable
@MemberGroupLayout(columnSpans = {12,0,0,12})
public class MateriaCalificacion implements Comparable<MateriaCalificacion>{
	
	// {{ Habilitado (property)
	private Character habilitado;
	
	@Column(allowsNull = "false")
	@Hidden
	@MemberOrder(sequence = "1")
	public Character getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(final Character habilitado) {
		this.habilitado = habilitado;
	}
	// }}


	
	// {{ AlumnoCalificacion (property)
	private AlumnoCalificacion alumnoCalificacion;
	
	@Hidden(where = Where.ALL_TABLES)
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public AlumnoCalificacion getAlumnoCalificacion() {
		return alumnoCalificacion;
	}

	public void setAlumnoCalificacion(final AlumnoCalificacion alumnoCalificacion) {
		this.alumnoCalificacion = alumnoCalificacion;
	}
	// }}


	
	// {{ Materia (property)
	private MateriaDelCurso materiaDelCurso;
	
	//@Hidden(where = Where.ALL_EXCEPT_STANDALONE_TABLES)
	@Named("Materia")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public MateriaDelCurso getMateriaDelCurso() {
		return materiaDelCurso;
	}

	public void setMateriaDelCurso(final MateriaDelCurso materia) {
		this.materiaDelCurso = materia;		
	}
	// }}
	
	// {{ Nota (property)
	private int nota;

	@Named("Nota")
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public int getNota() {
		return nota;
	}

	public void setNota(final int nota) {
		this.nota = nota;
	}
	// }}
	
	// {{ Alumno (property)
	private Alumno alumno;

	//@Hidden()
	@Named("Alumno")
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}
	// }}
	
	// {{ Observacion (property)
	private String observacion;

	@Named("Observación")
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "true")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(final String observacion) {
		this.observacion = observacion;
	}
	// }}

	
	public String title(){
		//return alumno.title() + "- Materia: " + materiaDelCurso.getMateria().getNombre() + "- Nota: " + String.valueOf(nota);
		return getAlumno().title();
	}

	@Override
	public int compareTo(MateriaCalificacion o) {
		return ObjectContracts.compare(this, o, "materia");
	}


	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	Curso curso;
}
