package br.com.monte.santos.rest.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	
	public static RestTemplate getRestTemplateBuilder(String url) {
		return new RestTemplateBuilder().rootUri(url).build();
	}
}
