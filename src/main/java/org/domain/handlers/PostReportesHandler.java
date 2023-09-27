package org.domain.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.openapi.HttpMethod;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiContent;
import io.javalin.openapi.OpenApiResponse;
import org.domain.models.Entidad;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.jetbrains.annotations.NotNull;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@Tag(name = "Post Reportes")
public class PostReportesHandler implements Handler {
    @Operation(
            summary = "Crea un reporte",
            description = "Crea un reporte basado en una lista de entidades junto con sus incidentes."
    )
    @RequestBody(
            description = "Lista de entidades donde cada entidad tiene una lista de incidentes",
            required = true,
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Entidad.class)))
    )
    @ApiResponse(responseCode = "201", description = "Reporte creado")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")

    @OpenApi(
            path = "/api/reporte",
            methods = {HttpMethod.POST},
            responses = {
                @OpenApiResponse(status = "201", content = @OpenApiContent(from = Ranking.class)),
                @OpenApiResponse(status = "400" )
            }
    )
    @Override
    public void handle(@NotNull Context context) throws Exception {
        Ranking reporte = context.bodyAsClass(Ranking.class);

        validarReporte(reporte);

        List<ItemRanking> ranking = reporte.generarRanking();

        ranking.forEach(r -> System.out.println("Entidad: " + r.getEntidad().getNombre() + " - Valor: " + r.getValor()));

        context.status(201);

        context.json(ranking);
    }

    private void validarReporte(Ranking reporte) {
        if(reporte.getEntidades().isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos una entidad para generar el Ranking.");
        }
        reporte.getEntidades().forEach(Entidad::filtrarIncidentesDeLaSemana);
    }
}
