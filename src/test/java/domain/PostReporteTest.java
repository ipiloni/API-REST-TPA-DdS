package domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.domain.models.Entidad;
import org.domain.models.Incidente;
import org.domain.models.Ranking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostReporteTest {
    public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault();

            String url = "http://localhost:8080/api/reporte";

            HttpPost httpPost = new HttpPost(url);

            // Crear una entidad JSON que representa la entidad
            String jsonEntity = crearReporteDePrueba();

            StringEntity entity = new StringEntity(jsonEntity);

            // Configurar el tipo de contenido y establecer la entidad JSON en la solicitud
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // Realizar la solicitud POST
            HttpResponse response = httpClient.execute(httpPost);

            // Imprimir la respuesta
            System.out.println("Código de respuesta: " + response.getStatusLine().getStatusCode());
            System.out.println("Respuesta del servidor:");
            System.out.println(response.getEntity().getContent().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String crearReporteDePrueba() throws JsonProcessingException {
        Ranking ranking = new Ranking();

        Entidad entidad1 = new Entidad();
        Entidad entidad2 = new Entidad();
        Entidad entidad3 = new Entidad();

        Incidente incidente1 = new Incidente(LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(1), false);
        Incidente incidente2 = new Incidente(LocalDateTime.now().minusDays(3), null, true);
        Incidente incidente3 = new Incidente(LocalDateTime.now().minusHours(4), null, true);
        Incidente incidente4 = new Incidente(LocalDateTime.now().minusMinutes(45), null, true);
        Incidente incidente5 = new Incidente(LocalDateTime.now().minusDays(7), null, true);
        Incidente incidente6 = new Incidente(LocalDateTime.now().minusDays(8), LocalDateTime.now().minusDays(6), false);
        Incidente incidente7 = new Incidente(LocalDateTime.now().minusMinutes(120), LocalDateTime.now().minusMinutes(60), false);

        entidad1.setId(1);
        entidad2.setId(2);
        entidad3.setId(3);

        entidad1.setNombre("Apple");
        entidad2.setNombre("Microsoft");
        entidad3.setNombre("Google");

        List<Incidente> incidentesApple = new ArrayList<>();
        List<Incidente> incidentesMicrosoft = new ArrayList<>();
        List<Incidente> incidentesGoogle = new ArrayList<>();

        incidentesApple.add(incidente1);
        incidentesApple.add(incidente2);

        incidentesMicrosoft.add(incidente3);
        incidentesMicrosoft.add(incidente7);
        incidentesMicrosoft.add(incidente5);

        incidentesGoogle.add(incidente4);
        incidentesGoogle.add(incidente6);

        entidad1.setIncidentes(incidentesApple);
        entidad2.setIncidentes(incidentesMicrosoft);
        entidad3.setIncidentes(incidentesGoogle);

        List<Entidad> entidades = new ArrayList<>();
        entidades.add(entidad1);
        entidades.add(entidad2);
        entidades.add(entidad3);

        ranking.setEntidades(entidades);
        ranking.setCoeficiente(1.5);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(ranking);
    }
}
