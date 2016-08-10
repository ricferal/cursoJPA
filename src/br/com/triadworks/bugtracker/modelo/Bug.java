package br.com.triadworks.bugtracker.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Bug implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String sumario;
	@Column(length=1000)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	@ManyToOne
	private Usuario responsavel;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTO;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="bug",fetch=FetchType.LAZY)
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	/**
	 * Atualiza data de criação
	 */
	@PrePersist
	public void atualizaData() {
		this.criadoEm = new Date();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	public Usuario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<Comentario> getComentarios() {
		return Collections.unmodifiableList(this.comentarios);
	}
	/*public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bug other = (Bug) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	public void comenta(Comentario comentario) throws ComentarioSemAutorException{
		if (comentario.getAutor() == null) {
			throw new ComentarioSemAutorException();
		}
		
		comentario.setBug(this);
		this.comentarios.add(comentario);
	}
	
	public void adiciona(Comentario comentario){
		comentario.setBug(this);
		this.comentarios.add(comentario);
	}
	
}
