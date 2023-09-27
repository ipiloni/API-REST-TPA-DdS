package org.domain.config;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.openapi.plugin.OpenApiConfiguration;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;

import java.util.function.Consumer;

public class Config {
    public static Consumer<JavalinConfig> getConfigs() {
        return (config -> {
            OpenApiConfiguration openApiConfiguration = new OpenApiConfiguration();
            openApiConfiguration.getInfo().setTitle("Ranking Impacto de Incidentes API");
            openApiConfiguration.getInfo().setDescription("Este servicio permite calcular el ranking semanal previsto en la entrega anterior con el criterio de mayor grado de impacto de las problemáticas considerando que las que algunas comunidades tienen mayor cantidad de miembros y por lo tanto les afecta de mayor medida el no funcionamiento de ese servicio.");
            openApiConfiguration.setDocumentationPath("/openapi");
            openApiConfiguration.getInfo().setVersion("1.0.0");
            config.plugins.register(new OpenApiPlugin(openApiConfiguration));
            config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
        });
    }

}
