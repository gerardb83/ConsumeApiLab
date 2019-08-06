package co.grandcircus.ConsumeApiLab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumeApiLabApiService {

	private RestTemplate restTemplate;
	
	{
	    // This configures the restTemplateWithUserAgent to include a User-Agent header with every HTTP
		// request. Some of the APIs in this demo have a bug where User-Agent is required.
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
	        request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
	        return execution.execute(request, body);
	    };
	    restTemplate = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}
	
	public List<Tiny> getTinyList(){
		String url = "https://dwolverton.github.io/fe-demo/data/computer-science-hall-of-fame.json";
		ResultResponse response = restTemplate.getForObject(url, ResultResponse.class);
		List<Tiny> unsorted = response.getTiny();
		unsorted.sort(Comparator.comparing(Tiny::getYear));
		return unsorted;
	}
	
	public List<Complete> getCompleteList() {
		String url = "https://dwolverton.github.io/fe-demo/data/computer-science-hall-of-fame.json";
		ResultResponse response = restTemplate.getForObject(url, ResultResponse.class);
		List<Complete> unsorted = response.getComplete();
		unsorted.sort(Comparator.comparing(Complete::getYear));
		return unsorted;
	}
}
