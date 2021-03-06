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
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@javax.jdo.annotations.Queries({ 
	@javax.jdo.annotations.Query(name = "listarHorasDeUnPlan", language = "JDOQL", value = "SELECT "
			+ "FROM dom.horario.HorarioPlanHora "
			+ "WHERE this.horarioPlan.plan.descripcion == :plan "
			+ "&& this.tipoHoraPlan == 'HORA_CATEDRA' "
			+ "ORDER BY this.horaInicio.hora asc, this.horaFin.hora asc") })
@ObjectType("HORARIO_PLAN_HORA")
@Bookmarkable
@Bounded
public class HorarioPlanHora {

	// {{ HorarioPlan (property)
	private HorarioPlan horarioPlan;

	@MemberOrder(sequence = "10")
	@Column(allowsNull = "true")
	public HorarioPlan getHorarioPlan() {
		return horarioPlan;
	}

	public void setHorarioPlan(final HorarioPlan horarioPlan) {
		this.horarioPlan = horarioPlan;
	}

	// }}

	// {{ HoraInicio (property)
	private Hora horaInicio;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Hora getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(final Hora horaInicio) {
		this.horaInicio = horaInicio;
	}

	// }}

	// {{ HoraFin (property)
	private Hora horaFin;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Hora getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(final Hora horaFin) {
		this.horaFin = horaFin;
	}

	// }}

	// {{ TipoHoraPlan (property)
	private E_HorarioHoraTipo tipoHoraPlan;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public E_HorarioHoraTipo getTipoHoraPlan() {
		return tipoHoraPlan;
	}

	public void setTipoHoraPlan(final E_HorarioHoraTipo tipoHoraPlan) {
		this.tipoHoraPlan = tipoHoraPlan;
	}
	// }}
	
	public String title(){
		
		return getHoraInicio().title() + " a " + getHoraFin().title();
	}

	@Override
	public String toString() {
		return horaInicio + " a " + horaFin ;
	}

	
}
