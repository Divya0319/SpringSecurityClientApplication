package com.fastturtle.springsecurityclient.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends BaseModel {

    private String name;

    @ManyToOne
    @JsonBackReference
    private AppUser user;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

}
