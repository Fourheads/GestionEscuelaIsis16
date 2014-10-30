package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.applib.query.QueryDefault;

import dom.simple.Curso;
import dom.simple.CursoRepositorio;

@Hidden
@DomainService(repositoryFor = Anio.class)
public class AnioRepositorio {

	// region > agregarAnio
	// //////////////////////////////////////
	@NotInServiceMenu
	@Named("Agregar Año al Plan")
	@MemberOrder(sequence = "1", name = "Listado de Años del Plan")
	public Plan agregarAnio(final @Named("Plan") Plan plan,
			final @Named("") int anioNumero) {

		Anio nuevoAnio = new Anio();
		nuevoAnio.setAnioNumero(anioNumero);

		plan.getAnioList().add(nuevoAnio);

		return plan;
	}

	public List<Integer> choices1AgregarAnio(final @Named("Plan") Plan plan) {

		List<Integer> aniosDisponibles = new ArrayList<Integer>();
		List<Integer> aniosCreados = new ArrayList<Integer>();
		List<Anio> anioList = listarAniosDeUnPlan(plan);

		for (Anio anio : anioList) {
			aniosCreados.add(anio.getAnioNumero());
		}

		for (int i = 1; i < 9; i++) {
			aniosDisponibles.add(i);
		}

		aniosDisponibles.removeAll(aniosCreados);

		return aniosDisponibles;
	}

	public String validateAgregarAnio(Plan plan, int anioNumero) {
		SortedSet<Anio> aniosList = plan.getAnioList();
		for (Anio anio : aniosList) {
			if (anio.getAnioNumero() == anioNumero) {
				return "El año '" + anioNumero + "' ya fué creado";
			}
		}

		return null;
	}

	// endRegion > agregarAnio

	// {{ eliminarAnio (action)
	@NotInServiceMenu
	@Named("Eliminar Año del Plan")
	@MemberOrder(sequence = "2", name = "Listado de Años del Plan")
	public Plan eliminarAnio(final Plan plan, @Named("Año") Anio anio,
			@Named("¿Esta Seguro?") Boolean seguro) {
		
		List<Curso> CursoList=curso.listarCursosDeUnAnio(plan, anio);
		
		for(int x=0; x<=CursoList.size();x++)
		{			
			curso.eliminarCurso(plan,CursoList.get(x), true);
		}
		
		for(Materia mate:anio.getMateriaList())
		{			
			materia.eliminarMateria(anio, mate, true);
		}
		
		plan.getAnioList().clear();
		anio.setPlan(null);
		anio.getMateriaList().clear();

		
		container.remove(anio);
		return plan;
	}

	public SortedSet<Anio> choices1EliminarAnio(final Plan plan) {
		return plan.getAnioList();
	}

	// }}

	@NotContributed
	public List<Anio> listarAniosDeUnPlan(Plan plan) {
		return container.allMatches(new QueryDefault<Anio>(Anio.class,
				"listarAniosDeUnPlan", "plan", plan.getDescripcion()));
	}
	


	@Inject
	DomainObjectContainer container;
    @javax.inject.Inject
    private CursoRepositorio curso;
    @javax.inject.Inject
    private MateriaRepositorio materia;
}
