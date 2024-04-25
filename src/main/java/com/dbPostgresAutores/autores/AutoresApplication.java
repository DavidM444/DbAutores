package com.dbPostgresAutores.autores;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.RSocketGraphQlClient;

@SpringBootApplication
public class AutoresApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AutoresApplication.class, args);
	}

	@Bean
	HttpGraphQlClient httpClient (){
		return  HttpGraphQlClient.builder().url("http://127.0.0.1:8082/grapql").build();
	}

	@Bean
	RSocketGraphQlClient rSocketGraphQlClient(RSocketGraphQlClient.Builder<?> builder){
		return builder.tcp("127.0.0.1",8081).route("/grapql").build();
	}

	@Bean
	ApplicationRunner applicationRunner(RSocketGraphQlClient rsocket, HttpGraphQlClient htttp){
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				var HttpRequestDocument = """
						query{
							hello: String
						}
						""";
				htttp.document(HttpRequestDocument).retrieve("/hello").toEntity(Name.class).subscribe(System.out::println);
			}
		};
	}
	record Name(String res){}
}
