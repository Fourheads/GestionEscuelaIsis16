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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

//import MateriaCalificacion;
import dom.calificacion.Periodo;
import dom.escuela.Alumno;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "findAlumnoCalificacionPorAlumnoPorPeriodo", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.AlumnoCalificacion"
					+" WHERE this.periodo.nombre == :periodo &&" +
					" this.alumno.dni == :dni &&" +
					" this.habilitado == 'S'"),
	@javax.jdo.annotations.Query(name = "findAlumnoCalificacionPorPeriodo", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.AlumnoCalificacion"
					+" WHERE this.periodo.nombre == :periodo && this.habilitado == 'S'" +
					" order by this.alumno.apellido dsc"),
	@javax.jdo.annotations.Query(name = "findAlumnoCalificacionPorAlumno", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.AlumnoCalificacion"
					+" WHERE this.alumno.dni == :dni && this.habilitado == 'S'"),
	@javax.jdo.annotations.Query(name = "findAlumnoCalificacionPorPeriodoPorCursoPorMateria", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.AlumnoCalificacion"
							+" WHERE this.periodo.nombre == :periodo &&" +
							" this.alumno.curso.anio.plan.descripcion == :plan &&" +
							" this.alumno.curso.anio.anioNumero == :anio &&" +
							" this.alumno.curso.division == :division &&" +								
							" this.listMateriaCalificacion.contains(matCal) &&" +
							" matCal.materiaDelCurso.materia.nombre == :materia) && this.habilitado == 'S'"),
	@javax.jdo.annotations.Query(name = "findAlumnoCalificacionPorPeriodoPorCurso", 
			language = "JDOQL", 
			value = "SELECT FROM dom.simple.AlumnoCalificacion"
							+" WHERE this.periodo.nombre == :periodo &&" +							
							" this.alumno.curso.anio.anioNumero == :anio &&" +
							" this.alumno.curso.division == :division && this.habilitado == 'S'")
})
@Bookmarkable
@Bounded
public class AlumnoCalificacion {
	
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
	
	// {{ Alumno (property)
	private Alumno alumno;

	@Named("Alumno")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "1")
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}
	// }}
	
	// {{ ListMateriaCalificacion (property)
	@Join
	@Element(mappedBy = "alumnoCalificacion", dependent = "true")
	private List<MateriaCalificacion> listMateriaCalificacion = new ArrayList<MateriaCalificacion>();

	@Named("Materias")	       
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "2")
	public List<MateriaCalificacion> getListMateriaCalificacion() {
		//Probar con un iterador
		final List<MateriaCalificacion> mat = new ArrayList<MateriaCalificacion>();
		for(MateriaCalificacion m: listMateriaCalificacion){
			if(m.getHabilitado() == 'S'){
				mat.add(m);
			}
		}
		return mat;
	}

	public void setListMateriaCalificacion(final List<MateriaCalificacion> listMateriaCalificacion) {
		this.listMateriaCalificacion = listMateriaCalificacion;
	}
	// }}

    // {{ Periodo (property)
	private Periodo periodo;

	@Named("Instancia")	
	@Column(allowsNull = "true")
	@MemberOrder(sequence = "3")
	public Periodo getPeriodo() {
		
		return periodo;
	}

	public void setPeriodo(final Periodo periodo) {
		this.periodo = periodo;
	}
	// }}
	
	public String title(){
		return getAlumno().title();
	}
}
