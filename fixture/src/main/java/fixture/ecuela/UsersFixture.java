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

import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import dom.seguridad.Permission;
import dom.seguridad.PermissionRepository;
import dom.seguridad.Role;
import dom.seguridad.RoleRepository;
import dom.seguridad.ShiroUser;
import dom.seguridad.ShiroUserRepository;
import dom.*;

public class UsersFixture extends FixtureScript{

	public UsersFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		List<Permission> listapermisos=new ArrayList<Permission>();
		List<Role>	listaroles=new ArrayList<Role>();
		
		listapermisos.add(create("Alumnos-listartodos-lectura","dom:escuela:AlumnoRepositorio:listAll:r", executionContext));
		listapermisos.add(create("Alumnos-crearAlumno-escritura","dom:escuela:AlumnoRepositorio:create:w", executionContext));
		listapermisos.add(create("Alumnos-buscarporDNI-lectura","dom:escuela:AlumnoRepositorio:ListByDni:r", executionContext));
		listapermisos.add(create("Alumnos-listarAlde un Curso-lectura","dom:escuela:AlumnoRepositorio:queryListarAlumnosDeUnCurso:r", executionContext));
		listapermisos.add(create("Alumnos-RecobrarAlumno-escritura","dom:escuela:AlumnoRepositorio:recoverAlumno:w", executionContext));
		listapermisos.add(create("Alumnos-BorrarAlumno-escritura","dom:escuela:AlumnoRepositorio:removeAlumno:w", executionContext));
		
		listapermisos.add(create("Curso-ListarporPlan-lectura","dom:escuela:CursoRepositorio:listarCursosDeUnPlan:r", executionContext));
		listapermisos.add(create("Curso-Crear-escritura","dom:escuela:CursoRepositorio:crearCurso:w", executionContext));
		listapermisos.add(create("Curso-ListarconAlumnos-lectura","dom:escuela:CursoRepositorio:listarCursoConAlumnos:r", executionContext));
		listapermisos.add(create("Curso-ListarporAño-lectura","dom:escuela:CursoRepositorio:listarCursosDeUnAnio:r", executionContext));
		listapermisos.add(create("Curso-Seleccionar-lectura","dom:escuela:CursoRepositorio:seleccionarUnCurso:r", executionContext));
		listapermisos.add(create("Curso-AsignarPreceptor-escritura","dom:escuela:CursoRepositorio:asignarPreceptorAlCurso:w", executionContext));
		listapermisos.add(create("Curso-AsignarProfesor-escritura","dom:escuela:CursoRepositorio:asignarProfesorAMateriaDelCurso:w", executionContext));
		listapermisos.add(create("Curso-Verhorario-Todos","dom:escuela:CursoRepositorio:verHorarioDelCurso:*", executionContext));
		listapermisos.add(create("Curso-Recuperar-escritura","dom:escuela:CursoRepositorio:recuperarCurso:w", executionContext));
		listapermisos.add(create("Curso-TraerLibroDiario-lectura","dom:escuela:CursoRepositorio:trarlibrodiariorepo:r", executionContext));
		listapermisos.add(create("Curso-Eliminar-escritura","dom:escuela:CursoRepositorio:eliminarCurso:w", executionContext));

	}


	     private Permission create(String permissionDescription,String permissionText, ExecutionContext executionContext) 
	     {
        	return executionContext.add(this, this.permisorpo.create(permissionDescription, permissionText));
    	 }
    	 
    	 private Role create(String roleName,Permission permission, ExecutionContext executionContext) 
	     {
        	return executionContext.add(this, this.rolrepo.create(roleName, permission));
    	 }
    	 
    	 private ShiroUser create(String userName,String password, Role role, ExecutionContext executionContext) 
	     {
        	return executionContext.add(this, this.userrepo.create(userName, password, role));
    	 }
	 
    @javax.inject.Inject
    private PermissionRepository permisorpo;
    @javax.inject.Inject
    private RoleRepository rolrepo;
    @javax.inject.Inject
    private ShiroUserRepository userrepo;
}
