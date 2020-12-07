package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import dominio.Competencia;
import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;
import utils.HibernateUtils;

public class ParticipanteDAOHibernate implements ParticipanteDAO{

	@Override
	public Participante darDeAltaParticpante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeBajaParticpante(ParticipanteDTO participanteDTO) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null; 
		
		try{
		    tx = session.beginTransaction();
		    		    
		    CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Participante> criteria = builder.createQuery(Participante.class);
		    Root<Participante> root = criteria.from(Participante.class);
		    criteria.select(root).where(builder.equal(root.get("nombre"), participanteDTO.getNombre()));
		    Participante participante = session.createQuery(criteria).getSingleResult();
			
		    session.delete(participante);

			System.out.println("El participante "+ participante.getNombre()+" se elimino con exito");
			session.flush();	
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();	
			throw ex;
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();	
		}
	}

	@Override
	public void modificarParticipante() {
	
		
	}

	@Override
	public void guardarParticipante(Participante participante) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
		    
			session.save(participante);
			session.flush();	
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();	
			throw ex;
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
			
		}
		
	}

	@Override
	public Participante recuperarParticipante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean nombreOEmailYaExiste(ParticipanteDTO participante, CompetenciaDTO competencia) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			String hql = "SELECT nombre FROM Participante p WHERE nombre = :nombre_participante and id_competencia = :id_competencia";
			Query query = session.createQuery(hql);
			query.setParameter("nombre_participante",participante.getNombre());
			query.setParameter("id_competencia", competencia.getId());
			List resultados = query.list();
			
			if (resultados.isEmpty()) {
				return false;
			}
			else {
				return true;
			}
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}
	}

}
