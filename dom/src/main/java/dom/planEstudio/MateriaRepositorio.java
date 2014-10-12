package dom.planEstudio;

import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotInServiceMenu;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.query.QueryDefault;
import org.datanucleus.store.query.Query;

import dom.libroDiario.LibroDiarioRepositorio;
import dom.libroDiario.MateriaDelLibroDiario;
import dom.simple.Curso;
import dom.simple.CursoRepositorio;
import dom.simple.MateriaDelCurso;
import dom.simple.MateriaDelCursoRepositorio;

@Hidden
@DomainService(repositoryFor = Materia.class)
public class MateriaRepositorio {

	// {{ listarMateriasDeUnAnio (action)
	@MemberOrder(sequence = "2")
	@NotContributed
	@NotInServiceMenu
	public List<Materia> listarMateriasDeUnAnio(final @Named("Plan") Plan plan,
			final @Named("Año") Anio anio) {
		return container.allMatches(new QueryDefault<Materia>(Materia.class,
				"listarMateriasDeUnAnio", "anio", anio.getAnioNumero(), "plan",
				plan.getDescripcion()));
	}

	public Plan default0ListarMateriasDeUnAnio() {
		List<Plan> tempList = container.allInstances(Plan.class);
		if (tempList.isEmpty()) {
			return null;
		}
		return tempList.get(0);
	}

	public SortedSet<Anio> choices1ListarMateriasDeUnAnio(Plan plan) {

		return plan.getAnioList();
	}

	// }}

	// region > agregarMateria
	// //////////////////////////////////////
	@Hidden
	@MemberOrder(sequence = "3")
	public Anio agregarMateria(final @Named("Año") Anio anio,
			final @Named("Nombre") String nombre) {

		Materia materia = new Materia();
		materia.setNombre(nombre);
		anio.getMateriaList().add(materia);

		agregarMateriaCursosYaCreados(anio, materia);
		
		return anio;
	}

	// endregion > agregarMateria

	// region > eliminarMateria
	// //////////////////////////////////////
	@NotInServiceMenu
	@NotContributed
	@MemberOrder(sequence = "3")
	@DescribedAs(value = "Eliminar una Materia de este Año. Esto se aplica a todos los Cursos ya creados")
	public Anio eliminarMateria(final @Named("Año") Anio anio,
			final @Named("Nombre") Materia materia,
			final @Named("Esta seguro?") Boolean seguro) {
		
		eliminarMateriaCursosYaCreados(anio, materia);
		
		container.remove(materia);
		return anio;
	}

	public List<Materia> choices1EliminarMateria(final Anio anio) {

		return anio.getMateriaList();
	}

	public Materia default1EliminarMateria(final Anio anio) {
		if (choices1EliminarMateria(anio).isEmpty()) {
			return null;
		}

		return choices1EliminarMateria(anio).get(0);
	}

	public String validateEliminarMateria(Anio anio, Materia materia, Boolean seguro) {
		if (!seguro) {
			return "Debe chequear la confirmacion. Tenga en cuenta que esta "
					+ "eliminacion se aplica a todos los cursos de este Año ya creados ";
		}
		return null;
	}

	// endregion > agregarMateria

	@Programmatic
	private void eliminarMateriaCursosYaCreados(Anio anio, Materia materia) {
		
		List<Curso> cursoList = cursoRepositorio.listarCursosDeUnAnio(anio.getPlan(), anio);
		
		for (Curso curso : cursoList){
			List<MateriaDelCurso> materiaDelCursoList = curso.getMateriaDelCursoList();
			List<MateriaDelLibroDiario> materiadellibrodiarioList=librodiariorepositorio.mostrarLibroDiarioDelCurso(curso).getMateriaDelLibroDiarioList();
			for (MateriaDelCurso materiaDelCurso : materiaDelCursoList){
				if (materiaDelCurso.getMateria() == materia){
					container.remove(materiaDelCurso);
				}
			}
			for (MateriaDelLibroDiario materialibrodiario : materiadellibrodiarioList){
				if (materialibrodiario.getMateriaDelCurso().getMateria() == materia){
					container.remove(materialibrodiario);
				}
			}
		}
	}

	private void agregarMateriaCursosYaCreados(Anio anio, Materia materia) {
		
		List<Curso> cursoList = cursoRepositorio.listarCursosDeUnAnio(anio.getPlan(), anio);
		for (Curso curso : cursoList){
			materiaDelCursoRepositorio.crearMateriaDelCurso(curso, materia);
			librodiariorepositorio.ModificarLibroDiario(curso, librodiariorepositorio.mostrarLibroDiarioDelCurso(curso));
		}
		
	}
	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	CursoRepositorio cursoRepositorio;
	@javax.inject.Inject
	MateriaDelCursoRepositorio materiaDelCursoRepositorio;
	@javax.inject.Inject
	LibroDiarioRepositorio librodiariorepositorio;
	// endregion

}
