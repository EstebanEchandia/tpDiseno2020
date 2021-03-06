package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="resultado", schema = "tp")

public class Resultado {


	@Id
	@SequenceGenerator(name="resultado-seq",sequenceName="tp.resultado_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="resultado-seq")
	private Integer id;
	
	@Column(name="participante1_presente")
	private Boolean participante1Presente;
	
	@Column(name="participante2_presente")
	private Boolean participante2Presente;
	
	@Column(name="participante1_ganador")
	private Boolean participante1Ganador;
	
	@Column(name="participante2_ganador")
	private Boolean participante2Ganador;
	
	

	@ManyToOne()
    @JoinColumn(name = "id_encuentro_deportivo")
	private EncuentroDeportivo encuentroAsociado;
	
}
