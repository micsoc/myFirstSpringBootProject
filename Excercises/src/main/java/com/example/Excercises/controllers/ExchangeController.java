package com.example.Excercises.controllers;

import com.example.Excercises.currency.CurrencyExchanger;
import com.example.Excercises.currency.CurrencyType;
import com.example.Excercises.currency.ExchangeRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/")
@RestController
public class ExchangeController {

    @GetMapping("list")
    public List<CurrencyType> getList() {
        return Arrays.asList(CurrencyType.values());
    }


    @PostMapping("exchange")
    public CurrencyExchanger exchangeCurrency(@RequestBody ExchangeRequest request) {
        CurrencyExchanger exchanger = new CurrencyExchanger();

        exchanger.setAmount(request.getAmount());
        exchanger.setBuyCurrency(request.getBuy());
        exchanger.setSellCurrency(request.getSell());

        exchanger.exchange();
        return exchanger;
    }

    @PostMapping("courselist")
    public Map<CurrencyType, Double> getCourseList(@RequestBody CurrencyType[] list) {
        Map<CurrencyType, Double> coursesMap = new LinkedHashMap<>();
        CurrencyExchanger exchanger = new CurrencyExchanger();
        for (CurrencyType currency : list) {
            if (!currency.equals(CurrencyType.PLN)) {
                coursesMap.put(currency, exchanger.downloadCourse(currency));

            } else {
                coursesMap.put(currency, 1.00);
            }
        }
        return coursesMap;
    }

}
