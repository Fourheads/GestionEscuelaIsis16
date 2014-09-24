package dom.asistencia;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

@DomainService
public class AsistenciaAlumnoRepositorio {
	
	
	public static List<AsistenciaAlumno> queryAsistenciaAlumnoPorCursoPorDia(
			Date fecha, int anio, String division, String asistencia) {
		return container.allMatches(new QueryDefault<AsistenciaAlumno>(
				AsistenciaAlumno.class, "asistenciaAlumno_asistenciaDiaCurso", 
				"anio", anio, 
				"division", division,
				"fecha", fecha,
				"asistencia", asistencia
				));
	}
	
	
	public static List<AsistenciaAlumno> queryAsistenciaAlumnoPorCursoEnUnIntervalo (	
												String asistencia,
												int anio, 
												String division,
												Date desde,
												Date hasta,
												String dni
											){
		
		List<AsistenciaAlumno> tempList = container.allMatches(new
				QueryDefault<AsistenciaAlumno>( AsistenciaAlumno.class,
				"asistenciaAlumno_ContarAsistencias", 
				"asistencia", asistencia,
				"anio", anio, 
				"division",	division,		
				"desde", desde, 
				"hasta", hasta, 
				"dni", dni 
				));
			
		return tempList;
	}

	
	
	
	
	
	
	// region > injected services
	@javax.inject.Inject
	private static DomainObjectContainer container;

	// endregion
	
}
