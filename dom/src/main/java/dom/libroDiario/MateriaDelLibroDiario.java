package dom.libroDiario;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

import dom.simple.MateriaDelCurso;



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
	@Element(dependent = "False")
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
