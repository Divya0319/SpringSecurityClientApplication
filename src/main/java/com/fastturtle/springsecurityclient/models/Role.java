package com.fastturtle.springsecurityclient.models;

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
    private AppUser user;

}
