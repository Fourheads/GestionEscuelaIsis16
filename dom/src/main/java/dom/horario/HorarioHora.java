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

import dom.simple.MateriaDelCurso;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Where;

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
@ObjectType("HORARIO_HORA")
@Bookmarkable
@Bounded
public class HorarioHora {
	// {{ HorarioDia (property)
	private HorarioDia horarioDia;

	@Hidden(where = Where.ALL_TABLES)
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioDia getHorarioDia() {
		return horarioDia;
	}

	public void setHorarioDia(final HorarioDia horarioDia) {
		this.horarioDia = horarioDia;
	}

	// }}

	// {{ MateriaDelCurso (property)
	private MateriaDelCurso materiaDelCurso;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public MateriaDelCurso getMateriaDelCurso() {
		return materiaDelCurso;
	}

	public void setMateriaDelCurso(final MateriaDelCurso materiaDelCurso) {
		this.materiaDelCurso = materiaDelCurso;
	}
	// }}
	
	// {{ HorarioHoraTipo (property)
	private E_HorarioHoraTipo horarioHoraTipo;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public E_HorarioHoraTipo getHorarioHoraTipo() {
		return horarioHoraTipo;
	}

	public void setHorarioHoraTipo(final E_HorarioHoraTipo horarioHoraTipo) {
		this.horarioHoraTipo = horarioHoraTipo;
	}
	// }}

	// {{ HorarioPlanHora (property)
	private HorarioPlanHora horarioPlanHora;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioPlanHora getHorarioPlanHora() {
		return horarioPlanHora;
	}

	public void setHorarioPlanHora(final HorarioPlanHora horarioPlanHora) {
		this.horarioPlanHora = horarioPlanHora;
	}
	// }}
	
	public String title(){
		return getHorarioPlanHora().title();
	}


	
}
