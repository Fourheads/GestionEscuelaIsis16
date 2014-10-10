package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.simple.Curso;

@Bounded
@PersistenceCapable
public class LibroDiario {
	
	// {{ Curso (property)
	private Curso curso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}

	// MateriaDelLibroDiarioList (Collection)
	// //////////////////////////////////////////
	
	@Persistent(mappedBy = "libroDiario", dependentElement = "true")
	@Join
	private List<MateriaDelLibroDiario> materiaDelLibroDiarioList = new ArrayList<MateriaDelLibroDiario>();

	@MemberOrder(sequence = "1")
	@Render(Type.EAGERLY)
	public List<MateriaDelLibroDiario> getMateriaDelLibroDiarioList() {
		return materiaDelLibroDiarioList;
	}

	public void setMateriaDelLibroDiarioList(final List<MateriaDelLibroDiario> materiaDelLibroDiarioList) {
		this.materiaDelLibroDiarioList = materiaDelLibroDiarioList;
	}
	// end region MateriaDelLibroDiarioList (Collection)

	// Title (GUI)
	// //////////////////////////////////////////

	public String title() {
		return "Libro diario de " + curso.title();
	}

	// end region Title (GUI)
	// //////////////////////////////////////////

}
