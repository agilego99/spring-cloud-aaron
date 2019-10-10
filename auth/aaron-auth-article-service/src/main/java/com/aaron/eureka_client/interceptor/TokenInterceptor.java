package com.aaron.eureka_client.interceptor;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
/**
 * RestTemplate攔截器，傳遞認證的Token
 * @author aaron
 *
 */
@Component
public class TokenInterceptor implements ClientHttpRequestInterceptor {
 	@Override
 	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
 			throws IOException {
 		System.err.println("進入RestTemplate攔截器");
 		HttpHeaders headers = request.getHeaders();
 	    headers.add("Authorization", System.getProperty("fangjia.auth.token"));
 	    return execution.execute(request, body);
 	}
}
