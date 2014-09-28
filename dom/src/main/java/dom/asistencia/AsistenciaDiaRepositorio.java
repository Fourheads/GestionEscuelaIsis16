package dom.asistencia;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;

@Hidden
@DomainService
public class AsistenciaDiaRepositorio {

	public List<AsistenciaDia> porFechaParaUnEsquema(LocalDate fecha, Asistencia asistencia){
	
	return container.allMatches(new QueryDefault<AsistenciaDia>(
					AsistenciaDia.class,
					"BuscarAsistenciDiaPorFechaParaUnEsquema", "fecha",
					fecha, "descripcion", asistencia.getDescripcion()));
	
	}
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	
}
