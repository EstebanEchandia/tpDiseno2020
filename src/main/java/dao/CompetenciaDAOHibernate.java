package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dominio.Competencia;
import dominio.Deporte;
import dominio.Reserva;
import dto.CompetenciaDTO;
import utils.HibernateUtils;

public class CompetenciaDAOHibernate implements CompetenciaDAO{
	
	public static synchronized CompetenciaDAO getInstance() {
		if(competenciaDAO==null) {
			competenciaDAO = new CompetenciaDAOHibernate();
		}
		return competenciaDAO;		
	}
	
	private static CompetenciaDAO competenciaDAO = null;
	
	public CompetenciaDAOHibernate() {
		super();
		
	}
	

	public Competencia darDeAltaCompetencia(CompetenciaDTO compeDTO) {
	
		return null;
	}
	
	public Boolean guardarCompetencia(Competencia competencia) throws Exception{
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
		    
			session.save(competencia);
			session.flush();
			
			LugarDeRealizacionDAOHibernate.getInstance().guardarReservas(competencia.getReservasDisponibles(), competencia, session);
			
			session.flush();
			
			competencia.getModalidad().setCompetencia(competencia);
			session.save(competencia.getModalidad());
			session.flush();
					
			SistemaDeCompetenciaDAOHibernate.getInstance().guardarSistemaDeCompetencia(competencia.getModalidad().getSistemaCompetencia(), competencia.getModalidad(), session);
			session.flush();
			
			FormaPuntuacionDAOHibernate.getInstance().guardarFormaPuntuacion(competencia.getModalidad().getFormaPuntuacion(), competencia.getModalidad(), session);
			session.flush();
			
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();	
			throw ex;
		}
		finally {
			
			session.close();
			
		}
		return true;
	}

	public Boolean verificarSiExiste(String nombre) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Competencia> criteria = builder.createQuery(Competencia.class);
		    Root<Competencia> root = criteria.from(Competencia.class);
		    criteria.select(root).where(builder.equal(root.get("nombre"), nombre));
		    List<Competencia> deportes = session.createQuery(criteria).getResultList();
		    
			return deportes.size()>0;
		}
		finally {
			session.close();
		}
	}
	public Competencia recuperarCompetencia() {
	
		return null;
	}


	public void darDeBajaCompetencia() {
		// TODO Auto-generated method stub
		
	}


	public void modificarCompetencia() {
		// TODO Auto-generated method stub
		
	}


	public void update(Competencia competencia) {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
}
