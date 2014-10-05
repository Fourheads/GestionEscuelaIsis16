package dom.horario;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.simple.Alumno;
import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

public class HorarioCursoView extends AbstractViewModel {

	String memento;

	@Override
	public String viewModelMemento() {
		// TODO Auto-generated method stub
		return memento;
	}

	@Override
	public void viewModelInit(String mementoAsString) {
		this.memento = mementoAsString;

		Memento memento = mementoService.parse(mementoAsString);

		String plan = memento.get("plan", String.class);
		setPlan(plan);
		Integer anio = memento.get("anio", Integer.class);
		setAnio(anio);
		String division = memento.get("division", String.class);
		setDivision(division);
		List<HorarioHoraSemanaView> viewList = horarioHoraSemanaService
				.crearHorarioHoraSemanaViewList(plan, anio, division);
		setHorarioHoraSemanaViewList(viewList);

	}

	// Propiedades del ModelView
	// ///////////////////////////////////////////////////////////////////////

	// {{ Plan (property)
	private String plan;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getPlan() {
		return plan;
	}

	public void setPlan(final String plan) {
		this.plan = plan;
	}

	// }}

	// {{ Anio (property)
	private Integer anio;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Integer getAnio() {
		return anio;
	}

	public void setAnio(final Integer anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	// {{ HorarioHoraSemanaViewList (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "true")
	private List<HorarioHoraSemanaView> horarioHoraSemanaViewList = new ArrayList<HorarioHoraSemanaView>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<HorarioHoraSemanaView> getHorarioHoraSemanaViewList() {
		return horarioHoraSemanaViewList;
	}

	public void setHorarioHoraSemanaViewList(
			final List<HorarioHoraSemanaView> horarioHoraSemanaViewList) {
		this.horarioHoraSemanaViewList = horarioHoraSemanaViewList;
	}

	// {{ asignarMateria (action)
	@MemberOrder(sequence = "1")
	public HorarioCursoView asignarMateria(	final @Named("Día")E_HorarioDiaSemana dia, 
											final @Named("Hora") HorarioPlanHora hora, 
											final @Named("Materia")MateriaDelCurso materia) {
		return this; 
	}
	
	public List<HorarioPlanHora> choices1AsignarMateria(){
		
		return container.allMatches(new QueryDefault<HorarioPlanHora>(HorarioPlanHora.class,
				"listarHorasDeUnPlan", "plan", getPlan()));
	}
	
	public List<MateriaDelCurso> choices2AsignarMateria(){
		return container.allMatches(new QueryDefault<MateriaDelCurso>(MateriaDelCurso.class, "MateriaDelCursoDeUnCurso", 
				"plan", getPlan(),
				"anio", getAnio(),
				"division", getDivision()));
	}
	
	
	// }}


	
	// }} (end region)
	// //////////////////////////////////////

	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	HorarioHoraSemanaService horarioHoraSemanaService;
	@javax.inject.Inject
	DomainObjectContainer container;

}
