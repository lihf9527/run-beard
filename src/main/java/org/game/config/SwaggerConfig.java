package org.game.config;

import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Profile({"local", "dev", "test"})
@Component
@EnableSwagger2
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfig {
    @Setter
    private String host;

    @Bean
    public Docket docket() {
        List<ResponseMessage> responseMessages = responseMessages();
        return new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        return Collections.singletonList(new ApiKey("authorization", "authorization", "header"));
    }

    private List<ResponseMessage> responseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        ResponseMessageBuilder builder = new ResponseMessageBuilder();
        responseMessages.add(builder.code(0).message("失败").build());
        responseMessages.add(builder.code(1).message("成功").build());
        responseMessages.add(builder.code(404).message("资源不存在").build());
        responseMessages.add(builder.code(500).message("服务器异常").build());
        return responseMessages;
    }
}