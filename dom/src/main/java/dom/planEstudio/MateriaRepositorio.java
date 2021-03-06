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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.query.QueryDefault;
import org.datanucleus.store.query.Query;

import dom.escuela.Curso;
import dom.escuela.CursoRepositorio;
import dom.escuela.MateriaDelCurso;
import dom.escuela.MateriaDelCursoRepositorio;
import dom.libroDiario.LibroDiarioRepositorio;
import dom.libroDiario.MateriaDelLibroDiario;

@Hidden
@DomainService(repositoryFor = Materia.class)
public class MateriaRepositorio {

	// {{ listarMateriasDeUnAnio (action)
	@MemberOrder(sequence = "2")
	@NotContributed
	@NotInServiceMenu
	public List<Materia> listarMateriasDeUnAnio(final @Named("Plan") Plan plan,
			final @Named("Año") Anio anio) {
		return filtroMa(container.allMatches(new QueryDefault<Materia>(Materia.class,
				"listarMateriasDeUnAnio", "anio", anio.getAnioNumero(), "plan",
				plan.getDescripcion())),'S');
	}

	public Plan default0ListarMateriasDeUnAnio() {
		List<Plan> tempList = container.allInstances(Plan.class);
		if (tempList.isEmpty()) {
			return null;
		}
		return tempList.get(0);
	}

	public SortedSet<Anio> choices1ListarMateriasDeUnAnio(Plan plan) {

		return plan.getAnioList();
	}

	// }}

	// region > agregarMateria
	// //////////////////////////////////////
	@Hidden
	@MemberOrder(sequence = "3")
	public Anio agregarMateria(final @Named("Año") Anio anio,
			final @Named("Nombre") String nombre, final @MaxLength(2048)
	  		@MultiLine @Named("Programa") String programa) {

		Materia materia = new Materia();
		materia.setNombre(nombre);
		materia.setPrograma(programa);
		materia.setHabilitado('S');
		anio.getMateriaList().add(materia);

		agregarMateriaCursosYaCreados(anio, materia);
		
		return anio;
	}

	// endregion > agregarMateria

	// region > eliminarMateria
	// //////////////////////////////////////
	@NotInServiceMenu
	@NotContributed
	@MemberOrder(sequence = "3")
	@DescribedAs(value = "Eliminar una Materia de este Año. Esto se aplica a todos los Cursos ya creados")
	public Anio eliminarMateria(final @Named("Año") Anio anio,
			final @Named("Nombre") Materia materia,
			final @Named("Esta seguro?") Boolean seguro) {
		
		eliminarMateriaCursosYaCreados(anio, materia);
		
		materia.setHabilitado('N');
		container.remove(materia);
		return anio;
	}

	public List<Materia> choices1EliminarMateria(final Anio anio) {

		return filtroMa(anio.getMateriaList(),'S');
	}

	public Materia default1EliminarMateria(final Anio anio) {
		if (choices1EliminarMateria(anio).isEmpty()) {
			return null;
		}

		return choices1EliminarMateria(anio).get(0);
	}

	public String validateEliminarMateria(Anio anio, Materia materia, Boolean seguro) {
		if (!seguro) {
			return "Debe chequear la confirmacion. Tenga en cuenta que esta "
					+ "eliminacion se aplica a todos los cursos de este Año ya creados ";
		}
		return null;
	}

	// endregion > agregarMateria

	@Programmatic
	private void eliminarMateriaCursosYaCreados(Anio anio, Materia materia) {
		
		List<Curso> cursoList = cursoRepositorio.listarCursosDeUnAnio(anio.getPlan(), anio);
		
		for (Curso curso : cursoList){
			List<MateriaDelCurso> materiaDelCursoList = curso.getMateriaDelCursoList();
			List<MateriaDelLibroDiario> materiadellibrodiarioList=librodiariorepositorio.mostrarLibroDiarioDelCurso(curso).getMateriaDelLibroDiarioList();
			for (MateriaDelCurso materiaDelCurso : materiaDelCursoList){
				if (materiaDelCurso.getMateria() == materia){
					container.remove(materiaDelCurso);
				}
			}
			for (MateriaDelLibroDiario materialibrodiario : materiadellibrodiarioList){
				if (materialibrodiario.getMateriaDelCurso().getMateria() == materia){
					container.remove(materialibrodiario);
				}
			}
		}
	}

	private void agregarMateriaCursosYaCreados(Anio anio, Materia materia) {
		
		List<Curso> cursoList = cursoRepositorio.listarCursosDeUnAnio(anio.getPlan(), anio);
		for (Curso curso : cursoList){
			materiaDelCursoRepositorio.crearMateriaDelCurso(curso, materia);
			librodiariorepositorio.ModificarLibroDiario(curso, librodiariorepositorio.mostrarLibroDiarioDelCurso(curso));
		}
		
	}
	
	
	private List<Materia> filtroMa(List<Materia> Materias, char A)
	{
		List<Materia> filtroMa=new ArrayList<Materia>();
		
		for(Materia Ma:Materias)
		{
			if(Ma.getHabilitado()==A)
				filtroMa.add(Ma);
		}
		
		return filtroMa;
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	CursoRepositorio cursoRepositorio;
	@javax.inject.Inject
	MateriaDelCursoRepositorio materiaDelCursoRepositorio;
	@javax.inject.Inject
	LibroDiarioRepositorio librodiariorepositorio;
	// endregion

}
