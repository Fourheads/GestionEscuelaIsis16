package dom.horario;

import java.util.List;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotContributed;

import dom.simple.Curso;

@Hidden
@DomainService
public class HorarioDiaRepositorio {
	
	@NotContributed
	public SortedSet<HorarioDia> crearHorarioDiaList(Curso curso) {

		SortedSet<HorarioDia> horarioDiaList = new TreeSet<HorarioDia>();
		horarioDiaList.add(crearUnDia(1, E_HorarioDiaSemana.LUNES, curso));
		horarioDiaList.add(crearUnDia(2, E_HorarioDiaSemana.MARTES, curso));
		horarioDiaList.add(crearUnDia(3, E_HorarioDiaSemana.MIERCOLES, curso));
		horarioDiaList.add(crearUnDia(4, E_HorarioDiaSemana.JUEVES, curso));
		horarioDiaList.add(crearUnDia(5, E_HorarioDiaSemana.VIERNES, curso));
		
		
		return horarioDiaList;
	}
	
	@NotContributed
	private HorarioDia crearUnDia(int ordenDia, E_HorarioDiaSemana dia, Curso curso){
		HorarioDia horarioDia = new HorarioDia();
		horarioDia.setOrdenDias(ordenDia);
		horarioDia.setDiaDeLaSemana(dia);
		
		List <HorarioHora> horarioHoraList = new ArrayList<HorarioHora>();
		
		List <HorarioPlanHora> horarioPlanHoraList = curso.getAnio().getPlan().getHorarioPlan().getHorarioPlanHoraList();
		for (HorarioPlanHora horarioPlanHora : horarioPlanHoraList){

			horarioHoraList.add(horaRepositorio.crearHorarioHora(horarioDia, horarioPlanHora));
		}
		horarioDia.setHorarioHoraList(horarioHoraList);
		
		return horarioDia;
	}
	
	@javax.inject.Inject
	HorarioHoraRepositorio horaRepositorio;
}
