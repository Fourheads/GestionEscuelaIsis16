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

import dom.planEstudio.Anio;
import dom.planEstudio.Plan;
import dom.planEstudio.PlanRepositorio;
import dom.simple.Curso;
import dom.simple.CursoRepositorio;
import dom.simple.Turno;

public class CursoFixture extends FixtureScript{

	public CursoFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		//BorrarDBCurso(executionContext);
		
		int Cantidad=(int) ((GenericData.ObtenerCantidad()*4)/100);//La cantidad de cursos es proporcional.
		
		Turno[] turno=Turno.values();
		
		for(Plan plan:this.Plan.listarPlanes())
		{
			for(Anio anio:plan.getAnioList())
			{
				for(int x=0;x<Cantidad;x++)
				{				
					createCurso(plan, anio, GenericData.ObtenerLetras(), turno[1], executionContext);
				}
			}
		}
		
	}

    public void BorrarDBCurso(ExecutionContext executionContext)
    {
    	isisJdoSupport.executeUpdate("TRUNCATE \"Curso\" CASCADE");
    	isisJdoSupport.executeUpdate("TRUNCATE \"Curso_ListaAlumno\" CASCADE");
    	isisJdoSupport.executeUpdate("TRUNCATE \"Curso_materiaDelCursoList\" CASCADE");
    	return;
    }
    
    // //////////////////////////////////////

    private Curso createCurso(final Plan plan,Anio anio, String division,Turno turno, ExecutionContext executionContext) {
        return executionContext.add(this, this.Curso.crearCurso(plan, anio, division, turno));
    }
    
    @javax.inject.Inject
    private PlanRepositorio Plan;
    @javax.inject.Inject
    private CursoRepositorio Curso;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport; 
}


