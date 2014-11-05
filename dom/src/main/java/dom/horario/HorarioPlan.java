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

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.planEstudio.Plan;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
// @javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name =
// "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
// + "FROM dom.planEstudio.Anio "
// + "WHERE this.plan.descripcion == :descripcion") })
@ObjectType("HORARIO_PLAN")
@Bookmarkable
public class HorarioPlan {

	// {{ Plan (property)
	private Plan plan;
	@Disabled
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(final Plan plan) {
		this.plan = plan;
	}

	// }}

	// HorarioPlanHoraList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "horarioPlan", dependentElement = "true")
	@Join
	private List<HorarioPlanHora> horarioPlanHoraList = new ArrayList<HorarioPlanHora>();
	@Disabled
	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<HorarioPlanHora> getHorarioPlanHoraList() {
		return horarioPlanHoraList;
	}

	public void setHorarioPlanHoraList(
			final List<HorarioPlanHora> horarioPlanHoraList) {
		this.horarioPlanHoraList = horarioPlanHoraList;
	}

	// end region HorarioPlanHoraList (Collection)

	// {{ InicioClases (property)
	private Hora inicioClases;
	@Disabled
	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Hora getInicioClases() {
		return inicioClases;
	}

	public void setInicioClases(final Hora inicioClases) {
		this.inicioClases = inicioClases;
	}

	// }}

	public String title() {

		return "Horario de " + getPlan().getDescripcion();
	}

}
