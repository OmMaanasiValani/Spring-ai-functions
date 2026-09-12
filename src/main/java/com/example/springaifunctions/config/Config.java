package com.example.springaifunctions.config;

import com.example.springaifunctions.functions.StockPriceFunction;
import com.example.springaifunctions.model.StockPriceRequest;
import com.example.springaifunctions.model.StockPriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class Config {


    private final StockPriceFunction stockPriceFunction;

    @Value("${sfg.aiapp.ninjaapikey}")
    private String apiNinjasKey;

    @Tool(name = "getStockPrice",description = "Gets stock price for a given ticker symbol")
    public StockPriceResponse getStockPrice(@ToolParam(description = "Stock ticker symbol, example AAPL")String ticker){
       return stockPriceFunction.getStockPrice(ticker);
    }
}
