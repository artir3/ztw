package com.arma.ztw.restrezerv.services.table;

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
@Table(name = "tables")
public class Tables implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String type;

    @Column
    private Boolean extendable;

    @Column
    private Short noSites;

    @Column
    private Short naxNoSites;

    @Column
    private Boolean open;

}
