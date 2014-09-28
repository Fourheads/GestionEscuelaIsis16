package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.query.QueryDefault;

@DomainService(menuOrder = "10", repositoryFor = Plan.class)
public class PlanRepositorio {

	// {{ crearPlan (action)
	@MemberOrder(sequence = "1")
	public Plan crearPlan(final String descripcion) {

		Plan plan = container.newTransientInstance(Plan.class);

		plan.setDescripcion(descripcion);

		container.persistIfNotAlready(plan);

		return plan;
	}

	// }}

	// {{ listarPlanes (action)
	@MemberOrder(sequence = "1.2")
	public List<Plan> listarPlanes() {
		return queryListarPlanesAlfabeticamente();
	}
	@Programmatic
	public List<Plan> queryListarPlanesAlfabeticamente() {
		return container.allMatches(new QueryDefault<Plan>(Plan.class,
				"listarPlanes" 
				));
	}

	// }}

	// {{ seleccionar un Plan (action)
	@MemberOrder(sequence = "1.5")
	@NotContributed
	public Plan seleccionarUnPlan(Plan plan) {
		return plan;
	}
	
	public List<Plan> choices0SeleccionarUnPlan(){
		return queryListarPlanesAlfabeticamente();
	}
	
	public Plan default0SeleccionarUnPlan(){
		List<Plan> planList = queryListarPlanesAlfabeticamente();
		if (planList.isEmpty()){
			return null;
		}
		return planList.get(0);
	}

	// }}

	

	// {{ EliminarPlan (action)
	@MemberOrder(sequence = "1.1")
	public String eliminarPlan(final @Named("Plan a eliminar") Plan plan,
			final @Named("Esta seguro?") Boolean seguro) {
		String descripcion = plan.getDescripcion();
		container.remove(plan);
		return "El plan de estudio '" + descripcion + "' ha sido Eliminado";
	}

	public String validateEliminarPlan(Plan plan, Boolean seguro) {
		if (!seguro) {
			return "Marque en la opcion si está seguro!!! Si no lo está cancele esta opción";
		}

		return null;
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
