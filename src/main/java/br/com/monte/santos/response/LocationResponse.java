package br.com.monte.santos.response;

import java.util.List;

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
public class LocationResponse {
	
	@JsonProperty("consolidated_weather")
	private List<ClimaConsolidadoResponse> climaResponse;
	
	@JsonProperty("time")
	private String data;
	
	@JsonProperty("sun_rise")
	private String nascerDoSol;
	
	@JsonProperty("sun_set")
	private String porDoSol;
	
	@JsonProperty("timezone_name")
	private String nomeFusoHorario;
	
	@JsonProperty("parent")
	private LocalizacaoPaiResponse localizacaoPaiResponse;
	
	@JsonProperty("sources")
	private List<FonteResponse> fonteResponse;
	
	@JsonProperty("title")
	private String cidade;
	
	@JsonProperty("location_type")
	private String tipoLocalizacao;
	
	@JsonProperty("woeid")
	private Long id;
	
	@JsonProperty("latt_long")
	private String latitudeLongitude;
	
	@JsonProperty("timezone")
	private String fusoHorario;
}
