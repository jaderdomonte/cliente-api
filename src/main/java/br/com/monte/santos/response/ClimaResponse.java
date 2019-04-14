package br.com.monte.santos.response;

import java.math.BigDecimal;
import java.util.Date;

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
public class ClimaResponse {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("")
	private String tempo;
	
	@JsonProperty("")
	private String siglaTempo;
	
	@JsonProperty("wind_direction_compass")
	private String siglaDirecaoDoVento;
	
	@JsonProperty("created")
	private Date dataCriacao; 
	
	@JsonProperty("applicable_date")
	private Date data;
	
	@JsonProperty("min_temp")
	private BigDecimal temperaturaMinima;
	
	@JsonProperty("max_temp")
	private BigDecimal temperaturaMaxima; 
	
	@JsonProperty("the_temp")
	private BigDecimal temperatura; 
	
	@JsonProperty("wind_speed")
	private BigDecimal velocidadeDoVento; 
	
	@JsonProperty("wind_direction")
	private BigDecimal direcaoDoVento; 
	
	@JsonProperty("air_pressure")
	private BigDecimal pressaoDoAr; 
	
	@JsonProperty("humidity")
	private Long umidade; 
	
	@JsonProperty("visibility")
	private BigDecimal visibilidade;
	
	@JsonProperty("predictability")
	private Long previsibilidade;
}
