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


import java.util.List;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import dom.planEstudio.*;
import dom.simple.Curso;
import dom.simple.CursoRepositorio;

public class AnioFixture extends FixtureScript{

	private List<Plan> listaPlanes=this.Plan.listarPlanes();
	
	public AnioFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		BorrarDBAnio(executionContext);
    	
		int anios=0;
		
		for(Plan planes:listaPlanes)
    	{
			if(planes.getDescripcion().contains("Tecnico"))
				anios=6;
			else
				anios=5;
			
			for(int x=1;x<=anios;x++)
			{
				create(planes, x, executionContext);
			}
    	}
		
	}
	
    private Plan create(Plan plan, int anioNumero, ExecutionContext executionContext) {
        return executionContext.add(this, Anio.agregarAnio(plan, anioNumero));
    }
	
    public void BorrarDBAnio(ExecutionContext executionContext)
    {
    	for(Plan planes: listaPlanes)
    	{
    		for(Anio anios: planes.getAnioList())
    		{
    			if(!planes.getAnioList().isEmpty())
    			{
    				Anio.eliminarAnio(planes, anios, true);
    			}
    		}
    	}
		return;
    }
	
    @javax.inject.Inject
    private PlanRepositorio Plan;
    @javax.inject.Inject
    private AnioRepositorio Anio;


}
