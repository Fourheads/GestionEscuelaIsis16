package dom.libroDiario;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

import dom.simple.MateriaDelCurso;

@javax.jdo.annotations.Queries({
	@javax.jdo.annotations.Query(name = "MateriadelLibroDiarioList", language = "JDOQL", value = "SELECT FROM dom.libroDiario.MateriaDelLibroDiario"
			+ "WHERE this.libroDiario.materiaDelLibroDiarioList == :LibroDiario")})
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")

@ObjectType("MATERIAS_DEL_DIARIO")
@Bookmarkable
@Bounded
public class MateriaDelLibroDiario {
	
	// {{ Librodiario (property)
	private LibroDiario libroDiario;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(final LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}
	
	// }}
	
	// {{ MateriaDelCurso (property)
	private MateriaDelCurso materiaDelCurso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public MateriaDelCurso getMateriaDelCurso() {
		return materiaDelCurso;
	}

	public void setMateriaDelCurso(final MateriaDelCurso materiaDelCurso) {
		this.materiaDelCurso = materiaDelCurso;
	}
	// }}

	// {{ EntradaLibroDiario (Collection)
	@Join
	@Persistent(mappedBy = "materiaDelLibroDiario", dependentElement = "true")
	private SortedSet<EntradaLibroDiario> entradalibrodiario = new TreeSet<EntradaLibroDiario>();

	@MemberOrder(sequence = "1")
	public SortedSet<EntradaLibroDiario> getEntradaLibroDiario() {
		return entradalibrodiario;
	}

	public void setEntradaLibroDiario(final SortedSet<EntradaLibroDiario> entradalibrodiario) {
		this.entradalibrodiario = entradalibrodiario;
	}
	
	public void AsignarEntradaLibroDiario(EntradaLibroDiario entradalibro)
	{
		entradalibrodiario.add(entradalibro);
	}
	// }}




}
