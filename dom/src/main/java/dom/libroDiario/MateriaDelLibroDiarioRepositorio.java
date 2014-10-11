package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.joda.time.LocalDate;

import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

@DomainService
public class MateriaDelLibroDiarioRepositorio {

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
	@MemberOrder(sequence = "1")
	public EntradaLibroDiario NuevaEntradalibrodiario(@Named("Fecha") LocalDate  fecha, @Named("Numero de hora") int horas, @Named("Unidad") int unidad, @Named("Actividad") String actividad, @Named("Observaciones") String Observaciones){

		MateriaDelLibroDiario materiaslibrodiario= container.newTransientInstance(MateriaDelLibroDiario.class);
		

		
		EntradaLibroDiario entradadario=entradalibrodiariorepositiorio.crearEntradadeLibroDiario(fecha, horas, unidad, actividad, Observaciones);
		
		materiaslibrodiario.AsignarEntradaLibroDiario(entradadario);
		
		container.persistIfNotAlready(materiaslibrodiario);
		
		return entradadario;
	}
	
	public List<Integer> choices1NuevaEntradalibrodiario() {//Hojo ver cantidad de horas

		List<Integer> Horas = new ArrayList<Integer>();

		for (int i = 1; i <= 12; i++) {
			Horas.add(i);
		}

		return Horas;
	}

	public int default1NuevaEntradalibrodiario() {
		return choices1NuevaEntradalibrodiario().get(0);
	}
	
	public List<Integer> choices2NuevaEntradalibrodiario() {//Ver tema unidades

		List<Integer> Unidades = new ArrayList<Integer>();

		for (int i = 1; i <= 13; i++) {
			Unidades.add(i);
		}

		return Unidades;
	}

	public int default2NuevaEntradalibrodiario() {
		return choices2NuevaEntradalibrodiario().get(0);
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	EntradadeLibroDiarioRepositorio entradalibrodiariorepositiorio;
}
