package com.fastturtle.springsecurityclient.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Authority extends BaseModel {

    private String name;

    @ManyToOne
    @JsonBackReference
    private AppUser user;

    public Authority() {

    }

    public Authority(String name) {
        this.name = name;
    }

}
