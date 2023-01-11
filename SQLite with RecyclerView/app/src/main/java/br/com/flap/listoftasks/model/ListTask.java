package br.com.flap.listoftasks.model;

import java.io.Serializable;

public class ListTask implements Serializable {
    private Long id;
    private String task;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
