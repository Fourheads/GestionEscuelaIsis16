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

import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@ObjectType("CargarNotas")
public class CargarNotas {

	// {{ AlumnoActivo (property)
	private MateriaCalificacion alumnoActivo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public MateriaCalificacion getAlumnoActivo() {
		return alumnoActivo;
	}

	public void setAlumnoActivo(final MateriaCalificacion materiaCalificacion) {
		this.alumnoActivo = materiaCalificacion;
	}
	// }}
	
	// {{ ListMateriaCalificacion (property)
	@Join
	@Element(dependent = "false")
	private List<MateriaCalificacion> listMateriaCalificacion = new ArrayList<MateriaCalificacion>();
	
	@Disabled
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<MateriaCalificacion> getListMateriaCalificacion() {
		return listMateriaCalificacion;
	}

	public void setListMateriaCalificacion(final List<MateriaCalificacion> listMateriaCalificacion) {
		this.listMateriaCalificacion = listMateriaCalificacion;
	}
	// }}

}
