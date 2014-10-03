package dom.horario;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotInServiceMenu;

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
	@NotInServiceMenu
	public HorarioPlan crearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Duración (minutos)") Integer duracion) {

		HorarioPlanHora horarioPlanHora = horarioPlanHoraRepositorio
				.crearHorarioPlanHora(horarioPlan);

		Hora inicio = new Hora();
		Hora fin = new Hora();

		// inicio.setHora(inicioHora);
		// inicio.setMinutos(inicioMinutos);
		// fin.setHora(finHora);
		// fin.setMinutos(finMinutos);

		horarioPlanHora.setHoraInicio(inicio);
		horarioPlanHora.setHoraFin(fin);
		horarioPlanHora.setTipoHoraPlan(e_HorarioHoraTipo);
		horarioPlan.getHorarioPlanHoraList().add(horarioPlanHora);
		return horarioPlan;
	}

	public List<Integer> choices2CrearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Duración (minutos)") Integer duracion) {

		List<Integer> minutos = new ArrayList<Integer>();

		if (e_HorarioHoraTipo == E_HorarioHoraTipo.HORA_CATEDRA) {
			minutos.add(40);
		} else {
			for (Integer i = 5; i < 61; i = i + 5) {
				minutos.add(i);
			}
		}

		return minutos;
	}

	public String validateCrearHorarioPlanHora(HorarioPlan horarioPlan,
			@Named("Tipo de Hora") E_HorarioHoraTipo e_HorarioHoraTipo,
			@Named("Duración (minutos)") Integer duracion) {

		if (horarioPlan.getInicioClases()==null){
			return "Antes de agregar una hora debe indicar la hora de inicio de clases";
		}
		return null;
	}

	@Inject
	DomainObjectContainer container;
	@Inject
	HorarioPlanHoraRepositorio horarioPlanHoraRepositorio;

}
