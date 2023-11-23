package br.com.clocktimeapi.clocktimeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Clocktime API",
		version = "1.0",
		description = "API para controle de ponto eletrônico"
	),
	servers = {
		@Server(
			url = "http://localhost:8080",
			description = "Servidor Local"
		)
	}
)
public class ClocktimeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClocktimeapiApplication.class, args);
	}

}
