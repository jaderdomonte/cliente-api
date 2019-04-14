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
public class LocationSearchResponse {

	@JsonProperty("woeid")
	private String id;
	
	@JsonProperty("title")
	private String localizacao;
	
	@JsonProperty("location_type")
	private String tipoLocalizacao;
	
	@JsonProperty("latt_long")
	private String  latitudeLongitude;
}
