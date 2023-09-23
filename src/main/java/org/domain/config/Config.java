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
            config.plugins.register(new OpenApiPlugin(new OpenApiPluginConfiguration()));
            config.plugins.register(new SwaggerPlugin(new SwaggerConfiguration()));
            // Agregar mas en caso de ser necesario
        });
    }

}
