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

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Prototype;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScripts;


@Named("Test")
@DomainService(menuOrder = "200")
public class ServicesFixtures extends FixtureScripts {

    public ServicesFixtures() {
    	super("fixture.ecuela");
	}

    
    
   	@Prototype
    @MemberOrder(sequence="1")
    public void instalarFixturesUsers() {
        final List<FixtureResult> Users = findFixtureScriptFor(UsersFixture.class).run(null);
        //return Users.get(0).getObject();
    }

    
	@Prototype
    @MemberOrder(sequence="10")
    public Object instalarFixturesAlumnos() {
        final List<FixtureResult> alumnos = findFixtureScriptFor(AlumnosFixture.class).run(null);
        return alumnos.get(0).getObject();
    }
    
   
    
    @Prototype
    @MemberOrder(sequence="20")
    public Object instalarFixturesPersonal() {
        final List<FixtureResult> personal = findFixtureScriptFor(PersonalFixture.class).run(null);
        return personal.get(0).getObject();
    }
    
    @Prototype
    @MemberOrder(sequence="30")
    public Object instalarFixturesPlan() {
        final List<FixtureResult> planes = findFixtureScriptFor(PlanFixture.class).run(null);
        return planes.get(0).getObject();
    }
    
    @Prototype
    @MemberOrder(sequence="40")
    public Object instalarFixturesMaterias() {
        final List<FixtureResult> Materias = findFixtureScriptFor(MateriaFixture.class).run(null);
        return Materias.get(0).getObject();
    }
    
    @Prototype
    @MemberOrder(sequence="50")
    public Object instalarFixturesCurso() {
        final List<FixtureResult> Curso = findFixtureScriptFor(CursoFixture.class).run(null);
        return Curso.get(0).getObject();
    }
    
    
    @Prototype
    @MemberOrder(sequence="60")
    public String instalarFixturesHorario() {
        final List<FixtureResult> Horario = findFixtureScriptFor(HorarioFixture.class).run(null);
        return "Fixture Horario Instalado exitosamente";//Horario.get(0).getObject();
    }
    
    @Prototype
    @MemberOrder(sequence="70")
    public Object instalarFixturesCalificaciones() {
        final List<FixtureResult> AlumnoCalificacion = findFixtureScriptFor(CalificacionesFixture.class).run(null);
        return AlumnoCalificacion.get(0).getObject();
    }
    
    @Prototype
    @MemberOrder(sequence="80")
    public Object instalarFixturesAsistencia() {
        final List<FixtureResult> Asistencia = findFixtureScriptFor(AsistenciaFixture.class).run(null);
        return Asistencia.get(0).getObject();
    }
     
    
    
	@MemberOrder(sequence="100")
    public String BorrarBD()
    {
		final List<FixtureResult> Borrar=findFixtureScriptFor(GenericTearDownFixture.class).run(null);
		
		return "Se ha completado la operacion. Toda la DB ah sido borrada.";
    }
    
    
    @MemberOrder(sequence="99")
    public String IntstalarTodosLosFixtures()
    {
    	this.instalarFixturesPersonal();
    	
    	this.instalarFixturesAlumnos();

    	this.instalarFixturesPlan();
    	
    	this.instalarFixturesHorario();
    	
    	this.instalarFixturesMaterias();
    	
    	this.instalarFixturesCurso();
    	
    	this.instalarFixturesCalificaciones();
    	
    	this.instalarFixturesAsistencia();
    	
    	return "Todos los fixtures intastalados";
    }

}
