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
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.util.ObjectContracts;

import dom.horario.HorarioPlan;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listarPlanes", language = "JDOQL", value = "SELECT "
		+ "FROM dom.planEstudio.Plan "
		+ "order by this.descripcion asc") })


@ObjectType("PLAN")
@Bookmarkable
@Bounded
public class Plan implements Comparable<Plan>{

	// {{ Descripcion (property)
	private String descripcion;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	// AnioList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "plan", dependentElement = "true")
	@Join
	private SortedSet<Anio> aniolist = new TreeSet<Anio>();
	@Named("Listado de Años del Plan")
	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public SortedSet<Anio> getAnioList() {
		return aniolist;
	}

	public void setAnioList(final SortedSet<Anio> aniolist) {
		this.aniolist = aniolist;
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

	
	
	// end region AnioList (Collection)
	// //////////////////////////////////////////
	
	// {{ HorarioPlan (property)
	private HorarioPlan horarioPlan;

	@Disabled
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioPlan getHorarioPlan() {
		return horarioPlan;
	}

	public void setHorarioPlan(final HorarioPlan horarioPlan) {
		this.horarioPlan = horarioPlan;
	}
	// }}


	
	
	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return getDescripcion();
	}

	@Override
	public int compareTo(Plan other) {
		// TODO Auto-generated method stub
		return ObjectContracts.compare(this, other, "descripcion");
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

}
