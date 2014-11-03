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
import org.joda.time.LocalDate;

import dom.planEstudio.*;


public class MateriaFixture extends FixtureScript{

	public MateriaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBMaterias(executionContext);
		
		
		for(Plan plan:Plan.listarPlanes())
		{
			for(Anio anio:plan.getAnioList())
			{
				int fin=GenericData.Random(10, 15);
				for(int x=0;x<=fin;x++)
				{
					createMate(GenericData.ObtenerCiencia()+" "+anio.getAnioNumero()+"°", anio, executionContext);
				}
			}
		}
	}
	
    private Anio createMate(final String nombre, Anio anio, ExecutionContext executionContext) {
        return executionContext.add(this, materia.agregarMateria(anio, nombre));
    }
	
    public void BorrarDBMaterias(ExecutionContext executionContext)
    {
    	execute(new GenericTearDownFixture("Materia"),executionContext);
    	execute(new GenericTearDownFixture("MateriaCalificacion"),executionContext);
    	execute(new GenericTearDownFixture("MateriaDelCurso"),executionContext);
    	execute(new GenericTearDownFixture("MateriaDelLibroDiario"),executionContext);
    	execute(new GenericTearDownFixture("MateriaDelLibroDiario_entradalibrodiario"),executionContext);
    	
    	return;
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private PlanRepositorio Plan;
    @javax.inject.Inject
    private MateriaRepositorio materia;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport; 
}
