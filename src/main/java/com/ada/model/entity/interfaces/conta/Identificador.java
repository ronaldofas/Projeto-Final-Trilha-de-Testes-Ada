package com.ada.model.entity.interfaces.conta;

import java.io.Serializable;

public interface Identificador<T> extends Serializable {
    T getValor();
    void validar();
}
