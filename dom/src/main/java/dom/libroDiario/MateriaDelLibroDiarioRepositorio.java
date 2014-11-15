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

@DomainService
public class MateriaDelLibroDiarioRepositorio {
	
	@Hidden
	public List<MateriaDelLibroDiario> crearListaMateriaDelLibroDiario(final Curso curso) {
		
		List<MateriaDelCurso> materiaDelCursoList = curso.getMateriaDelCursoList();
		List<MateriaDelLibroDiario> materiaDelLibroDiarioList = new ArrayList<MateriaDelLibroDiario>();
		
		for (MateriaDelCurso materiaDelCurso : materiaDelCursoList){
			MateriaDelLibroDiario materiaDelLibroDiario = new MateriaDelLibroDiario();
			materiaDelLibroDiario.setMateriaDelCurso(materiaDelCurso);
			materiaDelLibroDiario.settiutulo(materiaDelCurso.getMateria().getNombre());
			materiaDelLibroDiarioList.add(materiaDelLibroDiario);
		}
		
		return materiaDelLibroDiarioList;
	}

	

	@Hidden
	public EntradaLibroDiario nuevaEntradalibrodiario(@Named("Curso") final LibroDiario LibroDiario, 
			@Named("Materia")final MateriaDelLibroDiario materialiDelLibroDiario,
			@Named("Fecha") final LocalDate  fecha, @Named("Hora") int horas, @Named("Unidad") int unidad,
			final @MaxLength(2048)
	  		@MultiLine @Named("Actividad") String actividad,
	  		final @MaxLength(2048)
			@MultiLine@Named("Observaciones") String Observaciones){
		
		
		EntradaLibroDiario entradadario=entradalibrodiariorepositiorio.crearEntradadeLibroDiario(fecha, horas, unidad, actividad, Observaciones
				);
		
		materialiDelLibroDiario.AsignarEntradaLibroDiario(entradadario);
		
		container.persistIfNotAlready(materialiDelLibroDiario);
		
		return entradadario;
	}
	
	public List<LibroDiario> choices0NuevaEntradalibrodiario()
	{
		List<LibroDiario> listalibro=new ArrayList<LibroDiario>();
		for(LibroDiario librodia: libroDiarioRepositorio.listaLibroDiarioDelCurso())
		{
			if(librodia.getCurso().getHabilitado()=='S')
				listalibro.add(librodia);
		}
		
		return listalibro;
	}
	
	public List<MateriaDelLibroDiario> choices1NuevaEntradalibrodiario(@Named("Curso") final LibroDiario LibroDiario)
	{
		return listarmateriaslibrodiario(LibroDiario);
	}

	
	public List<Integer> choices3NuevaEntradalibrodiario() {//ojo ver cantidad de horas
		
		List<Integer> Horas = new ArrayList<Integer>();
				
			for (int i = 1; i <= 6; i++) {
				Horas.add(i);
			}
			
		return Horas;
	}

	
	public int default3NuevaEntradalibrodiario() {
		return choices3NuevaEntradalibrodiario().get(0);
	}
	
	public List<Integer> choices4NuevaEntradalibrodiario() {//Ver tema unidades

		List<Integer> Unidades = new ArrayList<Integer>();

		for (int i = 1; i <= 10; i++) {
			Unidades.add(i);
		}

		return Unidades;
	}

	public int default4NuevaEntradalibrodiario() {
		return choices4NuevaEntradalibrodiario().get(0);
	}
	
	@Hidden
	public List<MateriaDelLibroDiario> mostrarmateriaLibroDiario(final Curso curso) {
		return listarmateriaslibrodiario(libroDiarioRepositorio.mostrarLibroDiarioDelCurso(curso));
	}
	
	@Named("Entradas por fecha")
	@MemberOrder(name="Libro diario",sequence = "3")
	public List<EntradaLibroDiario> listarEntradasporFecha(@Named("Curso") final Curso curso, @Named("Materia") final MateriaDelCurso materiadelcurso, @Named("Fecha") final LocalDate fecha)
	{
		return entradalibrodiariorepositiorio.entradasporfecha(curso, materiadelcurso, fecha);
	}
	
	
	public List<MateriaDelCurso> choices1ListarEntradasporFecha(@Named("Curso") final Curso curso)
	{
		if(curso != null)
			return materiaDelCursoRepositorio.listarMateriaDelCursoParaUnCurso(curso);
		else
			return null;
	}
	
	@Hidden
	public List<MateriaDelLibroDiario> listarmateriaslibrodiario(final LibroDiario libroDiario)
	{
		return container.allMatches(new QueryDefault<MateriaDelLibroDiario>(MateriaDelLibroDiario.class,
				"LibroDiarioMateriadelLibroDiarioList","libroDiario", libroDiario));
	}
	
	@Hidden
	//@MemberOrder(sequence = "2")
	public MateriaDelLibroDiario traerMateriaLibroDiario(@Named("Curso") final Curso curso, @Named("Materia") final MateriaDelCurso materiadelcurso)
	{
		return container.firstMatch(new QueryDefault<MateriaDelLibroDiario>(MateriaDelLibroDiario.class,
				"MateriadelLibroDiario","materiacurso", materiadelcurso));
	}
	
	public List<MateriaDelCurso> choices1TraerMateriaLibroDiario(@Named("Curso") final Curso curso)
	{
		if(curso != null)
			return materiaDelCursoRepositorio.listarMateriaDelCursoParaUnCurso(curso);
		else
			return null;
	}
	

	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	EntradadeLibroDiarioRepositorio entradalibrodiariorepositiorio;
	@javax.inject.Inject
	LibroDiarioRepositorio libroDiarioRepositorio;
	@javax.inject.Inject
	MateriaDelCursoRepositorio materiaDelCursoRepositorio;

}
