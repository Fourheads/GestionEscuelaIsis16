package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotInServiceMenu;

@DomainService(repositoryFor = Anio.class)
public class AnioRepositorio {

	@NotInServiceMenu
	@MemberOrder(sequence = "3")
	public Anio agregarMateria(final @Named("Año") Anio anio,
			final @Named("Nombre") String nombre) {

		Materia materia = new Materia();
		materia.setNombre(nombre);
		anio.getMateriaList().add(materia);

		return anio;
	}

	// region > agregarAnio
	// //////////////////////////////////////
	@NotInServiceMenu
	@MemberOrder(sequence = "3")
	public Plan agregarAnio(final @Named("Plan") Plan plan,
			final @Named("") int anioNumero) {

		Anio nuevoAnio = new Anio();
		nuevoAnio.setAnioNumero(anioNumero);

		plan.getAnioList().add(nuevoAnio);

		return plan;
	}

	public List<Integer> choices1AgregarAnio() {

		List<Integer> aniosDisponibles = new ArrayList<Integer>();

		for (int i = 1; i < 9; i++) {
			aniosDisponibles.add(i);
		}

		return aniosDisponibles;
	}

	public int default1AgregarAnio() {
		return choices1AgregarAnio().get(0);
	}

	public String validateAgregarAnio(Plan plan, int anioNumero) {
		List<Anio> aniosList = plan.getAnioList();
		for (Anio anio : aniosList) {
			if (anio.getAnioNumero() == anioNumero) {
				return "El año '" + anioNumero + "' ya fué creado";
			}
		}

		return null;
	}

	// endRegion > agregarAnio

}
