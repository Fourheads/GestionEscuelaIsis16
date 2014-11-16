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

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.applib.query.QueryDefault;

import dom.escuela.Curso;
import dom.escuela.CursoRepositorio;

@Hidden
@DomainService(repositoryFor = Anio.class)
public class AnioRepositorio {

	// region > agregarAnio
	// //////////////////////////////////////
	@NotInServiceMenu
	@Named("Agregar Año al Plan")
	@MemberOrder(sequence = "1", name = "Listado de Años del Plan")
	public Plan agregarAnio(final @Named("Plan") Plan plan,
			final @Named("") int anioNumero) {

		Anio nuevoAnio = new Anio();
		nuevoAnio.setAnioNumero(anioNumero);
		nuevoAnio.setHabilitado('S');
		plan.getAnioList().add(nuevoAnio);

		return plan;
	}

	public List<Integer> choices1AgregarAnio(final @Named("Plan") Plan plan) {

		List<Integer> aniosDisponibles = new ArrayList<Integer>();
		List<Integer> aniosCreados = new ArrayList<Integer>();
		List<Anio> anioList = listarAniosDeUnPlan(plan);

		for (Anio anio : anioList) {
			aniosCreados.add(anio.getAnioNumero());
		}

		for (int i = 1; i < 9; i++) {
			aniosDisponibles.add(i);
		}

		aniosDisponibles.removeAll(aniosCreados);

		return aniosDisponibles;
	}

	public String validateAgregarAnio(Plan plan, int anioNumero) {
		SortedSet<Anio> aniosList = plan.getAnioList();
		for (Anio anio : aniosList) {
			if (anio.getAnioNumero() == anioNumero) {
				return "El año '" + anioNumero + "' ya fué creado";
			}
		}

		return null;
	}

	// endRegion > agregarAnio

	// {{ eliminarAnio (action)
	@NotInServiceMenu
	@Named("Eliminar Año del Plan")
	@MemberOrder(sequence = "2", name = "Listado de Años del Plan")
	public Plan eliminarAnio(final Plan plan, @Named("Año") Anio anio,
			@Named("¿Esta Seguro?") Boolean seguro) {
		if(anio.getMateriaList()!=null)
		{
			anio.setHabilitado('N');		
			
			/*
			for(Curso cur:curso.listarCursosDeUnAnio(plan, anio))
			{			
				if(cur.getAnio()==anio)
					cur.setAnio(null);
			}
			
			for(Materia mate:anio.getMateriaList())
			{			
				materia.eliminarMateria(anio, mate, true);
			}
			*/
			
			plan.getAnioList().remove(anio);
			//anio.setPlan(null);
		}
		else
		{
			container.raiseError("El año esta vinculado y no pude ser borrado");
		}
		
		return plan;
	}

	public SortedSet<Anio> choices1EliminarAnio(final Plan plan) {
		return plan.getAnioList();
	}

	// }}

	@NotContributed
	public List<Anio> listarAniosDeUnPlan(Plan plan) {
		return filtroAn(container.allMatches(new QueryDefault<Anio>(Anio.class,
				"listarAniosDeUnPlan", "plan", plan.getDescripcion())),'S');
	}
	
	
	private List<Anio> filtroAn(List<Anio> Anios, char A)
	{
		List<Anio> filtroAn=new ArrayList<Anio>();
		
		for(Anio An:Anios)
		{
			if(An.getHabilitado()==A)
				filtroAn.add(An);
		}
		
		return filtroAn;
	}


	@Inject
	DomainObjectContainer container;
    @javax.inject.Inject
    private CursoRepositorio curso;
    @javax.inject.Inject
    private MateriaRepositorio materia;
}
