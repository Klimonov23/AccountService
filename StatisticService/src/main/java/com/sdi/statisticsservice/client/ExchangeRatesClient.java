package com.sdi.statisticsservice.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sdi.statisticsservice.domain.Currency;
import com.sdi.statisticsservice.domain.ExchangeRatesContainer;
import com.sdi.statisticsservice.util.LocalDateTypeAdapter;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collections;

@Component
public class ExchangeRatesClient {
    private static final Gson GSON=new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();
    @Value("${rates.url}")
    private String url;
    @Value("${rates.access_key}")
    private String access_key;
    public ExchangeRatesContainer getRates(Currency base) {
        Content getResult = null;
        try {
            getResult= Request.Get(url+"?access_key="+access_key+"&symbols=USD,RUB,EUR.")
                    .execute().returnContent();
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
        if (getResult==null) throw new NullPointerException("Your API is bullshit");
        String res= getResult.asString();
        Type type = new TypeToken<ExchangeRatesContainer>(){}.getType();
        return GSON.fromJson(res,type);
    }

}
