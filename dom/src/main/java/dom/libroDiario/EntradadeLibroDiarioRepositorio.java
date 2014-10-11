package dom.libroDiario;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Named;
import org.joda.time.LocalDate;

@Named("Entrada libro diario")
@DomainService(repositoryFor = EntradaLibroDiario.class)
public class EntradadeLibroDiarioRepositorio {

	public EntradaLibroDiario crearEntradadeLibroDiario(LocalDate fecha,
			int horas, int unidad, String actividad, String observaciones) {
		
		EntradaLibroDiario entradalibrodiario = container.newTransientInstance(EntradaLibroDiario.class);
		

		entradalibrodiario.setFecha(fecha);
		
		HoraCatedra horacate= horacatedra.crearhoracatedra(horas, unidad, actividad, observaciones);
		
		entradalibrodiario.asignarHoracatedra(horacate);
		
		
		container.persistIfNotAlready(entradalibrodiario);
		return entradalibrodiario;
	}

	public LocalDate default0CrearEntradadeLibroDiario() {
		
		new LocalDate();
		LocalDate fecha =LocalDate.now();
		
		return fecha;
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	HoraCatedraRepositorio horacatedra; 
	
}
