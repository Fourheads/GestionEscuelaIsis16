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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

import dom.simple.MateriaDelCurso;

@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "TodasMateriadelLibroDiarioList", language = "JDOQL", value = "SELECT FROM dom.libroDiario.MateriaDelLibroDiario"),
	@javax.jdo.annotations.Query(name = "LibroDiarioMateriadelLibroDiarioList", language = "JDOQL", value = "SELECT FROM dom.libroDiario.MateriaDelLibroDiario "
			+ "WHERE this.libroDiario == :libroDiario"),
			@javax.jdo.annotations.Query(name = "MateriadelLibroDiario", language = "JDOQL", value = "SELECT FROM dom.libroDiario.MateriaDelLibroDiario "
					+ "WHERE this.materiaDelCurso == :materiacurso")})
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("MATERIAS_DEL_DIARIO")
@Bookmarkable
@Bounded
public class MateriaDelLibroDiario {
	
	// {{ Librodiario (property)
	private LibroDiario libroDiario;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(final LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}
	
	// }}
	
	// {{ MateriaDelCurso (property)
	private MateriaDelCurso materiaDelCurso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public MateriaDelCurso getMateriaDelCurso() {
		return materiaDelCurso;
	}

	public void setMateriaDelCurso(final MateriaDelCurso materiaDelCurso) {
		this.materiaDelCurso = materiaDelCurso;
	}
	// }}

	// {{ EntradaLibroDiario (Collection)
	@Join
	@Persistent(mappedBy = "materiaDelLibroDiario", dependentElement = "true")
	private List<EntradaLibroDiario> entradalibrodiario = new ArrayList<EntradaLibroDiario>();

	@MemberOrder(sequence = "1")
	public List<EntradaLibroDiario> getEntradaLibroDiario() {
		return entradalibrodiario;
	}

	public void setEntradaLibroDiario(final List<EntradaLibroDiario> entradalibrodiario) {
		this.entradalibrodiario = entradalibrodiario;
	}
	
	public void AsignarEntradaLibroDiario(EntradaLibroDiario entradalibro)
	{
		entradalibrodiario.add(entradalibro);
	}
	// }}

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


}
