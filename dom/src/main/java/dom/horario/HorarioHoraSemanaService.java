package dom.horario;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.simple.Curso;

@DomainService
@Hidden
public class HorarioHoraSemanaService {

	public HorarioHoraSemanaView crearHorarioHoraSemanaView(String inicioFin,
			String lunes, String martes, String miercoles, String jueves,
			String viernes) {

		Memento memento = mementoService.create();

		memento.set("inicioFin", inicioFin);
		memento.set("lunes", lunes);
		memento.set("martes", martes);
		memento.set("miercoles", miercoles);
		memento.set("jueves", jueves);
		memento.set("viernes", viernes);

		return container.newViewModelInstance(HorarioHoraSemanaView.class,
				memento.asString());

	}

	public List<HorarioHoraSemanaView> crearHorarioHoraSemanaViewList(
			String plan, int anio, String division) {

		List<HorarioHoraSemanaView> viewList = new ArrayList<HorarioHoraSemanaView>();

		Curso curso = container.firstMatch(new QueryDefault<Curso>(Curso.class,
				"buscarUnCurso", "plan", plan, "anio", anio, "division",
				division));

		List<HorarioPlanHora> listadoHoras = curso.getAnio().getPlan()
				.getHorarioPlan().getHorarioPlanHoraList();

		SortedSet<HorarioDia> listadoDias = curso.getHorarioCurso()
				.getHorarioDiaList();

		for (HorarioPlanHora horarioPlanHora : listadoHoras) {

			int index = listadoHoras.indexOf(horarioPlanHora);

			String inicioFin = horarioPlanHora.title();

			String lunes = "";

			SortedSet<HorarioDia> listadoHorasDia = curso.getHorarioCurso()
					.getHorarioDiaList();

			for (HorarioDia horarioDia : listadoHorasDia) {
				if (horarioDia.getDiaDeLaSemana().equals(
						E_HorarioDiaSemana.LUNES)) {
					List<HorarioHora> horarioHoraList = horarioDia
							.getHorarioHoraList();
					for (HorarioHora horarioHora : horarioHoraList) {
						HorarioPlanHora planHora = horarioHora
								.getHorarioPlanHora(); // esta pertenece al
														// listado de horas del
														// Dia
						if (horarioPlanHora == planHora
								&& horarioHora.getMateriaDelCurso() != null) {
							lunes = horarioHora.getMateriaDelCurso()
									.getMateria().getNombre();
						}
					}
				}
			}

			String martes = "";
			String miercoles = "";
			String jueves = "";
			String viernes = "";

			viewList.add(crearHorarioHoraSemanaView(inicioFin, lunes, martes,
					miercoles, jueves, viernes));
		}

		return viewList;
	}

	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	DomainObjectContainer container;
}
