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
	public HorarioPlan crearHorarioPlanHora(HorarioPlan horarioPlan){
			horarioPlan.getHorarioPlanHoraList().add(horarioPlanHoraRepositorio.crearHorarioPlanHora(horarioPlan));
		return horarioPlan;
	}
	
	@Inject
	DomainObjectContainer container;
	@Inject
	HorarioPlanHoraRepositorio horarioPlanHoraRepositorio;
	
}	
