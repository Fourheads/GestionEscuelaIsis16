package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.simple.Curso;
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("LIBRO_DIARIO")
@Bookmarkable
@Bounded
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

	public void asignarMateriadeLibro(MateriaDelLibroDiario materialibrodiario)
	{
		materiaDelLibroDiarioList.add(materialibrodiario);
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
