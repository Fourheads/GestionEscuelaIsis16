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
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;
import org.joda.time.LocalDate;

public class PersonalFixture extends FixtureScript {

	private Funcion.E_funciones[] Funciones=Funcion.E_funciones.values();
	private int[][] proporcionado;
	private int control=Funciones.length-1;
    
	public PersonalFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
    	BorrarDBPersonal(executionContext);
        
        List<Personal> listPersonal=new ArrayList<Personal>();
        
        int Cantidad=GenericData.ObtenerCantidad();
        proporciones(Cantidad);
        
        // create
        for(int x=0; x<Cantidad;x++)
        {
        	listPersonal.add(create(GenericData.ObtenerNombre(),GenericData.ObtenerApellido() ,Persona.E_sexo.MASCULINO,GenericData.Random(15000000, 40000000),LocalDate.now(),Persona.E_nacionalidad.ARGENTINA, Localidad.E_localidades.NEUQUEN,GenericData.ObtenerCalle(), GenericData.Random(1, 9999),null,null,String.valueOf(GenericData.Random(10000000, 88888888)), executionContext));
        }
        

        for(Personal personal : listPersonal)
        	{
        		for(int x=0;x<=GenericData.Random(0, 1);x++)
        		{
        			Funcion newfuncion=traerfuncion();
        		
        			String concate=personal.getNombre()+" "+personal.getApellido()+" desempeña la labor de "+newfuncion.getNombre().toString();	
        		
        			personal.createFuncion(newfuncion.getNombre(), concate);
        		}
        	}
    }
    // //////////////////////////////////////

    private void proporciones(int cantidad)
    {
    	proporcionado=new int[Funciones.length][2];
    	int[] Enproporcion= new int[Funciones.length];
    	Enproporcion[0]=80; //Profesores 80%
    	Enproporcion[1]=12; //Preceptores 12%
    	Enproporcion[2]=6; //Secretarios 6%
    	Enproporcion[3]=2; //Directivos 2%
    	
    	for(int x=0; x<proporcionado.length;x++)
    		proporcionado[x][0]=(int)((cantidad*Enproporcion[x])/100);
    }
    
    private Funcion traerfuncion()
    {
    	Funcion newfuncion=new Funcion();
		
		newfuncion.setNombre(Funciones[control]);
    	if(proporcionado[control][1]<proporcionado[control][0])
    		proporcionado[control][1]++;
    	else
    	{
    		if(control>0)
    			control--;
    	}

    	return newfuncion;
    }

    
    public void BorrarDBPersonal(ExecutionContext executionContext)
    {
    	execute(new GenericTearDownFixture("Funcion"), executionContext);
        execute(new GenericTearDownFixture("Personal"), executionContext);

        return;
    }
    
    private Personal create(final String nombre, String apellido,Persona.E_sexo sexo, int dni, LocalDate nacimiento,Persona.E_nacionalidad nacionalidad, Localidad.E_localidades localidad, String calle, int numero, String piso,String departamento,String telefono, ExecutionContext executionContext) {
        return executionContext.add(this, personal.createPersonal(nombre, apellido, sexo, dni, nacimiento, nacionalidad, localidad, calle, numero, piso, departamento, telefono));
    }
    
    // //////////////////////////////////////

    @javax.inject.Inject
    private PersonalRepositorio personal;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport; 
}