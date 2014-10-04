package dom.horario;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.simple.Curso;

public class HorarioCursoView extends AbstractViewModel{

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
		
		
	}

	
	
	// Propiedades del ModelView
	/////////////////////////////////////////////////////////////////////////
	
	
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



	@javax.inject.Inject
	MementoService mementoService;

	
}
