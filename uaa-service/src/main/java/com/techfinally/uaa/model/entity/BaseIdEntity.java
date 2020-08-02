package com.techfinally.uaa.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Truong Duong
 */
@MappedSuperclass
@Getter
@Setter
public class BaseIdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

}