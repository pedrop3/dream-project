package com.learn.dream.project.config;

//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("public-apis")
//                .pathsToMatch("/**")
//                .build();
//    }
//
//    @Bean
//    OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("API title").version("API version"))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(
//                        new Components()
//                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")));
//    }
//}


import org.springframework.context.annotation.Configuration;



@Configuration
//@OpenAPIDefinition(info = @Info(
//        title = "ms-imedicina-transfer",
//        description = "Serviço de repasse do iMedicina.",
//        version = "v1"
//))
public class SwaggerConfig {

}
