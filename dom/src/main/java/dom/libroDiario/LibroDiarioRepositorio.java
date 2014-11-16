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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;




import dom.escuela.Curso;
import dom.escuela.CursoRepositorio;
import dom.escuela.MateriaDelCurso;

@Hidden
@Named("Libro diario")
@DomainService(menuOrder = "100")
public class LibroDiarioRepositorio {
	
	
	@Hidden
	public void crearLibroDiario(final Curso curso) {
		
		LibroDiario libroDiario = container.newTransientInstance(LibroDiario.class);
		libroDiario.setCurso(curso);
		libroDiario.setMateriaDelLibroDiarioList(materiaDelLibroDiarioRepositorio.crearListaMateriaDelLibroDiario(curso));
		libroDiario.settiutulo(curso.title());
		container.persistIfNotAlready(libroDiario);
		
	}
	
	@Hidden
	public void ModificarLibroDiario(final Curso curso, final LibroDiario libroDiario) {
		
		//libroDiario.setCurso(curso);
		libroDiario.setMateriaDelLibroDiarioList(materiaDelLibroDiarioRepositorio.crearListaMateriaDelLibroDiario(curso));
		container.persistIfNotAlready(libroDiario);
		
	}

	@Named("Nueva entrada libro diario ")
	@MemberOrder(name="Libro diario",sequence = "1")
	public EntradaLibroDiario nuevaEntradalibrodiario(@Named("Curso") final LibroDiario LibroDiario, 
			@Named("Materia")final MateriaDelLibroDiario materialiDelLibroDiario,
			@Named("Fecha") final LocalDate  fecha, @Named("Hora") int horas, @Named("Unidad") int unidad,
			final @MaxLength(2048)
	  		@MultiLine @Named("Actividad") String actividad,
	  		final @MaxLength(2048)
			@MultiLine@Named("Observaciones") String Observaciones)
	{
		return materiaDelLibroDiarioRepositorio.nuevaEntradalibrodiario(LibroDiario, materialiDelLibroDiario, fecha, horas, unidad, actividad, Observaciones);
	}
	
	public List<LibroDiario> choices0NuevaEntradalibrodiario()
	{
		List<LibroDiario> listalibro=new ArrayList<LibroDiario>();
		for(LibroDiario librodia: listaLibroDiarioDelCurso())
		{
			if(librodia.getCurso().getHabilitado()=='S')
				listalibro.add(librodia);
		}
		
		return listalibro;
	}
	
	public List<MateriaDelLibroDiario> choices1NuevaEntradalibrodiario(@Named("Curso") final LibroDiario LibroDiario)
	{
		return materiaDelLibroDiarioRepositorio.listarmateriaslibrodiario(LibroDiario);
	}

	
	public List<Integer> choices3NuevaEntradalibrodiario() {//ojo ver cantidad de horas
		
		List<Integer> Horas = new ArrayList<Integer>();
				
			for (int i = 1; i <= 16; i++) {
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

	@Named("Hoja del libro por dia")
	@MemberOrder(name="Libro diario",sequence = "4")
	public HojaLibroDiarioView mostrarhojalibrodiario(final Curso curso,@Named("Fecha") final LocalDate fecha)
	{
		Memento memento = mementoService.create();

		memento.set("Anio", curso.getAnio().getAnioNumero());
		memento.set("Division", curso.getDivision());
		memento.set("Fecha", fecha);
		memento.set("Plan", curso.getAnio().getPlan().getDescripcion());


		return container.newViewModelInstance(
				HojaLibroDiarioView.class, memento.asString());
	}
	
	public List<Curso> choices0Mostrarhojalibrodiario()
	{
		return cursorepositorio.listarCursoConAlumnos();
	}
	
	@Named("Entradas por fecha")
	@MemberOrder(name="Libro diario",sequence = "3")
	public List<EntradaLibroDiario> entradasporfecha(@Named("Curso") final Curso curso, @Named("Materia") final MateriaDelCurso materiadelcurso, @Named("Fecha") final LocalDate fecha)
	{
		return entradalibrodiarepositorio.entradasporfecha(curso, materiadelcurso, fecha);
	}
	
	public List<Curso> choices0Entradasporfecha()
	{
		return cursorepositorio.listarCursoConAlumnos();
	}
	
	public List<MateriaDelCurso> choices1Entradasporfecha(final Curso curso)
	{
		if(curso!=null)
			return curso.getMateriaDelCursoList();
		return null;
	}
	
	@Named("Mostrar libro diario del curso")
	@MemberOrder(name="Libro diario",sequence = "2")
	public LibroDiario mostrarLibroDiarioDelCurso(final Curso curso) {
		
		return container.firstMatch(new QueryDefault<LibroDiario>(LibroDiario.class,
				"LibroDiarioDeUnCurso", 
				"plan", curso.getAnio().getPlan().getDescripcion(), 
				"anio", curso.getAnio().getAnioNumero(),
				"division", curso.getDivision()));
	}
	
	public List<Curso> choices0MostrarLibroDiarioDelCurso()
	{
		return cursorepositorio.listarCursoConAlumnos();
	}
	
	@Hidden
	public List<LibroDiario> listaLibroDiarioDelCurso() {
		
		return container.allMatches(new QueryDefault<LibroDiario>(LibroDiario.class, "TodosLibroDiario"));
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MateriaDelLibroDiarioRepositorio materiaDelLibroDiarioRepositorio;
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	CursoRepositorio cursorepositorio;
	@javax.inject.Inject
	EntradadeLibroDiarioRepositorio entradalibrodiarepositorio;
}
