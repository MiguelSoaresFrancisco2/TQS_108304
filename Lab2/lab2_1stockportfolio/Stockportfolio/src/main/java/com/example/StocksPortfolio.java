package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StocksPortfolio {
    private IStockmarketService stockmarket;
    private List<Stock> stocks=new ArrayList<Stock>();


    public StocksPortfolio(IStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }


    public void addStock( Stock stock){
        this.stocks.add(stock);

    }

    public Double totalValue(){
        double total=0;
        for (Stock stock : stocks) {
            total+=stockmarket.lookUpPrice(stock.getLabel())*stock.getQuantity();

        }
        return total;

    }

     public List<Stock> mostValuableStocks(int topN) {
        return stocks.stream()
                .sorted(Comparator.comparingDouble(
                        stock -> -stockmarket.lookUpPrice(stock.getLabel()) * stock.getQuantity() // Ordenação decrescente
                ))
                .limit(topN) // Pega os primeiros 'topN' elementos
                .collect(Collectors.toList());
    }
}
