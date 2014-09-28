package dom.asistencia;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import dom.simple.Alumno;
import dom.simple.AlumnoRepositorio;

@DomainService
public class AnalisisAsistenciaService {

	public AnalisisAsistenciaView analizarIntervaloAsistenciaAlumno(
			String asistencia, int anio, String division, int dni,
			String nombre, String apellido, LocalDate desde, LocalDate hasta) {

		// memento asistencia, anio, division, dni, , nombre, apellido,
		// fechadesde, fechahasta

		List<AsistenciaAlumno> tempList = AsistenciaAlumnoRepositorio
				.queryAsistenciaAlumnoPorCursoEnUnIntervalo(asistencia, anio,
						division, desde, hasta, dni);

		// total asistencias

		int cantidadAsistencia = tempList.size();

		// total presente ausente tarde

		int presente = 0;
		int ausente = 0;
		int tarde = 0;

		for (AsistenciaAlumno asistenciaAlumno : tempList) {
			if (asistenciaAlumno.getEstaPresente()) {
				presente++;
			}

			if (!asistenciaAlumno.getEstaPresente()
					&& !asistenciaAlumno.getLlegoTarde()) {
				ausente++;
			}

			if (asistenciaAlumno.getLlegoTarde()) {
				tarde++;
			}
		}

		// porcentaje tarde ausente

		BigDecimal porcentajeTarde;
		BigDecimal porcentajeAusente;
		BigDecimal tardeBD = new BigDecimal(tarde);
		BigDecimal ausenteBD = new BigDecimal(ausente);

		if (cantidadAsistencia != 0) {

			porcentajeTarde = tardeBD.multiply(new BigDecimal(100)).divide(
					new BigDecimal(cantidadAsistencia), 2,
					BigDecimal.ROUND_HALF_UP);
			porcentajeAusente = ausenteBD.multiply(new BigDecimal(100)).divide(
					new BigDecimal(cantidadAsistencia), 2,
					BigDecimal.ROUND_HALF_UP);
		} else {
			porcentajeTarde = new BigDecimal(0);
			porcentajeAusente = new BigDecimal(0);
		}

		// total inasistencias

		BigDecimal mediaFalta = tardeBD.divide(new BigDecimal(2), 1,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal totalInasistencias = new BigDecimal(ausente).add(mediaFalta);

		// memento= nombre,apellido,cantidadAsistencia,presente,tarde,ausente,
		// porcentajeTarde,porcentajeAusente,totalInasistencias

		Memento memento = mementoService.create();

		memento.set("nombre", nombre);
		memento.set("apellido", apellido);
		memento.set("cantidadAsistencia", cantidadAsistencia);
		memento.set("presente", presente);
		memento.set("tarde", tarde);
		memento.set("ausente", ausente);
		memento.set("porcentajeTarde", porcentajeTarde);
		memento.set("porcentajeAusente", porcentajeAusente);
		memento.set("totalInasistencias", totalInasistencias);
				
		return container.newViewModelInstance(AnalisisAsistenciaView.class,
				memento.asString());
	}

	public List<AnalisisAsistenciaView> analizarIntervaloAsistenciaCurso(
			String asistencia, int anio, String division, LocalDate desde,
			LocalDate hasta) {

		List<AnalisisAsistenciaView> listaAnalisis = new ArrayList<AnalisisAsistenciaView>();

		String mementoAnalisis;

		List<Alumno> alumnoList = alumnoRepositorio.queryListarAlumnosDeUnCurso(anio, division);

		for (Alumno alumno : alumnoList) {

			int dni = alumno.getDni();
			String nombre = alumno.getNombre();
			String apellido = alumno.getApellido();

			listaAnalisis.add(analizarIntervaloAsistenciaAlumno(asistencia, anio, division, dni, nombre,
					apellido, desde, hasta));

		}

		return listaAnalisis;
	}

	// region > injected services
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	AlumnoRepositorio alumnoRepositorio;
	
	// endregion

}
