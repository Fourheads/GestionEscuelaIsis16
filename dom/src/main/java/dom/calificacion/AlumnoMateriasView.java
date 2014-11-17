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


package dom.calificacion;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import net.sf.jasperreports.engine.JRException;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import reportes.GenerarReporte;
import reportes.MateriasPorAlumno;

@Named("Listado de materias")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 4, 0, 0, 8 })
public class AlumnoMateriasView extends AbstractViewModel{
	
	private String title;
	private String memento;
	
	// {{ Alumno (property)
	private String alumno;

	@MemberOrder(sequence = "1")
	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(final String alumno) {
		this.alumno = alumno;
	}
	// }}

	// {{ Dni (property)
	private int dni;

	@MemberOrder(sequence = "1.1")
	public int getDni() {
		return dni;
	}

	public void setDni(final int dni) {
		this.dni = dni;
	}
	// }}

	// {{ Ciclo (property)
	private int ciclo;

	@MemberOrder(sequence = "1.5")
	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(final int ciclo) {
		this.ciclo = ciclo;
	}
	// }}

	// {{ Periodo (property)
	private String periodo;

	@MemberOrder(sequence = "1.6")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(final String periodo) {
		this.periodo = periodo;
	}
	// }}

	// {{ Curso (property)
	private int curso;

	@MemberOrder(sequence = "1.2")
	public int getCurso() {
		return curso;
	}

	public void setCurso(final int curso) {
		this.curso = curso;
	}
	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "1.3")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}
	// }}

	// {{ Turno (property)
	private String turno;

	@MemberOrder(sequence = "1.4")
	public String getTurno() {
		return turno;
	}

	public void setTurno(final String turno) {
		this.turno = turno;
	}
	// }}
	
	// {{ AlumnoActivo (property)
	private MateriaCalificacion alumnoActivo;
	
	@Hidden
	@Disabled
	@MemberOrder(sequence = "1.6")
	@Column(allowsNull = "false")
	public MateriaCalificacion getAlumnoActivo() {
		return alumnoActivo;
	}

	public void setAlumnoActivo(final MateriaCalificacion alumnoAtivo) {
		this.alumnoActivo = alumnoAtivo;
	}
	// }}

	// {{ MateriasCalificacion (property)
	//@Join
	//@Element(dependent = "false")
	private List<MateriaCalificacion> materiasCalificacion = new ArrayList<MateriaCalificacion>();

	@Disabled(where = Where.EVERYWHERE)
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1.7")
	public List<MateriaCalificacion> getMateriasCalificacion() {
		return materiasCalificacion;
	}

	public void setMateriasCalificacion(final List<MateriaCalificacion> materiasCalificacion) {
		this.materiasCalificacion = materiasCalificacion;
	}

	@Hidden
	public String getId() {
		return viewModelMemento();
	}
	
	public String title() {
		return title;
	}

	public String iconName() {
		return "SimpleObject";
	}	

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		
		Memento newMemento = mementoService.parse(memento);
		//titulo, alumno, ciclo, curso, division, dni, periodo, turno
		this.title = newMemento.get("titulo", String.class);
		setAlumno(newMemento.get("alumno", String.class));
		setCiclo(newMemento.get("ciclo", Integer.class));
		setCurso(newMemento.get("curso", Integer.class));
		setDivision(newMemento.get("division", String.class));
		setDni(newMemento.get("dni", Integer.class));
		setPeriodo(newMemento.get("periodo", String.class));
		setTurno(newMemento.get("turno", String.class));
		
		try{
			inicializarListMaterias();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Programmatic
	public void inicializarListMaterias(){
		setMateriasCalificacion(aluCalRepositorio.listPorAlumno(getDni()).getListMateriaCalificacion());
	}
	
	

	@Override
	public String viewModelMemento() {
		return memento;
	}
	
	@Named("Imprimir Reporte")	
	public String imprimirReporte() throws JRException{
		List<Object> listReport = new ArrayList<Object>();
		
		
		//AlumnoCalificacion alumno = aluRepositorio.listPorAlumno(inAlumno.getDni());
		
		for(MateriaCalificacion m: getMateriasCalificacion()){
			MateriasPorAlumno matAlumno = new MateriasPorAlumno();
			
			if(m.getMateriaDelCurso().getProfesor() == null){
				matAlumno.setProfesor("(sin informar)");
			}else{
				matAlumno.setProfesor(m.getMateriaDelCurso().getProfesor().title());
			}
			matAlumno.setAlumno(getAlumno());
			matAlumno.setCiclo(String.valueOf(getCiclo()));
			matAlumno.setCurso(String.valueOf(getCurso()) + "'" + getDivision() + "'" + "-" + m.getAlumno().getCurso().getAnio().getPlan().getDescripcion());
			matAlumno.setDni(String.valueOf(String.valueOf(getDni())));
			matAlumno.setPeriodo(getPeriodo());
			matAlumno.setTurno(getTurno());
			matAlumno.setMateria(m.getMateriaDelCurso().getMateria().getNombre());
			
			if(m.getNota() == 0){
				matAlumno.setNota("-");
				
			}else{
				matAlumno.setNota(String.valueOf(m.getNota()));
			}
			
			if(m.getObservacion() == null){
				matAlumno.setObservacion("-");
			}else{
				matAlumno.setObservacion(m.getObservacion());
			}
			
			listReport.add(matAlumno);
		}
		
		GenerarReporte.generarReporte("materiasAlumno.jrxml", listReport);
		
		return "Reporte generado.";
		
	}
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluRepositorio;
	
	@javax.inject.Inject
	GenerarReporte reporte;

	
	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluCalRepositorio;
}
