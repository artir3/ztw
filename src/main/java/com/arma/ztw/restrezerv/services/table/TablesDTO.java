package com.arma.ztw.restrezerv.services.table;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TablesDTO implements Serializable {

    private Long id;
    private String type;
    private Boolean extendable;
    private Short noSites;
    private Short naxNoSites;
    private Boolean open;

}
