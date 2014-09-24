package dom.asistencia;

import java.io.ObjectInputStream.GetField;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberGroups;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.PublishedObject;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.annotation.TypicalLength;

@Named("Contabilizar Asistencias View")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 6, 0, 6, 12 }, right = "Intervalo", left = "Curso")
public class ContabilizarAsistenciasView extends AbstractViewModel {

	String memento;
	String title;

	public String title() {
		return title;
	}

	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;

		String[] parametros = memento.split(",");

		title = "Estadísticas de Asistencia";
		setAsistencia(parametros[0]);
		setAnio(Integer.parseInt(parametros[1]));
		setDivision(parametros[2]);
		try {
			setDesde(TraductorService.stringToDate(parametros[3]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			setHasta(TraductorService.stringToDate(parametros[4]));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		setIndice(Integer.parseInt(parametros[5]));

		setAnalisisAsistenciaView(AnalisisAsistenciaService
				.analizarIntervaloAsistenciaCurso(getAsistencia(), getAnio(),
						getDivision(), getDesde(), getHasta()));

	}

	// {{ Desde (property)
	private Date desde;

	@MemberOrder(sequence = "1", name = "Intervalo")
	@Column(allowsNull = "true")
	public Date getDesde() {
		return desde;
	}

	public void setDesde(final Date desde) {
		this.desde = desde;
	}

	// }}

	// {{ Hasta (property)
	private Date hasta;

	@MemberOrder(sequence = "2", name = "Intervalo")
	@Column(allowsNull = "true")
	public Date getHasta() {
		return hasta;
	}

	public void setHasta(final Date hasta) {
		this.hasta = hasta;
	}

	// }}

	// {{ Anio (property)
	private int anio;

	@MemberOrder(sequence = "2", name = "Curso")
	@Column(allowsNull = "true")
	@TypicalLength(value = 5)
	@Named("Año")
	public int getAnio() {
		return anio;
	}

	public void setAnio(final int anio) {
		this.anio = anio;
	}

	// }}

	// {{ Division (property)
	private String division;

	@MemberOrder(sequence = "3", name = "Curso")
	@Column(allowsNull = "true")
	@TypicalLength(value = 5)
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}

	// }}

	// {{ Indice (property)
	private int indice;

	@Hidden
	@Column(allowsNull = "true")
	public int getIndice() {
		return indice;
	}

	public void setIndice(final int indice) {
		this.indice = indice;
	}

	// }}

	// {{ Asistencia (property)
	private String asistencia;

	@MemberOrder(sequence = "1", name = "Curso")
	@Column(allowsNull = "true")
	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(final String asistencia) {
		this.asistencia = asistencia;
	}

	// }}

	// {{ AnalisisAsistenciaView (Collection Property)
	// //////////////////////////////////////////

	@Join
	@Element(dependent = "true")
	private List<AnalisisAsistenciaView> analisisAsistenciaList = new ArrayList<AnalisisAsistenciaView>();

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	@Named("Analisis de Asistencia por Alumno")
	public List<AnalisisAsistenciaView> getAnalisisAsistenciaView() {
		return analisisAsistenciaList;
	}

	public void setAnalisisAsistenciaView(
			final List<AnalisisAsistenciaView> analisisAsistenciaList) {
		this.analisisAsistenciaList = analisisAsistenciaList;
	}

	// }} (end region)
	// //////////////////////////////////////

}
