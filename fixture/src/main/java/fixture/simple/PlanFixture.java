/*
 * This is a software made for highschool management 
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */


package fixture.simple;


import java.util.ArrayList;
import java.util.List;

import dom.planEstudio.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;

public class PlanFixture extends FixtureScript{

	private List<Plan> listaPlanes=new ArrayList<Plan>();
	
	public PlanFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBPlan(executionContext);
		
		//int Cantidad=(int) ((GenericData.ObtenerCantidad()*6)/100);//La cantidad de planes es proporcional.
		
		int Cantidad=50;
		
        for(int x=0; x<Cantidad;x++)
        {
        	listaPlanes.add(createPlan(GenericData.ObtenerPlan(), executionContext));
        }
        
        
        int anios=9;
        
		for(Plan planes:listaPlanes)
    	{
			if(planes.getDescripcion().contains("Tecnico"))
				anios=6;
			else
				anios=5;
			
			for(int x=1;x<=anios;x++)
			{
				createAnio(planes, x, executionContext);
			}
    	}

	}
	
    private Plan createPlan(final String descripcion, ExecutionContext executionContext) {
        return executionContext.add(this, plan.crearPlan(descripcion));
    }
    
    public Plan createAnio(Plan plan, int anioNumero, ExecutionContext executionContext) {
        return executionContext.add(this, Anio.agregarAnio(plan, anioNumero));
    }
    
    public void BorrarDBPlan(ExecutionContext executionContext)
    {
    		//this.plan.eliminarPlan(planes, true);
    		isisJdoSupport.executeUpdate("TRUNCATE \"Plan\" CASCADE");
    		isisJdoSupport.executeUpdate("TRUNCATE \"Anio\" CASCADE");
    		//isisJdoSupport.executeUpdate("DROP TABLE \"Plan\" CASCADE");
		return;
    }
    
    // //////////////////////////////////////

    @javax.inject.Inject
    private PlanRepositorio plan;
    @javax.inject.Inject
    private AnioRepositorio Anio;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport; 
}
