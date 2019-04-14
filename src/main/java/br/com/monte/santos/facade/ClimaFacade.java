package br.com.monte.santos.facade;

import org.springframework.stereotype.Component;

import br.com.monte.santos.response.ClimaConsolidadoResponse;
import br.com.monte.santos.response.LocationResponse;
import br.com.monte.santos.response.LocationSearchResponse;
import br.com.monte.santos.rest.client.RestClient;

@Component
public class ClimaFacade {

	private static final String META_WEATHER_API_LOCATION = "https://www.metaweather.com/api/location";

	public LocationSearchResponse consultarLocalizacaoPorCidade(String cidade) {
		LocationSearchResponse[] locationSearch= RestClient.getRestTemplateBuilder(META_WEATHER_API_LOCATION)
														   .getForObject("/search/?query="+cidade, LocationSearchResponse[].class);
		
		System.out.println(locationSearch[0]);
		
		return null;
	}
	
//	public LocationSearchResponse consultarLocalizacaoPorCidade(String cidade) {
//
//		LocationSearchResponse[] localizacao = null;
//
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//
//		localizacao = restTemplate.getForObject(META_WEATHER_API_LOCATION_SEARCH + cidade, LocationSearchResponse[].class);
//		
//		System.out.println(localizacao[0]);
//		
//		return localizacao[0];
//	}
	
	public LocationSearchResponse consultarLocalizacaoPorLatitudeLongitude(String latitude, String longitude) {
		LocationSearchResponse locationSearch[] = RestClient.getRestTemplateBuilder(META_WEATHER_API_LOCATION)
													   .getForObject(construirUrlComParametros(latitude, longitude), LocationSearchResponse[].class);
//		System.out.println(locationSearch[0]);
		
		consultarClimaPorLocalizacao(locationSearch[0].getId());
		
		return locationSearch[0];
	}

	private String construirUrlComParametros(String latitude, String longitude) {
		return "/search/?lattlong="+latitude+","+longitude;
	}
	
	public LocationResponse consultarClimaPorLocalizacao(Long idLocalizacao) {
		LocationResponse localizacaoResponse = RestClient.getRestTemplateBuilder(META_WEATHER_API_LOCATION)
														 .getForObject("/{woeid}", LocationResponse.class, idLocalizacao);
		
		System.out.println(localizacaoResponse);
		
		return null;
		
	}
}
