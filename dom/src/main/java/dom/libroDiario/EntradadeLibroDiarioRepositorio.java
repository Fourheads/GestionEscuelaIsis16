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

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dom.simple.Curso;
import dom.simple.MateriaDelCurso;
import dom.simple.MateriaDelCursoRepositorio;

//@Named("Entrada libro diario")
@Hidden
@DomainService(repositoryFor = EntradaLibroDiario.class)
public class EntradadeLibroDiarioRepositorio {

	@Hidden
	public EntradaLibroDiario crearEntradadeLibroDiario(LocalDate fecha,
			int horas, int unidad, final @MaxLength(2048)
	  @MultiLine @Named("Actividad") String actividad,
	  final @MaxLength(2048)
	  @MultiLine@Named("Observaciones") String Observaciones) {
		
		EntradaLibroDiario entradalibrodiario = container.newTransientInstance(EntradaLibroDiario.class);
		

		entradalibrodiario.setFecha(fecha);
		
		HoraCatedra horacate= horacatedra.crearhoracatedra(horas, unidad, actividad, Observaciones);
		
		entradalibrodiario.asignarHoracatedra(horacate);
		
		
		container.persistIfNotAlready(entradalibrodiario);
		return entradalibrodiario;
	}

	public LocalDate default0CrearEntradadeLibroDiario() {
		
		new LocalDate();
		LocalDate fecha =LocalDate.now();
		
		return fecha;
	}
	
	@Hidden
	//@MemberOrder(sequence = "1")
	public List<EntradaLibroDiario> TodasLasEntradas()
	{
		return container.allMatches(new QueryDefault<EntradaLibroDiario>(EntradaLibroDiario.class,
				"TodasEntradaLibroDiarioList"));
	}
	
	@Hidden
	public List<EntradaLibroDiario> entradasporfecha(@Named("Curso") final Curso curso, @Named("Materia") final MateriaDelCurso materiadelcurso, @Named("Fecha") final LocalDate fecha)
	{
		return container.allMatches(new QueryDefault<EntradaLibroDiario>(EntradaLibroDiario.class,
				"EntradaLibroDiarioListFecha", "Fecha", fecha, "materiacurso", materiadelcurso));
	}
	
	/*
	public List<MateriaDelCurso> choices1Entradasporfecha(@Named("Curso") final Curso curso)
	{
		if(curso != null)
			return materiaDelCursoRepositorio.listarMateriaDelCursoParaUnCurso(curso);
		else
			return null;
	}*/
	
	@Hidden
	public List<EntradaLibroDiario> listarhorasusadasenfecha(LibroDiario librodiario, LocalDate fecha)
	{
		return container.allMatches(new QueryDefault<EntradaLibroDiario>(EntradaLibroDiario.class,
				"Entradasporfechaporlibrodiario","LibroDiario", librodiario, "Fecha", fecha ));
	}

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	HoraCatedraRepositorio horacatedra; 
	@javax.inject.Inject
	MateriaDelCursoRepositorio materiaDelCursoRepositorio;
	
}
