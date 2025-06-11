package com.cathay.exchangeflow.controller.currency;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cathay.exchangeflow.application.currency.AddCurrencyCommand;
import com.cathay.exchangeflow.application.currency.AddCurrencyUseCase;
import com.cathay.exchangeflow.application.currency.CurrencyListResult;
import com.cathay.exchangeflow.application.currency.DeleteCurrencyCommand;
import com.cathay.exchangeflow.application.currency.DeleteCurrencyUseCase;
import com.cathay.exchangeflow.application.currency.EditCurrencyCommand;
import com.cathay.exchangeflow.application.currency.EditCurrencyUseCase;
import com.cathay.exchangeflow.application.currency.GetCurrencyCommand;
import com.cathay.exchangeflow.application.currency.GetCurrencyListUseCase;
import com.cathay.exchangeflow.application.currency.GetCurrencyUseCase;
import com.cathay.exchangeflow.domain.currency.Currency;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {
    private final GetCurrencyListUseCase getCurrencyListUseCase;
    private final GetCurrencyUseCase getCurrencyUseCase;
    private final AddCurrencyUseCase addCurrencyUseCase;
    private final EditCurrencyUseCase editCurrencyUseCase;
    private final DeleteCurrencyUseCase deleteCurrencyUseCase;

    public CurrencyController(GetCurrencyListUseCase getCurrencyListUseCase,
            GetCurrencyUseCase getCurrencyUseCase, AddCurrencyUseCase addCurrencyUseCase,
            EditCurrencyUseCase editCurrencyUseCase, DeleteCurrencyUseCase deleteCurrencyUseCase) {
        this.getCurrencyListUseCase = getCurrencyListUseCase;
        this.getCurrencyUseCase = getCurrencyUseCase;
        this.addCurrencyUseCase = addCurrencyUseCase;
        this.editCurrencyUseCase = editCurrencyUseCase;
        this.deleteCurrencyUseCase = deleteCurrencyUseCase;
    }

    @GetMapping
    public GetCurrencyListResponse list() {
        CurrencyListResult result = getCurrencyListUseCase.execute();
        return new GetCurrencyListResponseBuilder(result).build();
    }

    @GetMapping("/{id}")
    public GetCurrencyResponse get(@PathVariable("id") Long id) {
        Currency result = getCurrencyUseCase.execute(new GetCurrencyCommand(id));
        return new GetCurrencyResponse(result.getId(), result.getCode().getValue(),
                result.getName(), result.getVersion().getValue());
    }

    @PostMapping
    public void add(@Valid @RequestBody AddCurrencyRequestData body) {
        addCurrencyUseCase.execute(new AddCurrencyCommand(body.getCode(), body.getName()));
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable("id") Long id,
            @Valid @RequestBody EditCurrencyRequestData body) {
        editCurrencyUseCase.execute(
                new EditCurrencyCommand(id, body.getCode(), body.getName(), body.getVersion()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        deleteCurrencyUseCase.execute(new DeleteCurrencyCommand(id));
    }
}
