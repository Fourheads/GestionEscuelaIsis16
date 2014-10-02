package dom.horario;

import javax.jdo.annotations.Column;

import org.apache.isis.applib.annotation.MemberOrder;

public class HorarioPlanHora {
	
	// {{ HorarioPlan (property)
	private HorarioPlan horarioPlan;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public HorarioPlan getHorarioPlan() {
		return horarioPlan;
	}

	public void setHorarioPlan(final HorarioPlan horarioPlan) {
		this.horarioPlan = horarioPlan;
	}
	// }}

	

}
