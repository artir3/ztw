package com.arma.ztw.restrezerv.services.localization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "localization")
public class Localization implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String city;

    @Column(columnDefinition = "text")
    private String street;

    @Column(columnDefinition = "text")
    private String homeNo;

    @Column(columnDefinition = "text")
    private String latitude;

    @Column(columnDefinition = "text")
    private String longitude;

}
