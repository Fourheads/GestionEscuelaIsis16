/*
 *  Licensed to the Apache Software Foundation (ASF) under one
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

import java.util.List;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Prototype;
import org.apache.isis.applib.fixturescripts.FixtureResult;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScripts;
import org.apache.isis.applib.fixturescripts.SimpleFixtureScript;

import dom.planEstudio.Anio;
import dom.planEstudio.Plan;

/**
 * Enables fixtures to be installed from the application.
 */
@Named("Test")
@DomainService(menuOrder = "200")
public class SimpleObjectsFixturesService extends FixtureScripts {

    public SimpleObjectsFixturesService() {
        super("fixture.simple");
    }

   
    @Override // compatibility with core 1.5.0
    public FixtureScript default0RunFixtureScript() {
        return findFixtureScriptFor(SimpleFixtureScript.class);
    }

    /**
     * Raising visibility to <tt>public</tt> so that choices are available for first param
     * of {@link #runFixtureScript(FixtureScript, String)}.
     */
  
    
    @Override
    public List<FixtureScript> choices0RunFixtureScript() {
        return super.choices0RunFixtureScript();
    }
    	

    // //////////////////////////////////////

    @Hidden
    @Prototype
    @MemberOrder(sequence="20")
    public Object installFixturesAndReturnFirst() {
        final List<FixtureResult> run = findFixtureScriptFor(SimpleObjectsFixture.class).run(null);
        return run.get(0).getObject();
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
    
}
