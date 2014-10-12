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
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.simple.Curso;

@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "TodosLibroDiario", language = "JDOQL", value = "SELECT FROM dom.libroDiario.LibroDiario "),
			@javax.jdo.annotations.Query(name = "LibroDiarioDeUnCurso", language = "JDOQL", value = "SELECT FROM dom.libroDiario.LibroDiario "
					+ "WHERE this.curso.anio.plan.descripcion == :plan "
					+ "&& this.curso.anio.anioNumero == :anio"
					+ "&& this.curso.division == :division")})
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("LIBRO_DIARIO")
@Bookmarkable
@Bounded
public class LibroDiario {
	
	// {{ Curso (property)
	private Curso curso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}

	// MateriaDelLibroDiarioList (Collection)
	// //////////////////////////////////////////
	
	@Persistent(mappedBy = "libroDiario", dependentElement = "true")
	@Join
	private List<MateriaDelLibroDiario> materiaDelLibroDiarioList = new ArrayList<MateriaDelLibroDiario>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<MateriaDelLibroDiario> getMateriaDelLibroDiarioList() {
		return materiaDelLibroDiarioList;
	}

	public void setMateriaDelLibroDiarioList(final List<MateriaDelLibroDiario> materiaDelLibroDiarioList) {
		this.materiaDelLibroDiarioList = materiaDelLibroDiarioList;
	}

	public void asignarMateriadeLibro(MateriaDelLibroDiario materialibrodiario)
	{
		materiaDelLibroDiarioList.add(materialibrodiario);
	}
	// end region MateriaDelLibroDiarioList (Collection)

	// Title (GUI)
	// //////////////////////////////////////////

	
	// {{ tiutulo (property)
	private String Titulo;

	@MemberOrder(sequence = "1")
	public String gettiutulo() {
		return Titulo;
	}

	public void settiutulo(final String Titulo) {
		this.Titulo = Titulo;
	}
	// }}


	
	public String title() {
		return this.Titulo;
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

}
