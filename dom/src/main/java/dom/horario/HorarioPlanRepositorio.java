package dom.horario;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;

import dom.planEstudio.Plan;

@DomainService
@Hidden
public class HorarioPlanRepositorio {
	
	public HorarioPlan crearHorarioPlan(Plan plan){
		
		HorarioPlan horarioPlan = container.newTransientInstance(HorarioPlan.class);
		horarioPlan.setPlan(plan);
		return horarioPlan;
	}

	
	@Inject
	DomainObjectContainer container;
}
