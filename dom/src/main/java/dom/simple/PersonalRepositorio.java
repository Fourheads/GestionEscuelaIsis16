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

package dom.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Mask;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.value.DateTime;
import org.joda.time.LocalDate;

import dom.planEstudio.Plan;
import dom.planEstudio.PlanRepositorio;
import dom.simple.Funcion.E_funciones;
import dom.simple.Localidad.E_localidades;
import dom.simple.Persona.E_nacionalidad;
import dom.simple.Persona.E_sexo;

@DomainService(menuOrder = "40", repositoryFor = Personal.class)
@Named("Personal Educativo")
public class PersonalRepositorio {

	public String getId() {
        return "personal";
    }

    public String iconName() {
        return "SimpleObject";
    }
    
        
    //Crear personal    
    @MemberOrder(sequence = "1")
    @Named ("Ingresar Nuevo")
    public Personal createPersonal(final @RegEx(validation = "[A-Za-z]+") @Named("Nombre") String nombre,
            final @RegEx(validation = "[A-Za-z]+") @Named("Apellido") String apellido,
            final @Named("Sexo") E_sexo sexo,
            final @RegEx(validation = "/d{6,10}") @Named("DNI") int dni, 
            final @Named("Fecha Nacimiento") LocalDate nacimiento,
            final @Named("Nacionalidad") E_nacionalidad nacionalidad,
            final @Named("Domicilio. Localidad") E_localidades localidad,
            final @Named("Domicilio. Calle") String calle,
            final @RegEx(validation = "/d{5}") @Named("Domicilio. Numero") int numero,
            final @RegEx(validation = "/d+")@org.apache.isis.applib.annotation.Optional @Named("Domicilio. Piso") String piso,
            final @org.apache.isis.applib.annotation.Optional @Named("Domicilio. Departamento") String departamento,
            @RegEx(validation = "/d+") @SuppressWarnings("deprecation") final @Mask("(NNNN)NNN-NNNNNNN") @org.apache.isis.applib.annotation.Optional @Named("TelÃ©fono") String telefono){
    	
    	final Personal newPersonal = container.newTransientInstance(Personal.class);
    	final Direccion newDireccion = new Direccion();
        final Localidad newLocalidad = new Localidad();
    	
        
        newLocalidad.setNombre(localidad);
        
        newDireccion.setCalle(calle.toUpperCase());
        newDireccion.setDepartamento(departamento);
        newDireccion.setLocalidad(newLocalidad);
        newDireccion.setNumero(numero);
        newDireccion.setPiso(piso);
        
        newPersonal.setApellido(apellido.toUpperCase());
        newPersonal.setDireccion(newDireccion);
        newPersonal.setDni(dni);
        newPersonal.setFechaNacimiento(nacimiento);
        newPersonal.setNacionalidad(nacionalidad);
        newPersonal.setNombre(nombre.toUpperCase());
        newPersonal.setSexo(sexo);
        newPersonal.setTelefono(telefono);
        newPersonal.setHabilitado('S');
        
        container.persistIfNotAlready(newPersonal);        
    	return newPersonal;
    }
    
    //Validar createPersonal
    public String validateCreatePersonal(String nombre, String apellido, E_sexo sexo, int dni, LocalDate nacimiento, E_nacionalidad nacionalidad,
   		 E_localidades localidad, String calle, int numero, String piso, String departamento, String telefono)
	{	
    	List<Personal> dniPersonal = container.allMatches((new QueryDefault<Personal>(Personal.class, "findByDni", "dni", dni)));
    	if(!dniPersonal.isEmpty()){					
			return "El número de dni ya existe";			
		}
    	if (nacimiento.isAfter(LocalDate.now())){		
			return "La fecha de nacimiento debe ser menor al día actual";
		}
		return null;
		
	}
    
    // Listados  ////////////////////
        
    //Todo
    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "5")
    @Named ("Todos")
    public List<Personal> listAll(){    	
    	return filtroPe(container.allInstances(Personal.class),'S');
    }
    
    
//    // Preceptores    
//    @MemberOrder(sequence = "3.1")
//    @ActionSemantics(Of.SAFE)
//    @Named ("Preceptores")
//    public List<Personal> listPreceptor(){
//    	return container.allMatches(
//    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.PRECEPTOR.toString()));
//    }
//    
//    // Profesores    
//    @MemberOrder(sequence = "3.2")
//    @Named ("Profesores")
//    public List<Personal> listProfesor(){
//    	return container.allMatches(
//    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.PROFESOR.toString()));
//    }
//    
//    // Directores    
//    @MemberOrder(sequence = "3.3")
//    @Named ("Directores")
//    public List<Personal> listDirector(){
//    	return container.allMatches(
//    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.DIRECTOR.toString()));
//    }
//    
//    // Secretarios    
//    @MemberOrder(sequence = "3.4")
//    @Named ("Secretarios")
//    public List<Personal> listSecretario(){
//    	return container.allMatches(
//    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", E_funciones.SECRETARIO.toString()));
//    }    
//    
    @MemberOrder(sequence = "4")
    @Named ("Listar Por funcion")
    public List<Personal> listarPersonalSegunFuncion(@Named("Funcion")E_funciones funcion){
    	return filtroPe(container.allMatches(
    			new QueryDefault<Personal>(Personal.class, "findByFuncion", "nombre", funcion.toString())),'S');
    }    
    
    public E_funciones default0ListarPersonalSegunFuncion(){
    	List<E_funciones> funciones = Arrays.asList(E_funciones.values());
    	    	
    	return funciones.get(0);
    }
    
    
	private List<Personal> filtroPe(List<Personal> personal, char A)
	{
		List<Personal> filtroPe=new ArrayList<Personal>();
		
		for(Personal Pe:personal)
		{
			if(Pe.getHabilitado()==A)
				filtroPe.add(Pe);
		}
		
		return filtroPe;
	}
    
    //  Fin Listados  ////////////////////
    
    
    @Hidden(where = Where.OBJECT_FORMS)    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "2")
    @Named("Recuperar")    
    public String recoverPersonal(@Named("Personal") Personal delPersonal) {
    		
    		delPersonal.setHabilitado('S');
    	
    		String remPersonal = delPersonal.title();			
			container.removeIfNotAlready(delPersonal);			
			return  remPersonal + " fue recuperado exitoramente";		
	}
    
    public List<Personal> choices0RecoverPersonal()
    {
    	return filtroPe(container.allInstances(Personal.class),'N');
    }
    
    // Eliminar Personal    
    @Hidden(where = Where.OBJECT_FORMS)    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "3")
    @Named("Eliminar")    
    public String removePersonal(@Named("Personal") Personal delPersonal) {
    		
    		delPersonal.setHabilitado('N');
    	
    		for(Plan planes:planrepositorio.listarPlanes())
    		{
    			for(Curso curso:cursorepositorio.listarCursosDeUnPlan(planes))
    			{
    				for(MateriaDelCurso matecurso:curso.getMateriaDelCursoList())
    				{
    					if(matecurso.getProfesor()==delPersonal)
    						matecurso.setProfesor(null);
    				}
    				
    				if(curso.getPreceptor()==delPersonal)
    					curso.setPreceptor(null);
    					
    			}
    		}
    		
    		String remPersonal = delPersonal.title();			
					
			return  remPersonal + " fue eliminado";
			
	}
        
    
    @javax.inject.Inject 
    DomainObjectContainer container;
    @javax.inject.Inject 
    PlanRepositorio planrepositorio;
    @javax.inject.Inject 
    CursoRepositorio cursorepositorio;
}
