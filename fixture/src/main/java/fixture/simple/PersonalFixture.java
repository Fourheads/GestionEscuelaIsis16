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

import dom.simple.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.LocalDate;

public class PersonalFixture extends FixtureScript {


    public PersonalFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
    	//GenericTearDownFixture GenericPersonal=new GenericTearDownFixture("Personal");
    	//GenericPersonal.agregarOtraclase("Funcion");
    	execute(new GenericTearDownFixture("Funcion"), executionContext);
        execute(new GenericTearDownFixture("Personal"), executionContext);
        
        List<Personal> listPersonal=new ArrayList<Personal>();
        Funcion.E_funciones[] Funciones=Funcion.E_funciones.values();
        
        int Cantidad=50;
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	listPersonal.add(create(GenericData.ObtenerNombre(),GenericData.ObtenerApellido() ,Persona.E_sexo.MASCULINO,GenericData.Random(15000000, 50000000),LocalDate.now(),Persona.E_nacionalidad.ARGENTINA, Localidad.E_localidades.NEUQUEN,GenericData.ObtenerCalle(), GenericData.Random(1, 9999),null,null,String.valueOf(GenericData.Random(10000000, 88888888)), executionContext));
        }
        
        boolean IsTrue=true;
        int count=0;
        for(Personal personal : listPersonal)
        	{
        		for(int x=0;x<=GenericData.Random(0, 1);x++)
        		{
        			Funcion newfuncion=new Funcion();
        		
        			do
        				newfuncion.setNombre(Funciones[GenericData.Random(0, Funciones.length)]);
        			while(newfuncion.getNombre()==Funcion.E_funciones.DIRECTOR && IsTrue==false);
        		
        			if(newfuncion.getNombre()==Funcion.E_funciones.DIRECTOR)
        				count++;
        			
        			if(count==3)
        				IsTrue=false;
        		
        			String concate=personal.getNombre()+" "+personal.getApellido()+" desempeña la labor de "+newfuncion.getNombre().toString();	
        		
        			personal.createFuncion(newfuncion.getNombre(), concate);
        		}
        	}
    }
    // //////////////////////////////////////

    private Personal create(final String nombre, String apellido,Persona.E_sexo sexo, int dni, LocalDate nacimiento,Persona.E_nacionalidad nacionalidad, Localidad.E_localidades localidad, String calle, int numero, String piso,String departamento,String telefono, ExecutionContext executionContext) {
        return executionContext.add(this, personal.createPersonal(nombre, apellido, sexo, dni, nacimiento, nacionalidad, localidad, calle, numero, piso, departamento, telefono));
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private PersonalRepositorio personal;

}