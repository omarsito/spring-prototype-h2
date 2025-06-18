package com.piolin.spring.prototype.pojo;

import com.piolin.spring.prototype.util.Util;

public class Deleted {

    private Long id;
    private boolean wasDeleted;

    public Deleted(){}

    public Deleted(Long id, boolean wasDeleted){
        this.id = id;
        this.wasDeleted = wasDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isWasDeleted() {
        return wasDeleted;
    }

    public void setWasDeleted(boolean wasDeleted) {
        this.wasDeleted = wasDeleted;
    }

    @Override
    public String toString(){
        return new Util().convertObject2Json(this);
    }

}