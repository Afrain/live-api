package com.projeto.live.document;

/**
 * @author AFRAIN
 * Date    28 de jul de 2020
 * E-mail  calixtoafrain@gmail.com
 */

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LiveDocument {

	@Id
	private String id;
	private String nomeLive;
	private String nomeCanal;
	private LocalDateTime dataLive;
	private String linkLive;
	private LocalDateTime dataCadastro;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeLive() {
		return nomeLive;
	}
	public void setNomeLive(String nomeLive) {
		this.nomeLive = nomeLive;
	}
	public String getNomeCanal() {
		return nomeCanal;
	}
	public void setNomeCanal(String nomeCanal) {
		this.nomeCanal = nomeCanal;
	}
	public LocalDateTime getDataLive() {
		return dataLive;
	}
	public void setDataLive(LocalDateTime dataLive) {
		this.dataLive = dataLive;
	}
	public String getLinkLive() {
		return linkLive;
	}
	public void setLinkLive(String linkLive) {
		this.linkLive = linkLive;
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
