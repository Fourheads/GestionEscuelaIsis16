package dom.horario;

import dom.simple.MateriaDelCurso;

import java.util.Date;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.MemberOrder;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class HorarioHora {
	// {{ HorarioDia (property)
	private HorarioDia horarioDia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioDia getHorarioDia() {
		return horarioDia;
	}

	public void setHorarioDia(final HorarioDia horarioDia) {
		this.horarioDia = horarioDia;
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
	
	// {{ HorarioHoraTipo (property)
	private E_HorarioHoraTipo horarioHoraTipo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public E_HorarioHoraTipo getHorarioHoraTipo() {
		return horarioHoraTipo;
	}

	public void setHorarioHoraTipo(final E_HorarioHoraTipo horarioHoraTipo) {
		this.horarioHoraTipo = horarioHoraTipo;
	}
	// }}


	
}
