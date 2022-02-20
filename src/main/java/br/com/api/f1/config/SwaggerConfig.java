package br.com.api.f1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class SwaggerConfig {

    private static final String TITLE = "API de Dados da Formula 1";
    private static final String HTTP = "http";

    @Value("${gateway.host:#{null}}")
    private String gatewayHost;

    @Value("${gateway.port:#{null}}")
    private String gatewayPort;

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") final String appDescription, @Value("${application-version}") final String appVersion) {
        final String gatewayUrl = getApiGatewayServerUrl(gatewayHost, gatewayPort);
        if (StringUtils.isEmpty(gatewayUrl)) {
            return new OpenAPI()
                .info(new Info()
                    .title(TITLE)
                    .version(appVersion)
                    .description(appDescription));
        } else {
            return new OpenAPI()
                .addServersItem(new Server().url(gatewayUrl))
                .info(new Info()
                    .title(TITLE)
                    .version(appVersion)
                    .description(appDescription));
        }
    }

    private String getApiGatewayServerUrl(final String gatewayHost, final String gatewayPort) {
        String returnLink = null;
        final UriComponents uriComponents;
        if (StringUtils.isNotEmpty(gatewayHost)) {
            if (NumberUtils.isParsable(gatewayPort)) {
                uriComponents = UriComponentsBuilder.newInstance().scheme(HTTP)
                    .host(gatewayHost)
                    .port(NumberUtils.toInt(gatewayPort))
                    .build();
            } else {
                uriComponents = UriComponentsBuilder.newInstance().scheme(HTTP).host(gatewayHost).build();
            }

            returnLink = uriComponents.toUriString();
        }

        return returnLink;
    }

}
