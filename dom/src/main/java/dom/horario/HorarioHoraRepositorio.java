package dom.horario;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;

@DomainService
@Hidden
public class HorarioHoraRepositorio {
	
	public HorarioHora crearHorarioHora(HorarioDia horarioDia, HorarioPlanHora horarioPlanHora){
		
		HorarioHora horarioHora = new HorarioHora();
		horarioHora.setHorarioDia(horarioDia);
		horarioHora.setHorarioPlanHora(horarioPlanHora);
		horarioHora.setHorarioHoraTipo(horarioPlanHora.getTipoHoraPlan());
		
		return horarioHora;
	}

}
