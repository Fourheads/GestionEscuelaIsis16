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
    	for(Plan planes:this.plan.listarPlanes())
    	{
    		this.plan.eliminarPlan(planes, true);
    	}
		return;
    }
    
    // //////////////////////////////////////

    @javax.inject.Inject
    private PlanRepositorio plan;

}
