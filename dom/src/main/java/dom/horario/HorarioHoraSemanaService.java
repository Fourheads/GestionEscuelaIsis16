package dom.horario;

import java.util.ArrayList;
import java.util.List;

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

		Curso curso = container.firstMatch(new QueryDefault<Curso>(Curso.class, "buscarUnCurso",
												"plan",plan,
												"anio",anio,
												"division",division));
		
		
		String inicioFin = "8:00 a 8:40";
		String lunes = curso.getDivision();
		String martes = "";
		String miercoles = "";
		String jueves = "";
		String viernes = "";

		for (int i = 0; i < 10; i++) {
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
