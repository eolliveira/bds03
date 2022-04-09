package com.devsuperior.bds03.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> erros = new ArrayList<>();

    public void addError(String field, String message) {
        erros.add(new FieldMessage(field, message));
    }

    public List<FieldMessage> getErros() {
        return erros;
    }
}
