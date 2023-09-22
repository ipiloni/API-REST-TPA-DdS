package org.domain.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.domain.models.ItemRanking;
import org.domain.models.Ranking;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PostReportesHandler implements Handler {
    @Override
    public void handle(@NotNull Context context) throws Exception {
        Ranking reporte = context.bodyAsClass(Ranking.class);
        validarReporte(reporte);
        System.out.println("Generando ranking...");
        List<ItemRanking> ranking = reporte.generarRanking();
        context.status(201);
        context.json(ranking);
    }

    private void validarReporte(Ranking reporte) {
        if(reporte.getEntidades().isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos una entidad para generar el Ranking.");
        }
    }
}
