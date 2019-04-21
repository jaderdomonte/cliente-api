package br.com.monte.santos.facade;

import org.springframework.stereotype.Component;

import br.com.monte.santos.constants.RestPaths;
import br.com.monte.santos.response.LocationResponse;
import br.com.monte.santos.response.LocationSearchResponse;
import br.com.monte.santos.rest.client.RestClient;

@Component
public class ClimaFacade {

	public LocationSearchResponse consultarLocalizacaoPorCidade(String cidade) {
		LocationSearchResponse[] locationSearch= RestClient.getRestTemplateBuilder(RestPaths.META_WEATHER_API_LOCATION)
														   .getForObject("/search/?query="+cidade, LocationSearchResponse[].class);
		
		return locationSearch[0];
	}
	
	public LocationResponse consultarLocalizacaoPorLatitudeLongitude(String latitude, String longitude) {
		LocationSearchResponse locationSearch[] = RestClient.getRestTemplateBuilder(RestPaths.META_WEATHER_API_LOCATION)
													   .getForObject(construirUrlComParametros(latitude, longitude), LocationSearchResponse[].class);
		LocationResponse location = consultarClimaPorLocalizacao(locationSearch[0].getId());
		
		return location;
	}

	public LocationResponse consultarClimaPorLocalizacao(Long idLocalizacao) {
		return RestClient.getRestTemplateBuilder(RestPaths.META_WEATHER_API_LOCATION)
														 .getForObject("/{woeid}", LocationResponse.class, idLocalizacao);
	}
	
	private String construirUrlComParametros(String latitude, String longitude) {
		return "/search/?lattlong="+latitude+","+longitude;
	}
}
