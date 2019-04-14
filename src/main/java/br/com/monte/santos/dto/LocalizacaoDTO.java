package br.com.monte.santos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizacaoDTO {

	private String latitude;
	
	private String longitude;
	
	private String cidade;
}
