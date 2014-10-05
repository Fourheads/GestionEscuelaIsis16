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

		for (HorarioPlanHora horarioPlanHora : listadoHoras) {

			String inicioFin = horarioPlanHora.title();

			String[] dia = new String[5];
			E_HorarioDiaSemana[] diaNombre = { E_HorarioDiaSemana.LUNES,
					E_HorarioDiaSemana.MARTES, E_HorarioDiaSemana.MIERCOLES,
					E_HorarioDiaSemana.JUEVES, E_HorarioDiaSemana.VIERNES };

			SortedSet<HorarioDia> listadoHorasDia = curso.getHorarioCurso()
					.getHorarioDiaList();

			for (int diaNumero = 0; diaNumero < 5; diaNumero++) {
				for (HorarioDia horarioDia : listadoHorasDia) {
					if (horarioDia.getDiaDeLaSemana().equals(
							diaNombre[diaNumero])) {
						List<HorarioHora> horarioHoraList = horarioDia
								.getHorarioHoraList();
						for (HorarioHora horarioHora : horarioHoraList) {
							HorarioPlanHora planHora = horarioHora
									.getHorarioPlanHora(); // esta pertenece al
															// listado de horas
															// del
															// Dia
							if (horarioPlanHora == planHora
									&& horarioHora.getMateriaDelCurso() != null) {
								dia[diaNumero] = horarioHora
										.getMateriaDelCurso().getMateria()
										.getNombre();
							}
						}
					}
				}

			}

			viewList.add(crearHorarioHoraSemanaView(inicioFin, dia[0], dia[1],
					dia[2], dia[3], dia[4]));
		}

		return viewList;
	}

	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	DomainObjectContainer container;
}
