package com.ititon.datacraw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@MappedSuperclass
@ToString
public abstract class LongIdEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    @Getter
    @Setter
    private Long id;


}
