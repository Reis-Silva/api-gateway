package br.com.infnet.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder){
		return 	builder.routes()
				.route(p -> p
						.path("/api/gateway-vacina/**")
						.filters(f -> f.stripPrefix(2)
								.addResponseHeader("HoraRequisicao", String.valueOf(LocalDateTime.now())))
						.uri("lb://VACINA-SERVICE")
				)
				.route(p -> p
						.path("/api/gateway-venda/**")
						.filters(f -> f.stripPrefix(2)
								.addResponseHeader("HoraRequisicao", String.valueOf(LocalDateTime.now())))
						.uri("lb://VENDA-SERVICE")
				)
				.build();
	}
}
