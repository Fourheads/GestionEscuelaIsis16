package fixture.simple;


import dom.planEstudio.*;
import dom.simple.Localidad;
import dom.simple.Persona;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.joda.time.LocalDate;


public class PlanFixture extends FixtureScript{

	
	public PlanFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		
		BorrarDBPlan(executionContext);
		
		
		int Cantidad=GenericData.ObtenerCantidad();
		
        for(int x=0; x<Cantidad;x++)
        {
        	create(GenericData.ObtenerPlan(), executionContext);
        }
		
	}
	
    private Plan create(final String descripcion, ExecutionContext executionContext) {
        return executionContext.add(this, plan.crearPlan(descripcion));
    }
    
    
    public void BorrarDBPlan(ExecutionContext executionContext)
    {
    	//execute(new GenericTearDownFixture("HorarioPlan"), executionContext);
    	//execute(new GenericTearDownFixture("Plan_aniolist"), executionContext);
		execute(new GenericTearDownFixture("Plan"), executionContext);
		return;
    }
    
    // //////////////////////////////////////

    @javax.inject.Inject
    private PlanRepositorio plan;

}
