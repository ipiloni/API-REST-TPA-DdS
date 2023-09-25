package org.domain.handlers;

import io.javalin.http.Context;
import org.domain.mappers.RankingMapper;
import org.domain.models.Ranking;
import org.domain.models.dbo.RankingResponse;
import org.domain.persistence.Rankingdb;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetRankingHandlerTest {
    @InjectMocks
    private GetRankingHandler getRankingHandler;

    @Mock
    private RankingMapper rankingMapper;

    @Mock
    private Rankingdb rankingdb;

    @Mock
    private Context context;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verifyThrowExceptionRankingNull() throws Exception{
        Mockito.doReturn(null).when(rankingdb).get();

        getRankingHandler.handle(context);

        verify(context).status(404);
        verify(context, never()).json(any());
    }

    @Test
    public void verifyValidRanking() throws Exception{
        Mockito.doReturn(new Ranking(1, LocalDate.of(2023,9,11),List.of())).when(rankingdb).get();

        RankingResponse rankingResponse = new RankingResponse("2023-09-11", List.of());
        Mockito.doReturn(rankingResponse).when(rankingMapper).generateResponse(any());

        Mockito.when(context.status(200)).thenReturn(context); // Para el método status(200)
        Mockito.when(context.json(any())).thenReturn(context); // Para el método json(any())

        getRankingHandler.handle(context);

        verify(context).status(200);
        verify(context).json(rankingResponse);

    }
   /*
   public static void main(String[] args) {
        try {
            HttpClient httpClient = HttpClients.createDefault();

            String url = "http://localhost:4567/api/reporte";

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
    }*/

    /*public static String crearReporteDePrueba() throws JsonProcessingException {
        Ranking ranking = new Ranking();

        Entidad entidad1 = new Entidad();
        Entidad entidad2 = new Entidad();
        Entidad entidad3 = new Entidad();

        Incidente incidente1 = new Incidente(new Date(System.currentTimeMillis() - 2 * 24 * 60 * 60 * 1000), new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000), false);
        Incidente incidente2 = new Incidente(new Date(System.currentTimeMillis() - 3 * 24 * 60 * 60 * 1000), null, true);
        Incidente incidente3 = new Incidente(new Date(System.currentTimeMillis() - 4 * 60 * 60 * 1000), null, true);
        Incidente incidente4 = new Incidente(new Date(System.currentTimeMillis() - 45 * 60 * 1000), null, true); // hace 45 min y sigue abierto
        Incidente incidente5 = new Incidente(new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000), null, true);
        Incidente incidente6 = new Incidente(new Date(System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000), new Date(System.currentTimeMillis() - 5 * 12 * 60 * 60 * 1000), false); // hace 6 dias y se cerro hace 5 dias y 12 horas
        Incidente incidente7 = new Incidente(new Date(System.currentTimeMillis() - 120 * 60 * 1000), new Date(System.currentTimeMillis() - 60 * 60 * 1000), false);

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
        incidentesApple.add(incidente7);

        incidentesMicrosoft.add(incidente3);
        incidentesMicrosoft.add(incidente2);
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
    }*/
}
