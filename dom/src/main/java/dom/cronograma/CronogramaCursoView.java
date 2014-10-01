package dom.cronograma;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.simple.Curso;

public class CronogramaCursoView extends AbstractViewModel{

	String memento;
	
	@Override
	public String viewModelMemento() {
		// TODO Auto-generated method stub
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		
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


	// {{ Curso (property)
	private Curso curso;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}

	
	
}
