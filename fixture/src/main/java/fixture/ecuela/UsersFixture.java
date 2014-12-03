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
import java.util.Map;
import java.util.TreeSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.applib.security.RoleMemento;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;
import org.datanucleus.flush.ListAddAtOperation;
import org.datanucleus.store.types.backed.SortedSet;

import dom.seguridad.Permission;
import dom.seguridad.PermissionRepository;
import dom.seguridad.Role;
import dom.seguridad.RoleRepository;
import dom.seguridad.ShiroUser;
import dom.seguridad.ShiroUserRepository;

public class UsersFixture extends FixtureScript{

	public UsersFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		
		List<Role>	listaroles=DefautRoles();//Desarrollador,Administrador,Director,Secretario,Preceptor,Profesor,Alumno
		
		BorrarDBUser(executionContext);
			
		
		//Permisos
		
		//User Desarrollador
		if(!listaroles.get(0).getRoleName().equals(container.getUser().getName()))
		{
			List<Permission> listapermisos=new ArrayList<Permission>();
			listapermisos.add(create("EVERYTHING","*", executionContext));//0
			
			List<Role> newlistaroles=llenarlistapermisos(listaroles, listapermisos.get(0), 0);//Rol Desarrollador
			
			create("Desarrollador","fourheads",newlistaroles.get(0),executionContext);//Usuario Desarrollador.
		}
		
		//List<Permission> listapermisos=new ArrayList<Permission>();
		
		List<Permission> AlumnoRepo=(Crearpermisos(dom.escuela.AlumnoRepositorio.class, executionContext));
		List<Permission> CursoRepo=(Crearpermisos(dom.escuela.CursoRepositorio.class, executionContext));
		List<Permission> MateriadelcursoRepo=(Crearpermisos(dom.escuela.MateriaDelCursoRepositorio.class, executionContext));
		List<Permission> Legajo=(Crearpermisos(dom.escuela.LegajoRepositorio.class, executionContext));
		List<Permission> PersonalRepo=(Crearpermisos(dom.escuela.PersonalRepositorio.class, executionContext));
		
		List<Permission> LibroDiarioRepo=(Crearpermisos(dom.libroDiario.LibroDiarioRepositorio.class, executionContext));
		List<Permission> EntradaLiRepo=(Crearpermisos(dom.libroDiario.EntradadeLibroDiarioRepositorio.class, executionContext));
		List<Permission> HojalibroDiarioView=(Crearpermisos(dom.libroDiario.HojaLibroDiarioView.class, executionContext));
		List<Permission> MateriaDelLibroDiario=(Crearpermisos(dom.libroDiario.MateriaDelLibroDiarioRepositorio.class, executionContext));
		
		List<Permission> PlanRepositorio=(Crearpermisos(dom.planEstudio.PlanRepositorio.class, executionContext));
		List<Permission> AnioRepositorio=(Crearpermisos(dom.planEstudio.AnioRepositorio.class, executionContext));
		List<Permission> MateriaRepositorio=(Crearpermisos(dom.planEstudio.MateriaRepositorio.class, executionContext));
		
		
		List<Permission> HorarioCursoView=(Crearpermisos(dom.horario.HorarioCursoView.class, executionContext));
		
		List<Permission> HorarioCursoRepo=(Crearpermisos(dom.horario.HorarioCursoRepositorio.class, executionContext));
		List<Permission> HorarioDiaRepo=(Crearpermisos(dom.horario.HorarioDiaRepositorio.class, executionContext));
		List<Permission> HorarioHoraRepo=(Crearpermisos(dom.horario.HorarioHoraRepositorio.class, executionContext));
		List<Permission> HorairoHoraSemanaView=(Crearpermisos(dom.horario.HorarioHoraSemanaView.class, executionContext));
		List<Permission> HorarioPlanRepo=(Crearpermisos(dom.horario.HorarioPlanRepositorio.class, executionContext));
		
		List<Permission> AumnosMateriasView=(Crearpermisos(dom.calificacion.AlumnoMateriasView.class, executionContext));
		List<Permission> AlumnoCalificacionRepo=(Crearpermisos(dom.calificacion.AlumnoCalificacionRepositorio.class, executionContext));
		List<Permission> CalificacionRepo=(Crearpermisos(dom.calificacion.CalificacionRepositorio.class, executionContext));
		List<Permission> CargaNotaView=(Crearpermisos(dom.calificacion.CargarNotaView.class, executionContext));
		List<Permission> MateriaCalificaionRepo=(Crearpermisos(dom.calificacion.MateriaCalificacionRepositorio.class, executionContext));
		List<Permission> PeriodoRepo=(Crearpermisos(dom.calificacion.PeriodoRepositorio.class, executionContext));
		
