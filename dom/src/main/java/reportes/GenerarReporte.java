package reportes;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.activation.MimeType;
import javax.inject.Named;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.value.Blob;


@Named("Reportes")
@DomainService
public class GenerarReporte {
	
	public static void generarReporte(String jrxml, List<Object> parametros)  throws JRException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//Levanta el jrxml
		File file = new File(jrxml);
		
		//Almacena el array de datos
		JRBeanArrayDataSource jArray= new JRBeanArrayDataSource(parametros.toArray());
		
		InputStream input = null;
		try{
			input = new FileInputStream(file);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//Levanta el modelo del reporte
		JasperDesign jd = JRXmlLoader.load(input);
		
		//Compila el reporte
		JasperReport reporte = JasperCompileManager.compileReport(jd);
		
		//Lo llena con los datos del datasource
		JasperPrint print = JasperFillManager.fillReport(reporte, map, jArray);
		
		//Lo muestra con el jasperviewer
		//JasperViewer viewer = new JasperViewer(print, false);		
		//viewer.setVisible(true);
		JasperViewer.viewReport(print, false);
					
		//Guarda el reporte para poder levantarlo con un Blob
		//JasperExportManager.exportReportToPdfFile(print, "reportemp/NUEVO1.pdf");
					
		//Muestra el reporte en otra ventana
		//JasperExportManager.exportReportToHtmlFile(print, "reportemp/nuevo.html");
	
		
	}	
	
	
	@javax.inject.Inject	
	public ReportContext reportContext;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
}
