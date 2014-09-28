package dom.planEstudio;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;

@Hidden
@DomainService
public class NavegacionServicio {

	@MemberOrder(sequence = "1")
	@Named("< Volver al Plan")
	@PublishedAction
	public Plan volver(final @Named("Año") Anio anio){
		return anio.getPlan();
	}

	@MemberOrder(sequence = "1")
	@Named("< Volver a la Materia")
	@PublishedAction
	public Anio volver(final @Named("Año") Materia materia){
		return materia.getAnio();
	}
}
