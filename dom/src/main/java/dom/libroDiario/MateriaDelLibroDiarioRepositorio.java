package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.annotation.DomainService;

import dom.simple.Curso;
import dom.simple.MateriaDelCurso;

@DomainService
public class MateriaDelLibroDiarioRepositorio {

	public List<MateriaDelLibroDiario> crearListaMateriaDelLibroDiario(Curso curso) {
		
		List<MateriaDelCurso> materiaDelCursoList = curso.getMateriaDelCursoList();
		List<MateriaDelLibroDiario> materiaDelLibroDiarioList = new ArrayList<MateriaDelLibroDiario>();
		
		for (MateriaDelCurso materiaDelCurso : materiaDelCursoList){
			MateriaDelLibroDiario materiaDelLibroDiario = new MateriaDelLibroDiario();
			materiaDelLibroDiario.setMateriaDelCurso(materiaDelCurso);
			
			materiaDelLibroDiarioList.add(materiaDelLibroDiario);
		}
		
		return materiaDelLibroDiarioList;
	}

}
