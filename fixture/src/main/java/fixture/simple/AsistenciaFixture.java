/*
 * This is a software made for highschool management 
 * 
 * Copyright (C) 2014, Fourheads
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * 
 * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package fixture.simple;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import dom.planEstudio.Plan;
import dom.simple.Curso;
import dom.simple.Personal;

public class AsistenciaFixture extends FixtureScript {

	public AsistenciaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBAsistencia(executionContext);
		
	}
	
    private void BorrarDBAsistencia(ExecutionContext executionContext) {
    	execute(new GenericTearDownFixture(""),executionContext);

    	return;	
	}

	//private Curso createPreceptorCurso(Plan plan, Curso curso, Personal preceptor, ExecutionContext executionContext) {
       // return executionContext.add());
    //}

}
