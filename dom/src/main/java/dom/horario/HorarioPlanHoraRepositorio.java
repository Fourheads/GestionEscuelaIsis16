package dom.horario;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotContributed;

@Hidden
@DomainService
public class HorarioPlanHoraRepositorio {

	@NotContributed
	public HorarioPlanHora crearHorarioPlanHora(HorarioPlan horarioPlan) {
		HorarioPlanHora horarioPlanHora = container.newTransientInstance(HorarioPlanHora.class);
		horarioPlanHora.setHorarioPlan(horarioPlan);
		
		container.persistIfNotAlready(horarioPlanHora);
		return horarioPlanHora;
		
	}

	@Inject
	DomainObjectContainer container;
}
