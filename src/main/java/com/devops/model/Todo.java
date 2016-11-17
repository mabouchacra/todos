package com.devops.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by SQLI on 16/11/16.
 */
public class Todo implements Serializable {

    private UUID id;

    @NotNull
    private String texte;

    private String responsable;

    @NotNull
    private StatusTodo status = StatusTodo.ACTIVE;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public StatusTodo getStatus() {
        return status;
    }

    public void setStatus(StatusTodo status) {
        this.status = status;
    }
}
