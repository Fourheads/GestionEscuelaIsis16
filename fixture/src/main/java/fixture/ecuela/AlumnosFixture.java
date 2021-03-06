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

package fixture.ecuela;

import java.util.ArrayList;
import java.util.List;

import dom.escuela.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;
import org.joda.time.LocalDate;

public class AlumnosFixture extends FixtureScript {

    public AlumnosFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
    	BorrarDBAlumnos(executionContext);
        
        int Cantidad=GenericData.ObtenerCantidad()*14;
        
        List<Alumno> listAl=new ArrayList<Alumno>();
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	Alumno al=new Alumno();
        	al.setNombre(GenericData.ObtenerNombre());
        	al.setApellido(GenericData.ObtenerApellido());
        	
        	listAl.add(al);
        	
        }
        for(Alumno al:removerrepetidos(listAl))
        create(al.getNombre(),al.getApellido() ,Persona.E_sexo.MASCULINO,GenericData.Random(40000000, 60000000),LocalDate.now(),Persona.E_nacionalidad.ARGENTINA, Localidad.E_localidades.NEUQUEN,GenericData.ObtenerCalle(), GenericData.Random(1, 9999),null,null,String.valueOf(GenericData.Random(10000000, 88888888)), executionContext);
    }

    // //////////////////////////////////////

	private List<Alumno> removerrepetidos(List<Alumno> listaAlumno)
	{
		
		for(int x=0;x<listaAlumno.size()-1;x++)
		{
				for(int y=x+1;y<listaAlumno.size();y++)
				{
					
					if(listaAlumno.get(x).getNombre().equals(listaAlumno.get(y).getNombre()) && listaAlumno.get(x).getApellido().equals(listaAlumno.get(y).getApellido()))
					{
						listaAlumno.remove(y);
					}
					
				}
		}
		
		return listaAlumno;
	}
    
    private Alumno create(final String nombre, String apellido,Persona.E_sexo sexo, int dni, LocalDate nacimiento,Persona.E_nacionalidad nacionalidad, Localidad.E_localidades localidad, String calle, int numero, String piso,String departamento,String telefono, ExecutionContext executionContext) {
        return executionContext.add(this, alumnos.create(nombre, apellido, sexo, dni, nacimiento, nacionalidad, localidad, calle, numero, piso, departamento, telefono));
    }

    // //////////////////////////////////////

    public void BorrarDBAlumnos(ExecutionContext executionContext)
    {
        execute(new GenericTearDownFixture("Alumno"), executionContext);
    	
       return;
    }
    
    
    @javax.inject.Inject
    private AlumnoRepositorio alumnos;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport; 

}
