package gestor;

import java.util.ArrayList;
import java.util.List;

import dao.LugarDeRealizacionDAO;
import dao.LugarDeRealizacionDAOHibernate;
import dominio.LugarDeRealizacion;
import utils.Pair;
import utils.Triplet;

public class GestorLugarDeRealizacion {

	public static List<Triplet<Integer, String, Integer>> recuperarLugares(int idDeporte) {
		List<Triplet<Integer, String, Integer>> listaRetorno = new ArrayList<Triplet<Integer, String, Integer>> ();
		
		LugarDeRealizacionDAO lugarDeRealizacionDao = new LugarDeRealizacionDAOHibernate();
	
		List<LugarDeRealizacion> listaAuxiliar = lugarDeRealizacionDao.recuperarLugaresDeRealizacion(idDeporte);
		
		for (LugarDeRealizacion unLugar : listaAuxiliar) {
			listaRetorno.add(new Triplet<Integer, String, Integer>(unLugar.getCodigo(),unLugar.getNombre(),unLugar.getDisponibilidad()));
		}
		
		return listaRetorno;		
	}
	
}
