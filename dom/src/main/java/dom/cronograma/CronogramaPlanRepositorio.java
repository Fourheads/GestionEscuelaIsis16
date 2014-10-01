package dom.cronograma;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.util.ObjectContracts;

import dom.planEstudio.Plan;
import dom.simple.CursoRepositorio;
import dom.simple.MateriaDelCursoRepositorio;

@DomainService(menuOrder = "100", repositoryFor = CronogramaPlan.class)
@Named("Cronograma")
public class CronogramaPlanRepositorio{

	@MemberOrder(sequence = "1")
	public CronogramaPlan crearUnCronogramaPlan(@Named(value = "Plan de Estudio") Plan plan){
		
		CronogramaPlan cPlan= container.newTransientInstance(CronogramaPlan.class);
		cPlan.setPlan(plan);
		container.persistIfNotAlready(cPlan);
		
		return cPlan;		
	}
	
	@Named("Listar Planes")
	@MemberOrder(sequence = "2")
	public List<CronogramaPlan> listarCronogramaPlan(){
		
		return container.allInstances(CronogramaPlan.class);
	}
	

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;


	

	// endregion
}
