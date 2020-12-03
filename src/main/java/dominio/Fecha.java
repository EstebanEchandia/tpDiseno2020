package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="fecha", schema = "tp")

public class Fecha {

	@Id
	@SequenceGenerator(name="fecha-seq",sequenceName="tp.fecha_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fecha-seq")
	private Integer id;

	@Column(name="numeroFecha")
	private Integer numeroFecha;
	
	@OneToMany(mappedBy = "id")
	private List<EncuentroDeportivo> encuentros;
	
	@ManyToOne()
    @JoinColumn(name = "id")
	private Fixture fixture;
	
	
	
	public Fecha() {
		encuentros = new ArrayList<EncuentroDeportivo>();
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroFecha() {
		return numeroFecha;
	}
	public void setNumeroFecha(Integer numeroFecha) {
		this.numeroFecha = numeroFecha;
	}
	public List<EncuentroDeportivo> getEncuentros() {
		return encuentros;
	}
	public void setEncuentros(List<EncuentroDeportivo> encuentros) {
		this.encuentros = encuentros;
	}
	
	public void agregarEncuentro(EncuentroDeportivo e) {
		this.encuentros.add(e);
	}
	
	
}