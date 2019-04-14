package br.com.monte.santos.facade;

import org.springframework.stereotype.Component;

import br.com.monte.santos.response.LocationResponse;
import br.com.monte.santos.response.LocationSearchResponse;
import br.com.monte.santos.rest.client.RestClient;

@Component
public class ClimaFacade {

	private static final String META_WEATHER_API_LOCATION = "https://www.metaweather.com/api/location";

	private static final String META_WEATHER_API_LOCATION_SEARCH = META_WEATHER_API_LOCATION + "/search";
	
	public LocationSearchResponse consultarLocalizacaoPorCidade(String cidade) {
		LocationSearchResponse localizacao = RestClient.getRestTemplateBuilder(META_WEATHER_API_LOCATION_SEARCH)
				   									   .getForObject("/?query=", LocationSearchResponse.class, cidade);
		
		return localizacao;
	}
	
	public LocationSearchResponse consultarLocalizacaoPorLatitudeLongitude(String latitude, String longitude) {
		LocationSearchResponse localizacao = RestClient.getRestTemplateBuilder(META_WEATHER_API_LOCATION_SEARCH)
													   .getForObject("/?lattlong=", LocationSearchResponse.class, latitude+","+longitude);
		return localizacao;
	}
	
	public LocationResponse consultarClimaPorLocalizacao(Long idLocalizacao) {
		LocationResponse localizacaoResponse = RestClient.getRestTemplateBuilder(META_WEATHER_API_LOCATION)
														 .getForObject("/", LocationResponse.class, idLocalizacao);
		return localizacaoResponse;
		
	}
}
