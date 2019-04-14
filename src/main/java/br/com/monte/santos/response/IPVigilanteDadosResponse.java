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
public class IPVigilanteDadosResponse {
	
	@JsonProperty("ipv4")
	private String ipv4;
	
	@JsonProperty("continent_name")
	private String continente;
	
	@JsonProperty("country_name")
	private String pais;
	
	@JsonProperty("subdivision_1_name")
	private String estadoPrincipal;
	
	@JsonProperty("subdivision_2_name")
	private String estadoSecundario;
	
	@JsonProperty("city_name")
	private String cidade;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitude")
	private String longitude;
}
