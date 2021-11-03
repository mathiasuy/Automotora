package com.automotora.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Table(name = "users")
@Setter
@ToString
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
    private String name;
    private String email;
    private String country;
    private String website;

}
