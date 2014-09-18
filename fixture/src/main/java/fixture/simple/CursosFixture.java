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

import dom.simple.*;


import org.apache.isis.applib.fixturescripts.FixtureScript;

public class CursosFixture extends FixtureScript {

    public CursosFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
        execute(new GenericTearDownFixture("Curso"), executionContext);

        int Cantidad=7;
        
        // create
        for(int x=1; x<=Cantidad;x++)
        {
        // create
        create(x, GenericData.ObtenerLetras(),Turno.Mañana, executionContext);
        create(x, GenericData.ObtenerLetras(),Turno.Tarde, executionContext); 
        create(x, GenericData.ObtenerLetras(),Turno.Noche, executionContext);
        
        }
    }
    // //////////////////////////////////////

    private Curso create(final int anio, String division, Turno turno,ExecutionContext executionContext) {
        return executionContext.add(this, cursos.create(anio, division, turno ));
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private CursoRepositorio cursos;

}
