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
package dom.asistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

@Named("Tomar Asistencia View")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class TomarAsistenciaView extends AbstractViewModel {

	@Hidden
	public String getId() {
		return viewModelMemento();
	}

	private String title;

	// region > identification in the UI
	public String title() {
		return title;
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	// region > ViewModel contract
	private String memento;

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;

		Memento memento = mementoService.parse(mementoString);
		title = memento.get("titulo", String.class);
		setAsistencia(memento.get("asistencia", String.class));
		setFecha(memento.get("fecha", LocalDate.class));
		setAnio(memento.get("anio", String.class));
		setDivision(memento.get("division", String.class));
		setIndiceAlumno(memento.get("alumnoActivo", Integer.class));

		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(getFecha());

		try {
			inicializarListaAlumnos(asistencia, anio, division, fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		inicializarAlumnoActivo();

	}

	@Programmatic
	private void inicializarListaAlumnos(String asistencia, String anio,
			String division, LocalDate fecha) throws ParseException {
		int anioInt = Integer.parseInt(anio);

		setAsistenciAlumnos(AsistenciaAlumnoRepositorio
				.queryAsistenciaAlumnoPorCursoPorDia(fecha, anioInt, division,
						asistencia));
	}

	@Programmatic
	private void inicializarAlumnoActivo() {
		
		if (!getAsistenciAlumnos().isEmpty()){
			setAlumnoActivo(getAsistenciAlumnos().get(getIndiceAlumno()));
		}
		
	}

	// {{ Asistencia (property)
	private String asistencia;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(final String asistencia) {
		this.asistencia = asistencia;
	}

	// }}

	// {{ IndiceAlumno (property)
	private int indiceAlumno;

	@Hidden
	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public int getIndiceAlumno() {
		return indiceAlumno;
	}

	public void setIndiceAlumno(final int indiceAlumno) {
		this.indiceAlumno = indiceAlumno;
	}

	// }}

	// {{ Fecha (property)
	private LocalDate fecha;

	@MemberOrder(sequence = "1.3")
	@Column(allowsNull = "true")
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(final LocalDate fecha) {
		this.fecha = fecha;
	}

	// }}

	// {{ Anio (property)
	private String anio;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "true")
	@Named("Año")
	public String getAnio() {
		return anio;
	}

	public void setAnio(final String anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "3")
	@Column(allowsNull = "true")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	// {{ AlumnoActivo (property)
	private AsistenciaAlumno alumnoActivo;

	@Disabled
	@MemberOrder(sequence = "4")
	@Column(allowsNull = "false")
	public AsistenciaAlumno getAlumnoActivo() {
		return alumnoActivo;

	}

	public void setAlumnoActivo(final AsistenciaAlumno alumnoActivo) {
		this.alumnoActivo = alumnoActivo;
	}

	// }}

	// {{ AsistenciAlumnos (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "false")
	private List<AsistenciaAlumno> asistenciaAlumnoList = new ArrayList<AsistenciaAlumno>();

	@Disabled(where = Where.EVERYWHERE)
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "2")
	public List<AsistenciaAlumno> getAsistenciAlumnos() {
		return asistenciaAlumnoList;
	}

	public void setAsistenciAlumnos(
			final List<AsistenciaAlumno> asistenciaAlumnoList) {
		this.asistenciaAlumnoList = asistenciaAlumnoList;
	}

	// }} (end region)
	// //////////////////////////////////////

	// {{ pasarAlSiguienteAlumno (action)
	@Named("SiguienteAlumno")
	@MemberOrder(sequence = "4", name = "alumnoActivo")
	@PublishedAction
	public TomarAsistenciaView pasarAlSiguienteAlumno() {

		return generarNuevoViewModel(alumnoIndiceSiguiente());
	}

	// }}

	// {{ pasarAlAlumnoAnterior (action)
		@Named("Alumno Anterior")
		@MemberOrder(sequence = "5", name = "alumnoActivo")
		@PublishedAction
		public TomarAsistenciaView pasarAlAlumnoAnterior() {
						
			return generarNuevoViewModel(alumnoIndiceAnterior());
		}

		// }}
	
	// {{ MarcarAlumnoPresente (action)
	@Named("Presente")
	@MemberOrder(sequence = "1", name = "alumnoActivo")
	@PublishedAction
	public TomarAsistenciaView marcarAlumnoPresente() {

		getAlumnoActivo().setEstaPresente(true);
		getAlumnoActivo().setLlegoTarde(false);
		
		return generarNuevoViewModel(alumnoIndiceSiguiente());
	}

	// }}

	// {{ MarcarAlumnoTarde (action)
	@Named("Tarde")
	@MemberOrder(sequence = "2", name = "alumnoActivo")
	@PublishedAction
	public TomarAsistenciaView marcarAlumnoTarde() {

		getAlumnoActivo().setEstaPresente(true);
		getAlumnoActivo().setLlegoTarde(true);
		
		return generarNuevoViewModel(alumnoIndiceSiguiente());
	}

	// }}

	// {{ MarcarAlumnoAusente (action)
	@Named("Ausente")
	@MemberOrder(sequence = "3", name = "alumnoActivo")
	@PublishedAction
	public TomarAsistenciaView marcarAlumnoAusente() {

		getAlumnoActivo().setEstaPresente(false);
		getAlumnoActivo().setLlegoTarde(false);
		
		
		return generarNuevoViewModel(alumnoIndiceSiguiente());
	}

	@Programmatic
	private TomarAsistenciaView generarNuevoViewModel(int nuevoIndice) {
		
		
		
		Memento memento = mementoService.create();

		memento.set("titulo", "Tomar asistencia");
		memento.set("asistencia", getAsistencia());
		memento.set("fecha", fecha);
		memento.set("anio", getAnio());
		memento.set("division", getDivision());
		memento.set("alumnoActivo", nuevoIndice);

		return container.newViewModelInstance(TomarAsistenciaView.class,
				memento.asString());
	}

	@Programmatic
	public int alumnoIndiceSiguiente() {
		int nuevoIndice = getIndiceAlumno() + 1;

		if (nuevoIndice == getAsistenciAlumnos().size()) {
			nuevoIndice = 0;
		}
		return nuevoIndice;
	}
	
	public int alumnoIndiceAnterior() {
		int nuevoIndice = getIndiceAlumno() - 1;

		if (nuevoIndice == -1) {
			nuevoIndice = getAsistenciAlumnos().size() -1;
		}
		return nuevoIndice;
	}
	

	// }}

	// region > injected services
	@javax.inject.Inject
	private DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;

	// endregion

}
