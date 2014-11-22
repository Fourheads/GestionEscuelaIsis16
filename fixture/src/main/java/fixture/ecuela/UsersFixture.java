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
		
		listapermisos.add(create("EVERYTHING","*", executionContext));//0
		
		//1
		listapermisos.add(create("Alumnos-listartodos","dom.escuela:AlumnoRepositorio:listAll:*", executionContext));
		listapermisos.add(create("Alumnos-crearAlumno","dom.escuela:AlumnoRepositorio:create:*", executionContext));
		listapermisos.add(create("Alumnos-buscarporDNI","dom.escuela:AlumnoRepositorio:ListByDni:*", executionContext));
		listapermisos.add(create("Alumnos-listarAlde un Curso","dom.escuela:AlumnoRepositorio:queryListarAlumnosDeUnCurso:*", executionContext));
		listapermisos.add(create("Alumnos-RecobrarAlumno","dom.escuela:AlumnoRepositorio:recoverAlumno:*", executionContext));
		listapermisos.add(create("Alumnos-BorrarAlumno","dom.escuela:AlumnoRepositorio:removeAlumno:*", executionContext));
		//6
		
		//7
		listapermisos.add(create("Curso-ListarporPlan","dom.escuela:CursoRepositorio:listarCursosDeUnPlan:*", executionContext));
		listapermisos.add(create("Curso-Crear","dom.escuela:CursoRepositorio:crearCurso:*", executionContext));
		listapermisos.add(create("Curso-ListarconAlumnos","dom.escuela:CursoRepositorio:listarCursoConAlumnos:*", executionContext));
		listapermisos.add(create("Curso-ListarporAño","dom.escuela:CursoRepositorio:listarCursosDeUnAnio:*", executionContext));
		listapermisos.add(create("Curso-Seleccionar","dom.escuela:CursoRepositorio:seleccionarUnCurso:*", executionContext));
		listapermisos.add(create("Curso-AsignarPreceptor","dom.escuela:CursoRepositorio:asignarPreceptorAlCurso:*", executionContext));
		listapermisos.add(create("Curso-AsignarProfesor","dom.escuela:CursoRepositorio:asignarProfesorAMateriaDelCurso:*", executionContext));
		listapermisos.add(create("Curso-Verhorario","dom.escuela:CursoRepositorio:verHorarioDelCurso:*", executionContext));
		listapermisos.add(create("Curso-Recuperar","dom.escuela:CursoRepositorio:recuperarCurso:*", executionContext));
		listapermisos.add(create("Curso-TraerLibroDiario","dom.escuela:CursoRepositorio:trarlibrodiariorepo:*", executionContext));
		listapermisos.add(create("Curso-Eliminar","dom.escuela:CursoRepositorio:eliminarCurso:*", executionContext));
		//18
		
		//19
		listapermisos.add(create("Curso-Eliminar","dom.escuela:CursoRepositorio:eliminarCurso:*", executionContext));
		
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
