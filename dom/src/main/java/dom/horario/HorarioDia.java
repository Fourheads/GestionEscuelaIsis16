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
import java.util.Set;
import java.util.HashSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.util.ObjectContracts;
import org.omg.CORBA.Object;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
// @javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name =
// "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
// + "FROM dom.planEstudio.Anio "
// + "WHERE this.plan.descripcion == :descripcion") })
@ObjectType("HORARIO_DIA")
@Bookmarkable
@Bounded
public class HorarioDia implements Comparable<HorarioDia>{
	// {{ DiaDeLaSemana (property)
	private E_HorarioDiaSemana diaDeLaSemana;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public E_HorarioDiaSemana getDiaDeLaSemana() {
		return diaDeLaSemana;
	}

	public void setDiaDeLaSemana(final E_HorarioDiaSemana diaDeLaSemana) {
		this.diaDeLaSemana = diaDeLaSemana;
	}

	// }}

	// HorarioHoraList (Collection)
	// //////////////////////////////////////////

	@Persistent(mappedBy = "horarioDia", dependentElement = "true")
	@Join
	private List<HorarioHora> horarioHoraList = new ArrayList<HorarioHora>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<HorarioHora> getHorarioHoraList() {
		return horarioHoraList;
	}

	public void setHorarioHoraList(final List<HorarioHora> horarioHoraList) {
		this.horarioHoraList = horarioHoraList;
	}
	// end region HorarioHoraList (Collection)

	
	// {{ HorarioCurso (property)
	private HorarioCurso horarioCurso;

	@Hidden(where = Where.ALL_TABLES)
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioCurso getHorarioCurso() {
		return horarioCurso;
	}

	public void setHorarioCurso(final HorarioCurso horarioCurso) {
		this.horarioCurso = horarioCurso;
	}
	// }}

	// {{ OrdenDias (property)
	private int ordenDias;

	@Hidden
	@Column(allowsNull = "true")
	public int getOrdenDias() {
		return ordenDias;
	}

	public void setOrdenDias(final int ordenDias) {
		this.ordenDias = ordenDias;
	}
	// }}


	
	@Override
	public int compareTo(HorarioDia o) {
		return ObjectContracts.compare(this, o, "ordenDias");
	}

	public String title(){
		return getDiaDeLaSemana()+ " [" + getHorarioCurso().getCurso().title()+ "]";
	}

}
