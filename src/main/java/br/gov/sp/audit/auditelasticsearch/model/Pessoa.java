package br.gov.sp.audit.auditelasticsearch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "Pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private Long id;

	@Column(name= "Nome" , length = 50, nullable = false)
	private String nome;


	@Column(name= "Desc" , length = 255, nullable = true)
	private String desc;

	private Date created; 

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	@Override
	public String toString(){
		return  "Pessoa{" +
                "id=" + id +
                ", nome='" + nome+ '\'' +
                '}';
	}
}
