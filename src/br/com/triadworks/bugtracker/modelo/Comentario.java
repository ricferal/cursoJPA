package br.com.triadworks.bugtracker.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class Comentario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	
	@ManyToOne
	private Usuario autor;
	@ManyToOne
	private Bug bug;
	
	public Bug getBug() {
		return bug;
	}
	
	public void setBug(Bug bug) {
		this.bug = bug;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date criadoEm;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	public Date getCriadoEm() {
		return criadoEm;
	}
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

}
