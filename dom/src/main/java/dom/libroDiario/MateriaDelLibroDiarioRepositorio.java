package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

import dom.simple.Curso;
import dom.simple.MateriaDelCurso;


@DomainService
public class MateriaDelLibroDiarioRepositorio {/*

	public List<MateriaDelLibroDiario> crearListaMateriaDelLibroDiario(Curso curso) {
		
		List<MateriaDelCurso> materiaDelCursoList = curso.getMateriaDelCursoList();
		List<MateriaDelLibroDiario> materiaDelLibroDiarioList = new ArrayList<MateriaDelLibroDiario>();
		
		for (MateriaDelCurso materiaDelCurso : materiaDelCursoList){
			MateriaDelLibroDiario materiaDelLibroDiario = new MateriaDelLibroDiario();
			materiaDelLibroDiario.setMateriaDelCurso(materiaDelCurso);
			
			materiaDelLibroDiarioList.add(materiaDelLibroDiario);
		}
		
		return materiaDelLibroDiarioList;
	}

	
	@Named("Nueva entrada libro diario")
	@MemberOrder(sequence = "1")//@Named("Libro Diario") final LibroDiario librodiario, @Named("Materias del libro diario") final MateriaDelLibroDiario materialiDelLibroDiario,
	public EntradaLibroDiario nuevaEntradalibrodiario(@Named("Fecha") LocalDate  fecha, @Named("Numero de hora") int horas, @Named("Unidad") int unidad, @Named("Actividad") String actividad, @Named("Observaciones") String Observaciones){

		MateriaDelLibroDiario materialiDelLibroDiario=container.newTransientInstance(MateriaDelLibroDiario.class);
 
		EntradaLibroDiario entradadario=entradalibrodiariorepositiorio.crearEntradadeLibroDiario(fecha, horas, unidad, actividad, Observaciones
				);
		
		materialiDelLibroDiario.AsignarEntradaLibroDiario(entradadario);
		
		container.persistIfNotAlready(materialiDelLibroDiario);
		
		return entradadario;
	}
	/*
	public LibroDiario choices0NuevaEntradalibrodiario(final Curso curso)
	{
		return libroDiarioRepositorio.mostrarLibroDiarioDelCurso(curso);
	}
	
	public List<MateriaDelLibroDiario> choices1NuevaEntradalibrodiario(final LibroDiario librodairio)
	{
		return listarmateriasdellibrodiario(librodairio);
	}
	
	public List<MateriaDelLibroDiario> listarmateriasdellibrodiario(final LibroDiario librodairio)
	{
		return container.allMatches(new QueryDefault<MateriaDelLibroDiario>(MateriaDelLibroDiario.class,
				"MateriadelLibroDiarioList", 
				"LibroDiario", librodairio.getMateriaDelLibroDiarioList()));
	}
	
	public List<Integer> choices3NuevaEntradalibrodiario() {//ojo ver cantidad de horas

		List<Integer> Horas = new ArrayList<Integer>();

		for (int i = 1; i <= 12; i++) {
			Horas.add(i);
		}

		return Horas;
	}

	public int default3NuevaEntradalibrodiario() {
		return choices3NuevaEntradalibrodiario().get(0);
	}
	
	public List<Integer> choices4NuevaEntradalibrodiario() {//Ver tema unidades

		List<Integer> Unidades = new ArrayList<Integer>();

		for (int i = 1; i <= 13; i++) {
			Unidades.add(i);
		}

		return Unidades;
	}

	public int default4NuevaEntradalibrodiario() {
		return choices4NuevaEntradalibrodiario().get(0);
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	EntradadeLibroDiarioRepositorio entradalibrodiariorepositiorio;
	@javax.inject.Inject
	LibroDiarioRepositorio libroDiarioRepositorio;*/
}
