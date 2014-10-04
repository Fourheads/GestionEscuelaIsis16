package dom.horario;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.services.memento.MementoService;

@DomainService
@Hidden
public class HorarioHoraSemanaService {

	public HorarioHoraSemanaView crearHorarioHoraSemanaView(int i) {
		return container.newViewModelInstance(HorarioHoraSemanaView.class, String.valueOf(i));

	}

	public List<HorarioHoraSemanaView> crearHorarioHoraSemanaViewList() {

		List<HorarioHoraSemanaView> viewList = new ArrayList<HorarioHoraSemanaView>();

		for (int i = 0; i < 10; i++) {
			viewList.add(crearHorarioHoraSemanaView(i));
		}

		return viewList;
	}

	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	DomainObjectContainer container;
}
