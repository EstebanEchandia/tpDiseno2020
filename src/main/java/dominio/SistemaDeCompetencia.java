package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="sistema_competencia", schema = "tp")
public class SistemaDeCompetencia {
	
	public enum Tipo {
		LIGA, ELIMIN_SIMPLE, ELIMIN_DOBLE
	}
	
	@Id
	@SequenceGenerator(name="sistema-de-competencia-seq",sequenceName="tp.sistema_competencia_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sistema-de-competencia-seq")
	protected Integer id;
	
	@OneToOne()
    @JoinColumn(name = "id_modalidad")
	protected Modalidad modalidad;
	
	

	public SistemaDeCompetencia() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	
	
	
	
}
