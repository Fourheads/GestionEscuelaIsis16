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

package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.joda.time.LocalDate;

import dom.libroDiario.HoraCatedra;

@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "TodasEntradaLibroDiarioList", language = "JDOQL", value = "SELECT FROM dom.libroDiario.EntradaLibroDiario"),
	@javax.jdo.annotations.Query(name = "EntradaLibroDiarioListFecha", language = "JDOQL", value = "SELECT FROM dom.libroDiario.EntradaLibroDiario "
			+ "WHERE this.Fecha == :Fecha"
			+"&& this.materiaDelLibroDiario.materiaDelCurso == :materiacurso"),
			@javax.jdo.annotations.Query(name = "EntradaLibroDiario", language = "JDOQL", value = "SELECT FROM dom.libroDiario.EntradaLibroDiario "
					+ "WHERE this.materiaDelCurso == :materiacurso")})

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("ENTRADA_LIBRO_DIARIO")
@Bookmarkable
@Bounded
public class EntradaLibroDiario {
	
	// {{ Fecha (property)
	private LocalDate Fecha;

	@MemberOrder(sequence = "1")
	@Column(allowsNull="true")
	public LocalDate getFecha() {
		return Fecha;
	}

	public void setFecha(final LocalDate Fecha) {
		this.Fecha = Fecha;
	}
	// }}

	// {{ HoraCatedra (Collection)
	@Join
	@Persistent(mappedBy = "entradaLibroDiario", dependentElement = "true")
	private List<HoraCatedra> horacatedra = new ArrayList<HoraCatedra>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<HoraCatedra> getHoraCatedra() {
		return horacatedra;
	}

	public void setHoraCatedra(final List<HoraCatedra> horacatedra) {
		this.horacatedra = horacatedra;
	}

	public void asignarHoracatedra(HoraCatedra horacate) {
		this.horacatedra.add(horacate);
		
	}
	
	// }}

	// {{ materiaDelLibroDiario (property)
	private MateriaDelLibroDiario materiaDelLibroDiario;

	@MemberOrder(sequence = "1")
	public MateriaDelLibroDiario getmateriaDelLibroDiario() {
		return materiaDelLibroDiario;
	}

	public void setmateriaDelLibroDiario(final MateriaDelLibroDiario materiaDelLibroDiario) {
		this.materiaDelLibroDiario = materiaDelLibroDiario;
	}
	// }}


	public String title()
	{
		return "Entrada Libro diario";
	}

	
}