		List<Permission> AsistenciaDiaRepo=(Crearpermisos(dom.asistencia.AsistenciaDiaRepositorio.class, executionContext));
		List<Permission> AsistenciaRepo=(Crearpermisos(dom.asistencia.AsistenciaRepositorio.class, executionContext));
		List<Permission> AnalisisAsistenciaView=(Crearpermisos(dom.asistencia.AnalisisAsistenciaView.class, executionContext));
		List<Permission> ContabilizarAsistenciasView=(Crearpermisos(dom.asistencia.ContabilizarAsistenciasView.class, executionContext));
		List<Permission> TomarAsistenaciaView=(Crearpermisos(dom.asistencia.TomarAsistenciaView.class, executionContext));
		
		List<Permission> CuentasUsuarios=(Crearpermisos(dom.seguridad.Cuentas.class, executionContext));
		
		//Roles
		List<Permission> newlistapermisosAll=new ArrayList<Permission>();
		newlistapermisosAll.addAll(CursoRepo);
		newlistapermisosAll.addAll(CuentasUsuarios);
		newlistapermisosAll.addAll(ContabilizarAsistenciasView);
		newlistapermisosAll.addAll(CargaNotaView);
		newlistapermisosAll.addAll(CalificacionRepo);
		newlistapermisosAll.addAll(TomarAsistenaciaView);
		newlistapermisosAll.addAll(AnalisisAsistenciaView);
		newlistapermisosAll.addAll(AsistenciaRepo);
		newlistapermisosAll.addAll(AlumnoCalificacionRepo);
		newlistapermisosAll.addAll(AlumnoRepo);
		newlistapermisosAll.addAll(AnioRepositorio);
		newlistapermisosAll.addAll(AsistenciaDiaRepo);
		newlistapermisosAll.addAll(AumnosMateriasView);
		newlistapermisosAll.addAll(EntradaLiRepo);
		newlistapermisosAll.addAll(HojalibroDiarioView);
		newlistapermisosAll.addAll(HorairoHoraSemanaView);
		newlistapermisosAll.addAll(HorarioCursoRepo);
		newlistapermisosAll.addAll(HorarioCursoView);
		newlistapermisosAll.addAll(HorarioDiaRepo);
		newlistapermisosAll.addAll(HorarioHoraRepo);
		newlistapermisosAll.addAll(HorarioPlanRepo);
		newlistapermisosAll.addAll(Legajo);
		newlistapermisosAll.addAll(LibroDiarioRepo);
		newlistapermisosAll.addAll(MateriaCalificaionRepo);
		newlistapermisosAll.addAll(MateriadelcursoRepo);
		newlistapermisosAll.addAll(MateriaDelLibroDiario);
		newlistapermisosAll.addAll(MateriaRepositorio);
		newlistapermisosAll.addAll(PeriodoRepo);
		newlistapermisosAll.addAll(PersonalRepo);
		newlistapermisosAll.addAll(PlanRepositorio);

