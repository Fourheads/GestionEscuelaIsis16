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

package dom.horario;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
// @javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name =
// "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
// + "FROM dom.planEstudio.Anio "
// + "WHERE this.plan.descripcion == :descripcion") })
@ObjectType("HORA")
@Bookmarkable
@Bounded
public class Hora {

	// {{ Hora (property)
	private int hora;

	@Disabled
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public int getHora() {
		return hora;
	}

	public void setHora(final int hora) {
		this.hora = hora;
	}

	// }}

	// {{ Minutos (property)
	private int minutos;

	@Disabled
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(final int minutos) {
		this.minutos = minutos;
	}

	// }}

	public String title() {
		String relleno = "";
		if (getMinutos() < 10) {
			relleno = "0";
		}
		return getHora() + ":" + relleno + getMinutos();
	}

}
