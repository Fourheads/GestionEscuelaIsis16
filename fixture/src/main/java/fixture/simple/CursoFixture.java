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

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;

import dom.planEstudio.Anio;
import dom.planEstudio.Materia;
import dom.planEstudio.Plan;
import dom.planEstudio.PlanRepositorio;
import dom.simple.Alumno;
import dom.simple.Curso;
import dom.simple.CursoRepositorio;
import dom.simple.MateriaDelCurso;
import dom.simple.Personal;
import dom.simple.Turno;

public class CursoFixture extends FixtureScript{

	public CursoFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBCurso(executionContext);
		
		int Cantidad=(int) Math.ceil((GenericData.ObtenerCantidad()*4)/100);//La cantidad de cursos es proporcional.
		
		if(Cantidad<1)
			Cantidad=1;
		
		Turno[] turno=Turno.values();
		
		int valorturno=0;
		
		List<Plan> Lplanes=Plan.listarPlanes();
		
		
		List<Curso> Lcursos=new ArrayList<Curso>();
		
		int refcantidad=Cantidad;
		
		for(Plan plan: Lplanes)
		{
			Cantidad=CantidaddeCursos(Cantidad, plan);
			
			for(Anio anio:plan.getAnioList())
			{
				for(int x=1;x<=Cantidad;x++)
				{				
					if(x%2==0)
						valorturno=0;
					else
						valorturno=1;
					
					Lcursos.add(createCurso(plan, anio, GenericData.ObtenerLetras(), turno[valorturno], executionContext));
				}
			}
			
			Cantidad=refcantidad;
		}
		
		for(Plan plan: Lplanes)
		{
		
			for(Curso curso: Lcursos)
			{	
				createPreceptorCurso(plan, curso, curso.choices0AsignarPreceptor().get(GenericData.Random(0, curso.choices0AsignarPreceptor().size())), executionContext);
				
				for(MateriaDelCurso materiadelcurso: curso.getMateriaDelCursoList())
				{	
					createProfesorCurso(plan, curso, materiadelcurso, curso.choices1AsignarProfesorAMateria().get(GenericData.Random(0, curso.choices1AsignarProfesorAMateria().size())), executionContext);
				}
				
				for(int x=0; x<alumnosxcurso(Cantidad, plan);x++)
				{
					if(curso.choices0AsignarAlumnos()!=null)
						curso.asignarAlumnos(curso.choices0AsignarAlumnos().get(0));
					else
						x=alumnosxcurso(Cantidad, plan);
				}
				
			}
		}
		
		
	}
	
	private int CantidaddeCursos(int Cantidad, Plan plan)
	{
		while (alumnosxcurso(Cantidad, plan)>30) {
			Cantidad++;
		}
		return Cantidad;
	}
	
	private int alumnosxcurso(int Cantidad, Plan plan)
	{
		 int Alumnosporcurso=(int) Math.ceil(((GenericData.ObtenerCantidad()*5)/(plan.getAnioList().size()*Cantidad)));
		 return Alumnosporcurso;
	}

    public void BorrarDBCurso(ExecutionContext executionContext)
    {
    	execute(new GenericTearDownFixture("Curso"),executionContext);
    	execute(new GenericTearDownFixture("Curso_ListaAlumno"),executionContext);
    	execute(new GenericTearDownFixture("Curso_materiaDelCursoList"),executionContext);
    	execute(new GenericTearDownFixture("LibroDiario"),executionContext);
    	execute(new GenericTearDownFixture("LibroDiario_materiaDelLibroDiarioList"),executionContext);

    	return;
    }
    
    
    // //////////////////////////////////////

    private Curso createCurso(final Plan plan,Anio anio, String division,Turno turno, ExecutionContext executionContext) {
        return executionContext.add(this, this.Curso.crearCurso(plan, anio, division, turno));
    }
    
    private Curso createProfesorCurso(Plan plan, Curso curso, MateriaDelCurso materia, Personal profesor, ExecutionContext executionContext) {
        return executionContext.add(this, this.Curso.asignarProfesorAMateriaDelCurso(plan, curso, materia, profesor));
    }
    
    private Curso createPreceptorCurso(Plan plan, Curso curso, Personal preceptor, ExecutionContext executionContext) {
        return executionContext.add(this, this.Curso.asignarPreceptorAlCurso(plan, curso, preceptor));
    }
    
    
    @javax.inject.Inject
    private PlanRepositorio Plan;
    @javax.inject.Inject
    private CursoRepositorio Curso;
}


