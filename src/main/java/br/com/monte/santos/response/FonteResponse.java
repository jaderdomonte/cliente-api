package br.com.monte.santos.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class FonteResponse {
	
	@JsonProperty("title")
	private String fonte;
	
	@JsonProperty("slug")
	private String apelido;
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("crawl_rate")
	private Integer taxaDeRastreamento;
}
