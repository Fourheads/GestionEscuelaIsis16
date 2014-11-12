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

package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

import dom.simple.SimpleObject;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
		+ "FROM dom.planEstudio.Anio " + "WHERE this.plan.descripcion == :plan") })
@ObjectType("ANIO")
@Bookmarkable
@Bounded
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 }, left = "Información del Año del Plan")
public class Anio implements Comparable<Anio> {

	// {{ AnioNumero (property)
	private int anioNumero;

	@MemberOrder(sequence = "1", name = "Información del Año del Plan")
	@Column(allowsNull = "true")
	public int getAnioNumero() {
		return anioNumero;
	}

	public void setAnioNumero(final int anioNumero) {
		this.anioNumero = anioNumero;
	}

	// }}

	// {{ Plan (property)
	private Plan plan;

	@MemberOrder(sequence = "2", name = "Información del Año del Plan")
	@Column(allowsNull = "true")
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(final Plan plan) {
		this.plan = plan;
	}

	// }}

	// MateriaList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "anio", dependentElement = "true")
	@Join
	private List<Materia> collectionName = new ArrayList<Materia>();

	@Named("Listado de Materias del Año")
	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<Materia> getMateriaList() {
		return collectionName;
	}

	public void setMateriaList(final List<Materia> collectionName) {
		this.collectionName = collectionName;
	}

	// end region MateriaList (Collection)

	// {{ agregarMateriaAlPlan (action)
	@MemberOrder(sequence = "1", name = "Listado de Materias del Año")
	@Named("Agregar materia al Año")
	public Anio agregarMateriaAlAnio(
			@Named("Nombre de la materia") final String nombreMateria, final @MaxLength(2048)
	  		@MultiLine @Named("Programa") String programa ) {
		materiaRepositorio.agregarMateria(this, nombreMateria, programa);
		return this;
	}

	public String validateAgregarMateriaAlAnio(final String nombreMateria, final String programa) {
		List<Materia> listadoMaterias = this.getMateriaList();

		for (Materia materia : listadoMaterias) {
			if (materia.getNombre().equals(nombreMateria)) {
				return "La materia " + nombreMateria + " ya fué creada";
			}
		}

		return null;
	}

	// }}

	// {{ quitarMateriaDelAño (action)
	@MemberOrder(sequence = "2", name = "Listado de Materias del Año")
	public Anio quitarMateriaDelAño(final Materia materia,
			@Named("¿Está seguro?") Boolean seguro) {

		materiaRepositorio.eliminarMateria(this, materia, seguro);
		return this;
	}

	public List<Materia> choices0QuitarMateriaDelAño() {
		return this.getMateriaList();
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

	
	// }}

	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return getAnioNumero() + "° (" + getPlan().getDescripcion() + ")";
	}

	@Override
	public int compareTo(Anio o) {

		return ObjectContracts.compare(this, o, "anioNumero");
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

	@javax.inject.Inject
	MateriaRepositorio materiaRepositorio;

}
