package dom.simple;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.joda.time.LocalDate;

import dom.simple.Tarjeta.ECategoria;

@DomainService
@Hidden
public class LegajoRepositorio {

	@NotInServiceMenu
	@MemberOrder(sequence = "1.1", name = "Nueva Tarjeta")
	@Named("Nueva Tarjeta")
	public Legajo create (Legajo legajo,
			final @Named("Titulo") String titulo,
			final @MaxLength(2048)
		    	  @MultiLine 
		    	  @Named("Comentarios") String comentario,
		    final @Named("Categoria de Tarjeta") ECategoria categoria	  
			){
		
		final Tarjeta tarjeta = new Tarjeta();
		LocalDate fecha = LocalDate.now();
		
		tarjeta.setTitulo(titulo);
		tarjeta.setComentarios(comentario);
		tarjeta.setFecha(fecha);
		tarjeta.setCategoria(categoria);
		legajo.addTarjeta(tarjeta);
		
		//container.persistIfNotAlready(tarjeta);
		legajo.addTarjeta(tarjeta);
		return legajo;
	}
}
