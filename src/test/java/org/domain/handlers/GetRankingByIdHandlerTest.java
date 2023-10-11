package org.domain.handlers;

import io.javalin.http.Context;
import io.javalin.validation.Validator;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetRankingByIdHandlerTest {
    @InjectMocks
    private GetRankingByIdHandler getRankingByIdHandler;

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

    //TODO
    @Test
    public void verifyThrowExceptionRankingNull() throws Exception{
        Mockito.when(context.pathParam(any())).thenReturn("1");
        Mockito.doReturn(null).when(rankingdb).get(any());
        getRankingByIdHandler.handle(context);

        verify(context).status(404);
        verify(context, never()).json(any());
    }

    @Test
    public void verifyValidRanking() throws Exception{
        Mockito.doReturn(new Ranking(LocalDate.of(2023,9,11),List.of())).when(rankingdb).get(any());

        RankingResponse rankingResponse = new RankingResponse("2023-09-11"/*, List.of()*/);

        Mockito.doReturn(rankingResponse).when(rankingMapper).generateResponse(any());
        Mockito.when(context.pathParam(any())).thenReturn("1");
        Mockito.when(context.status(200)).thenReturn(context); // Para el método status(200)
        Mockito.when(context.json(any())).thenReturn(context); // Para el método json(any())

        getRankingByIdHandler.handle(context);

        verify(context).status(200);
        verify(context).json(rankingResponse);

    }
}
