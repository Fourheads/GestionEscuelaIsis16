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
	public HorarioPlan crearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Inicio (hora)") int inicioHora,
			@Named("Inicio (minutos)") int inicioMinutos,
			@Named("Inicio (hora)") int finHora,
			@Named("Inicio (minutos)") int finMinutos) {

		HorarioPlanHora horarioPlanHora = horarioPlanHoraRepositorio
				.crearHorarioPlanHora(horarioPlan);

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

	public String validateCrearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Inicio (hora)") int inicioHora,
			@Named("Inicio (minutos)") int inicioMinutos,
			@Named("Inicio (hora)") int finHora,
			@Named("Inicio (minutos)") int finMinutos) {

		if (inicioHora < 0 || finHora < 0 || inicioHora > 23 || finHora > 23) {
			return "ERROR. Ingrese por favor una hora entre 0 y 23";
		}
		if (inicioMinutos < 0 || finMinutos < 0 || inicioMinutos > 59
				|| finMinutos > 59) {
			return "ERROR. Ingrese por favor los minutos entre 0 y 59";
		}

		return null;
	}

	@Inject
	DomainObjectContainer container;
	@Inject
	HorarioPlanHoraRepositorio horarioPlanHoraRepositorio;

}
