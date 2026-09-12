package com.example.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockPriceResponse(@JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or ^DJI).")String ticker,
    @JsonPropertyDescription("The full company name associated with the ticker symbol.") String name,
    @JsonPropertyDescription("The current stock price. On paid plans this is the live price during market hours; on the free plan it is the closing price of the most recent completed trading session.") String price,
    @JsonPropertyDescription("The stock exchange where the stock is traded (e.g., NASDAQ, NYSE).")String exchange,
    @JsonPropertyDescription("Unix timestamp of the moment the price was struck — the last quote update on paid plans, or the 16:00 ET close of the session the price belongs to on the free plan. This is not the time your request was served, so a price that has not moved keeps the same updated value. Use it to tell a fresh price from a repeated one.")String updated,
    @JsonPropertyDescription("The currency code for the price (e.g., USD, EUR).")String currency,
    @JsonPropertyDescription("The trading volume (number of shares traded).")String volume,
    @JsonPropertyDescription("The closing price of the session immediately before the one price belongs to. null when only one session is on record.")String previous_close,
                                 @JsonPropertyDescription("The price move from previous_close to price, in the quote currency. Prefer this over comparing prices across two calls of your own — a price that has not been restruck yet will repeat, and differencing repeats yields the previous session's move rather than the current one. null when previous_close is unavailable.")String change



){}

