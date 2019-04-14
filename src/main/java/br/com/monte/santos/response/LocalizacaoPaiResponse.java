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
public class LocalizacaoPaiResponse {

	@JsonProperty("title")
	private String nome;
	
	@JsonProperty("location_type")
	private String tipo;
	
	@JsonProperty("woeid")
	private Integer id;
	
	@JsonProperty("latt_long")
	private String latitudeLongitude;
}