		if(!listaroles.get(1).getRoleName().equals(container.getUser().getName()))
		{
			List<Role> newlistaroles=llenarlistapermisos(listaroles, newlistapermisosAll, 1);//Rol Administrador	
			
			//Usuarios
			create("Administrador","admin",newlistaroles.get(1),executionContext);//Usuario Administrador.
		}
		//Roles alumnoRepositorio
		newlistapermisosAll.clear();
		newlistapermisosAll.addAll(AlumnoRepo);
		
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, "recoverAlumno:*"));

		listaroles.get(2).getPermissionsList().addAll(newlistapermisosAll);
		listaroles.get(3).getPermissionsList().addAll(newlistapermisosAll);
		

		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":create:*"));
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":removeAlumno:*"));
		
		listaroles.get(4).getPermissionsList().addAll(newlistapermisosAll);
		listaroles.get(5).getPermissionsList().addAll(newlistapermisosAll);
		
		
		//Roles CursoRepositorio
		newlistapermisosAll.clear();
		newlistapermisosAll.addAll(CursoRepo);
		
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":recuperarCurso:*"));
		
		listaroles.get(3).getPermissionsList().addAll(newlistapermisosAll);
		listaroles.get(3).getPermissionsList().remove(buscarUnPermiso(newlistapermisosAll, "dom.escuela:CursoRepositorio:trarlibrodiariorepo:*"));
		
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, "dom.escuela:CursoRepositorio:asignarPreceptorAlCurso:*"));
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, "dom.escuela:CursoRepositorio:asignarProfesorAMateriaDelCurso:*"));
		
		listaroles.get(2).getPermissionsList().addAll(newlistapermisosAll);

		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":crearCurso:*"));
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":eliminarCurso:*"));
		
		listaroles.get(4).getPermissionsList().addAll(newlistapermisosAll);
		listaroles.get(5).getPermissionsList().addAll(newlistapermisosAll);
		
		listaroles.get(5).getPermissionsList().remove(buscarUnPermiso(newlistapermisosAll, "dom.escuela:CursoRepositorio:listarCursosDeUnAnio:*"));
		listaroles.get(5).getPermissionsList().remove(buscarUnPermiso(newlistapermisosAll, "dom.escuela:CursoRepositorio:listarCursosDeUnPlan:*"));
		
		newlistapermisosAll.clear();
		newlistapermisosAll.add(buscarUnPermiso(CursoRepo, "dom.escuela:CursoRepositorio:verHorarioDelCurso:*"));
		listaroles.get(6).getPermissionsList().add(buscarUnPermiso(CursoRepo, "dom.escuela:CursoRepositorio:verHorarioDelCurso:*"));
		
		
		//Roles MateriaDelCursoRepositorio
		newlistapermisosAll.clear();
		newlistapermisosAll.addAll(MateriadelcursoRepo);
		
		listaroles.get(3).getPermissionsList().addAll(newlistapermisosAll);

		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":asignarProfesorAMateriaDelCurso:*"));
		listaroles.get(2).getPermissionsList().addAll(newlistapermisosAll);

		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":crearMateriaDelCurso:*"));
		for(int x=4;x<listaroles.size();x++)
			listaroles.get(x).getPermissionsList().addAll(newlistapermisosAll);
		
		
		//Roles MateriaDelCursoRepositorio
		newlistapermisosAll.clear();
		newlistapermisosAll.addAll(PersonalRepo);
		
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":recoverPersonal:*"));
		listaroles.get(3).getPermissionsList().addAll(newlistapermisosAll);

		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":removePersonal:*"));
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":listarPersonalSegunFuncion:*"));
		listaroles.get(2).getPermissionsList().addAll(newlistapermisosAll);
		
		
		//Roles LibroDiarioRepositorio
		newlistapermisosAll.clear();
		newlistapermisosAll.addAll(LibroDiarioRepo);
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":ModificarLibroDiario:*"));
		newlistapermisosAll.remove(buscarUnPermiso(newlistapermisosAll, ":crearLibroDiario:*"));
		
		for(int x=4;x<listaroles.size()-1;x++)
			listaroles.get(x).getPermissionsList().addAll(newlistapermisosAll);
		
		
		listaroles.get(2).getPermissionsList().add(buscarUnPermiso(LibroDiarioRepo, ":crearLibroDiario:*"));
		listaroles.get(3).getPermissionsList().add(buscarUnPermiso(LibroDiarioRepo, ":crearLibroDiario:*"));
		
		listaroles.get(2).getPermissionsList().add(buscarUnPermiso(LibroDiarioRepo, ":mostrarhojalibrodiario:*"));
		listaroles.get(3).getPermissionsList().add(buscarUnPermiso(LibroDiarioRepo, ":mostrarhojalibrodiario:*"));
	
		/*
		rolesnames.add("Desarrollador");0
		rolesnames.add("Administrador");1
		rolesnames.add("Director");2
		rolesnames.add("Secretario");3
		rolesnames.add("Preceptor");4
		rolesnames.add("Profesor");5
		rolesnames.add("Alumno");6
		*/
		for(int x=2;x<listaroles.size();x++)
			ArmaRoles(listaroles.get(x), executionContext);
	}
	
		private void BorrarDBUser(ExecutionContext executionContext)
		{
			
			if(isuserlogin())
			{			
				String username= container.getUser().getName();
				
				isisJdoSupport.executeUpdate("DELETE FROM \"ShiroUser_rolesList\" WHERE \"id_OID\" <>"+TraerIDUsuario(username));
				isisJdoSupport.executeUpdate("DELETE FROM \"ShiroUser\" WHERE \"id\" <>"+TraerIDUsuario(username));
				
				String[] roles=TraerIDRoles(TraerIDUsuario(username));
				
				isisJdoSupport.executeUpdate("DELETE FROM \"Role_permissionsList\" WHERE"+PrepararString(roles, "id_OID"));
				isisJdoSupport.executeUpdate("DELETE FROM \"Role\" WHERE"+PrepararString(roles, "id"));
				
				String[] permisos = TraerIDPermisos(roles);
								
				isisJdoSupport.executeUpdate("DELETE FROM \"Permission\" WHERE"+PrepararString(permisos, "id"));
				
			}
			else
				BorrarallDB(executionContext);

			return;
		}
		
		private boolean isuserlogin()
		{
			boolean islogin=false;
			String username= container.getUser().getName();
			if(username!="" || username!=null)
				islogin=true;
			
			return islogin;
		}
		
		private String filtro(String ID, String filtro)
		{
			String g=ID;
			g=g.replace(filtro, "");
			g=g.replace("=", "");
			g=g.replace("[", "");
			g=g.replace("{", "");
			g=g.replace("}", "");
			g=g.replace("]", "");
			
			return g;
		}
		
		private String TraerIDUsuario(String usuario)
		{
			List<Map<String, Object>> id=isisJdoSupport.executeSql("SELECT id FROM \"ShiroUser\" WHERE \"userName\"='"+usuario+"'");
			String g=filtro(id.toString(),"id");
			
			return g;
		}
		
		private String PrepararString(String[] lista, String id)
		{
			String seccion="";
			if(id=="id_OID")
				seccion=" \""+id+"\" <>";
			else
				seccion=" "+id+"<>";
			
			seccion=seccion+lista[0];
			
			if(lista.length>1)
			{
				
				for(int x=1; x<lista.length;x++)
				{
					seccion=seccion+" AND"+seccion.replace(lista[x-1], lista[x]);
				}
			}
			
			return seccion;
		}
		
		private String[] TraerIDRoles(String idusuario)
		{
			List<Map<String, Object>> id=isisJdoSupport.executeSql("SELECT \"id_EID\" FROM \"ShiroUser_rolesList\" WHERE \"id_OID\"="+idusuario+"");
			String[] g=id.toString().split(",");
			for(int x=0; x<g.length;x++)
				g[x]=filtro(g[x],"id_EID");
			return g;
		}
		
		private String[] TraerIDPermisos(String[] roles)
		{
			String cadena="";
			for(int y=0;y<roles.length;y++)
			{
				List<Map<String, Object>> id=isisJdoSupport.executeSql("SELECT \"id_EID\" FROM \"Role_permissionsList\" WHERE \"id_OID\"="+roles[y]+"");
				cadena=cadena+filtro(id.toString(),"id_EID")+",";
			}
			cadena=cadena.substring(0, cadena.length()-1);
			String[] permisos=cadena.split(",");
			for(int x=0; x<permisos.length;x++)
				permisos[x]=filtro(permisos[x],"id_EID");
			
			int index=0;
			int count=0;
			
			for(int x=0; x<permisos.length-1;x++)
			{
				for(int y=1; y<permisos.length;y++)
				{
					if(permisos[x].contains(permisos[y]))
					{
						permisos[y]="-";
						count++;
					}
				}
			}
			
			String[] finalpermisos = new String[permisos.length-count];
			
			for(int x=0; x<permisos.length;x++)
			{
				if(permisos[x]!="-")
				{
					finalpermisos[index]=permisos[x];
					index++;
				}	
			}
			
			return finalpermisos;
		}
		
		private void BorrarallDB(ExecutionContext executionContext)
		{
			execute(new GenericTearDownFixture("ShiroUser_rolesList"), executionContext);
			execute(new GenericTearDownFixture("ShiroUser"), executionContext);
			execute(new GenericTearDownFixture("Role_permissionsList"), executionContext);
			execute(new GenericTearDownFixture("Role"), executionContext);
			execute(new GenericTearDownFixture("Permission"), executionContext);
		}
	
		private List<Role> llenarlistapermisos(List<Role> listaroles, List<Permission> listapermisos, int index)
		{
			listaroles.get(index).getPermissionsList().addAll(listapermisos);
			return listaroles;
		}
		
		
		private List<Role> llenarlistapermisos(List<Role> listaroles, Permission permiso, int index)
		{
			listaroles.get(index).getPermissionsList().add(permiso);
			return listaroles;
		}
		
 		
		private void ArmaRoles(Role rol, ExecutionContext executionContext)
		{
					Role nrol=createRole(rol.getRoleName(), rol.getPermissionsList().first(), executionContext);
					if(rol.getPermissionsList().size()>1)
					{
						nrol.setPermissionsList(rol.getPermissionsList());
					}
		}
		
		
		private List<Role> DefautRoles()
	{
		List<Role>	listaroles=new ArrayList<Role>();
		
		List<String> rolesnames=new ArrayList<String>();
		
		rolesnames.add("Desarrollador");
		rolesnames.add("Administrador");
		rolesnames.add("Director");
		rolesnames.add("Secretario");
		rolesnames.add("Preceptor");
		rolesnames.add("Profesor");
		rolesnames.add("Alumno");
		
		for(int x=0;x<rolesnames.size();x++)
		{
			Role newrole=new Role();
			newrole.setRoleName(rolesnames.get(x));
			
			listaroles.add(newrole);
		}
		
		return listaroles;
	}
	
		
		private Permission buscarUnPermiso(List<Permission> lista, String textpermiso)
		{
			List<Permission> newlista=new ArrayList<Permission>();
			
			for(Permission per:lista)
			{
				if(per.getPermissionText().contains(textpermiso))
				{
					newlista.add(per);
				}
			}
			
			return newlista.get(0);
		}
		
		
		private List<Permission> buscarListaPermiso(List<Permission> lista, String descripermiso)
		{
			List<Permission> newlista=new ArrayList<Permission>();
			
			for(Permission per:lista)
			{
				if(per.getPermissionDescription().equals(descripermiso))
				{
					newlista.add(per);
				}
			}
			
			return newlista;
		}
	
		
		private List<Permission> Crearpermisos(Class clase, ExecutionContext executionContext)
		{
			String clasenombre=clase.getName().toString();
			
			String [] secciones=Dividir(clasenombre);
			
			List<Permission> listapermisos=new ArrayList<Permission>();
			
			List<String> listametodos= crearlistametodos(clase);
		
			if(listametodos!=null && secciones.length>1)
			{
					String title=secciones[2]+"-";
					for(int x=0; x<listametodos.size();x++)
					{
						String cuerpo=secciones[0]+"."+secciones[1]+":"+secciones[2]+":"+listametodos.get(x)+":*";
						String ntitle=title+listametodos.get(x);
						listapermisos.add(create(ntitle,cuerpo, executionContext));
					}			
				}
			else
			{
				listapermisos.add(create(clasenombre,secciones[0], executionContext));
			}
			
			return listapermisos;
		}

		
		private List<String> crearlistametodos(Class clase)
		{
			List<String> listametodos= new ArrayList<String>();
			String metodo="";
			for(int x=0;x<clase.getMethods().length;x++)
			{
				{
					metodo=clase.getMethods()[x].getName();
					listametodos.add(metodo);
				}
			}
			
			return validarmetodo(listametodos);
		}
		
		private List<String> validarmetodo(List<String> metodo)//ver
		{
			List<String> listametodos=new ArrayList<String>();
			listametodos.addAll(metodo);
			
			List<String> metodosaquitar=new ArrayList<String>();
			
			metodosaquitar.add("getId");
			metodosaquitar.add("hashCode");
			metodosaquitar.add("iconName");
			metodosaquitar.add("wait");
			metodosaquitar.add("default");
			metodosaquitar.add("choices");
			metodosaquitar.add("validate");
			metodosaquitar.add("equals");
			metodosaquitar.add("toString");
			metodosaquitar.add("getClass");
			metodosaquitar.add("notify");
			metodosaquitar.add("notifyAll");
			metodosaquitar.add("set");
			metodosaquitar.add("get");
			//metodosaquitar.add("viewModel");
			metodosaquitar.add("disabled");
			
			for(String quitar:metodosaquitar)
			{
				for(String palabra:listametodos)
				{
					if(palabra.contains(quitar))
					{
						metodo.remove(palabra);
					}
				}
			}
						
			return metodo;
		}
		
		
		private String[] Dividir(String texto)

		{
			texto=texto.replace('.', ',');
			String[] partes=texto.split(",");
			return partes;
		}
		
	    
		private Permission create(String permissionDescription,String permissionText, ExecutionContext executionContext) 
	     {
        	return executionContext.add(this, this.permisorpo.create(permissionDescription, permissionText));
    	 }
    	 
    	
		private Role createRole(String roleName,Permission sortedSet, ExecutionContext executionContext) 
	     {
        	return executionContext.add(this, this.rolrepo.create(roleName, sortedSet));
    	 }
    	 
    	 
    	private ShiroUser create(String userName,String password, Role role, ExecutionContext executionContext) 
	     {
        	return executionContext.add(this, this.userrepo.create(userName, password, role));
    	 }
	
    	
    @javax.inject.Inject
    DomainObjectContainer container;
    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;
    @javax.inject.Inject
    private PermissionRepository permisorpo;
    @javax.inject.Inject
    private RoleRepository rolrepo;
    @javax.inject.Inject
    private ShiroUserRepository userrepo;
}
