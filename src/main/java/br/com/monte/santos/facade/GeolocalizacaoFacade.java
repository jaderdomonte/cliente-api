package br.com.monte.santos.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.monte.santos.dto.LocalizacaoDTO;
import br.com.monte.santos.response.LocalizacaoResponse;
import br.com.monte.santos.rest.client.RestClient;

@Component
public class GeolocalizacaoFacade {

	private static final String HTTPS_IPVIGILANTE_COM = "https://ipvigilante.com";

	public LocalizacaoDTO consultarLocalizacaoPorIp(String ip) {
		ResponseEntity<LocalizacaoResponse> localizacaoResponse = RestClient.getRestTemplateBuilder(HTTPS_IPVIGILANTE_COM)
															.getForEntity("/", LocalizacaoResponse.class, ip);
		return convertTo(localizacaoResponse);
	}
	
	private LocalizacaoDTO convertTo(ResponseEntity<LocalizacaoResponse> localizacaoResponse) {
		return LocalizacaoDTO.builder().latitude(localizacaoResponse.getBody().getData().getLatitude())
									   .longitude(localizacaoResponse.getBody().getData().getLongitude())
									   .build();
	}
}
