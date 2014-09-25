package dom.asistencia;

import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.PublishedObject;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import dom.simple.Alumno;
import dom.simple.Curso;
import dom.simple.CursoRepositorio;

@Named("Contabilizar Asistencia")
@DomainService(menuOrder = "90")
public class ContabilizarAsistenciasService {

	// region > identification in the UI
	@Hidden
	public String getId() {
		return "contarAsistencia";
	}

	public String title() {
		return "Contabilizar Asistencia";
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	@MemberOrder(sequence = "2")
	@PublishedAction
	public ContabilizarAsistenciasView contarAsistenciasCurso(
			@Named("Esquema") Asistencia asistencia,
			@Named("Curso") Curso curso, @Named("Desde") LocalDate desde,
			@Named("Hasta") LocalDate hasta) {

		// memento: esquema, anio, division, desde, hasta

		Memento memento = mementoService.create();

		memento.set("titulo", desde + " / " + hasta);
		memento.set("asistencia", asistencia.getDescripcion());
		memento.set("anio", curso.getAnio());
		memento.set("division", curso.getDivision());
		memento.set("desde", desde);
		memento.set("hasta", hasta);

		return container.newViewModelInstance(
				ContabilizarAsistenciasView.class, memento.asString());
	}

	public List<Asistencia> choices0ContarAsistenciasCurso() {

		return container.allInstances(Asistencia.class);
	}

	public Asistencia default0ContarAsistenciasCurso() {

		return choices0ContarAsistenciasCurso().get(0);
	}

	public List<Curso> choices1ContarAsistenciasCurso() {

		return CursoRepositorio.querylistAll();
	}

	public Curso default1ContarAsistenciasCurso() {

		return choices1ContarAsistenciasCurso().get(0);
	}

	public String validateContarAsistenciasCurso(Asistencia esquema,
			Curso curso, LocalDate desde, LocalDate hasta) {

		List<Alumno> listadoAlumnos = container
				.allMatches(new QueryDefault<Alumno>(Alumno.class,
						"alumnosDeUnCurso", "anio", curso.getAnio(),
						"division", curso.getDivision()));

		if (listadoAlumnos.isEmpty()) {
			return "El curso seleccionado no posee alumnos";
		}

		return null;
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	static DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;

	// endregion
}
