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


package reportes;

import org.apache.isis.applib.annotation.MemberOrder;

public class AsistenciaPorCurso {
	
	// {{ Titulo (property)
	private String titulo;

	@MemberOrder(sequence = "1")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}
	// }}

	// {{ Curso (property)
	private String curso;

	@MemberOrder(sequence = "1")
	public String getCurso() {
		return curso;
	}

	public void setCurso(final String curso) {
		this.curso = curso;
	}
	// }}

	// {{ Registros (property)
	private String registros;

	@MemberOrder(sequence = "1")
	public String getRegistros() {
		return registros;
	}

	public void setRegistros(final String registros) {
		this.registros = registros;
	}
	// }}

	// {{ Presente (property)
	private String presente;

	@MemberOrder(sequence = "1")
	public String getPresente() {
		return presente;
	}

	public void setPresente(final String presente) {
		this.presente = presente;
	}
	// }}

	// {{ Ausente (property)
	private String ausente;

	@MemberOrder(sequence = "1")
	public String getAusente() {
		return ausente;
	}

	public void setAusente(final String ausente) {
		this.ausente = ausente;
	}
	// }}

	// {{ Tarde (property)
	private String tarde;

	@MemberOrder(sequence = "1")
	public String getTarde() {
		return tarde;
	}

	public void setTarde(final String tarde) {
		this.tarde = tarde;
	}
	// }}

	// {{ Porctarde (property)
	private String porctarde;

	@MemberOrder(sequence = "1")
	public String getPorctarde() {
		return porctarde;
	}

	public void setPorctarde(final String porctarde) {
		this.porctarde = porctarde;
	}
	// }}

	// {{ Porcausente (property)
	private String porcausente;

	@MemberOrder(sequence = "1")
	public String getPorcausente() {
		return porcausente;
	}

	public void setPorcausente(final String porcausente) {
		this.porcausente = porcausente;
	}
	// }}

	// {{ Totalfaltas (property)
	private String totalfaltas;

	@MemberOrder(sequence = "1")
	public String getTotalfaltas() {
		return totalfaltas;
	}

	public void setTotalfaltas(final String totalfaltas) {
		this.totalfaltas = totalfaltas;
	}
	// }}

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


	
}
