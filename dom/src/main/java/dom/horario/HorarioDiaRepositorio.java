package dom.horario;

import java.lang.reflect.Array;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;


@DomainService
public class HorarioDiaRepositorio {

	public SortedSet<HorarioDia> crearHorarioDiaList() {

		SortedSet<HorarioDia> horarioDiaList = new TreeSet<HorarioDia>();
		horarioDiaList.add(crearUnDia(1, E_HorarioDiaSemana.LUNES));
		horarioDiaList.add(crearUnDia(2, E_HorarioDiaSemana.MARTES));
		horarioDiaList.add(crearUnDia(3, E_HorarioDiaSemana.MIERCOLES));
		horarioDiaList.add(crearUnDia(4, E_HorarioDiaSemana.JUEVES));
		horarioDiaList.add(crearUnDia(5, E_HorarioDiaSemana.VIERNES));
		
		
		return horarioDiaList;
	}

	private HorarioDia crearUnDia(int ordenDia, E_HorarioDiaSemana dia){
		HorarioDia horarioDia = new HorarioDia();
		horarioDia.setOrdenDias(ordenDia);
		horarioDia.setDiaDeLaSemana(dia);
		return horarioDia;
	}
}
