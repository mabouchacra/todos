package com.devops.model;

import java.io.Serializable;

/**
 * Created by SQLI on 16/11/16.
 */
public class Todo implements Serializable {

    private Long id;

    private String texte;

    private String responsable;

    private StatusTodo status = StatusTodo.ACTIVE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
