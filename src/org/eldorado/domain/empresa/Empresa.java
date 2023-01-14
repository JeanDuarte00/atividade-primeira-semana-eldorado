package org.eldorado.domain.empresa;

import java.util.UUID;

public class Empresa {

    UUID id;
    String chave;

    public Empresa(String chave) {
        this.id = UUID.randomUUID();
        this.chave = chave;
    }
}
