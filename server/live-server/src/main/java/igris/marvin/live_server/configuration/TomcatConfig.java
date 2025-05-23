package igris.marvin.live_server.configuration;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public TomcatConnectorCustomizer connectorCustomizer() {
        return (connector) -> {
            connector.setProperty("maxConnections", "10000");
            connector.setProperty("connectionTimeout", "60000"); // 60 sec timeout
        };
    }

}
