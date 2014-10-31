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

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;

import dom.planEstudio.*;

public class MateriaFixture extends FixtureScript{

	public MateriaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		BorrarDBMaterias(executionContext);
		
		for(Plan plan:this.Plan.listarPlanes())
		{
			for(Anio anio:plan.getAnioList())
			{
				int fin=GenericData.Random(10, 15);
				for(int x=0;x<=fin;x++)
				{
					Materia materia=new Materia();
					materia.setAnio(anio);
					materia.setNombre(GenericData.ObtenerCiencia()+" "+anio.getAnioNumero()+"°");
					materia.setPrograma("Test Materia Fixture: "+materia.getNombre());
					
					anio.getMateriaList().add(materia);
				}
			}
		}
		
	}
	
    public void BorrarDBMaterias(ExecutionContext executionContext)
    {
    	isisJdoSupport.executeUpdate("TRUNCATE \"Materia\" CASCADE");
    	isisJdoSupport.executeUpdate("TRUNCATE \"MateriaCalificacion\" CASCADE");
    	isisJdoSupport.executeUpdate("TRUNCATE \"MateriaDelCurso\" CASCADE");
    	isisJdoSupport.executeUpdate("TRUNCATE \"MateriaDelLibroDiario\" CASCADE");
    	isisJdoSupport.executeUpdate("TRUNCATE \"MateriaDelLibroDiario_entradalibrodiario\" CASCADE");
    	return;
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private PlanRepositorio Plan;
    @javax.inject.Inject
    private AnioRepositorio Anio;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport; 
}
