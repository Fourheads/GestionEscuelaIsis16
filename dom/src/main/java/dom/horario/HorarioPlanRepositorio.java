package dom.horario;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

import dom.planEstudio.Plan;

@DomainService
@Hidden
public class HorarioPlanRepositorio {

	public HorarioPlan crearHorarioPlan(Plan plan) {

		HorarioPlan horarioPlan = container
				.newTransientInstance(HorarioPlan.class);
		horarioPlan.setPlan(plan);
		return horarioPlan;
	}

	@MemberOrder(sequence = "1")
	@Named("Crear Nueva Hora")
	public HorarioPlan crearHorarioPlanHora(	HorarioPlan horarioPlan,
												@Named("") E_HorarioHoraTipo e_HorarioHoraTipo,
												@Named("Inicio (hora)") int inicioHora,
												@Named("Inicio (minutos)") int inicioMinutos,
												@Named("Inicio (hora)") int finHora,
												@Named("Inicio (minutos)") int finMinutos){
			
		HorarioPlanHora horarioPlanHora = horarioPlanHoraRepositorio.crearHorarioPlanHora(horarioPlan);
		
		Hora inicio = new Hora();
		Hora fin = new Hora();
		inicio.setHora(inicioHora);
		inicio.setMinutos(inicioMinutos);
		fin.setHora(finHora);
		fin.setMinutos(finMinutos);
		
		horarioPlanHora.setHoraInicio(inicio);
		horarioPlanHora.setHoraFin(fin);
		horarioPlanHora.setTipoHoraPlan(e_HorarioHoraTipo);
		horarioPlan.getHorarioPlanHoraList().add(horarioPlanHora);
		return horarioPlan;
	}
	
	@Inject
	DomainObjectContainer container;
	@Inject
	HorarioPlanHoraRepositorio horarioPlanHoraRepositorio;
	
}	
