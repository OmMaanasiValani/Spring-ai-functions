package com.example.springaifunctions.functions;

import com.example.springaifunctions.model.StockPriceRequest;
import com.example.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

    @Component
    public class StockPriceFunction {

        private final String URL = "https://api.api-ninjas.com/v1/stockprice";

        @Value("${sfg.aiapp.ninjaapikey}")
        private String apiNinjasKey;


        public StockPriceResponse getStockPrice(String ticker) {
            System.out.println("Invoked apply.... ");

            RestClient restClient = RestClient.builder()
                    .baseUrl(URL)
                    .defaultHeaders(httpHeaders -> {
                        httpHeaders.set("X-Api-Key", apiNinjasKey);
                        httpHeaders.set("Accept", "application/json");
                        httpHeaders.set("Content-Type", "application/json");
                    }).build();

            return restClient.get().uri(uriBuilder -> {
                System.out.println("Building URI for stock price request: " + ticker);

                uriBuilder.queryParam("ticker", ticker);

                return uriBuilder.build();
            }).retrieve().body(StockPriceResponse.class);
        }
    }

