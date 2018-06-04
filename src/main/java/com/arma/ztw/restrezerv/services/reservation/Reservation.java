package com.arma.ztw.restrezerv.services.reservation;

import com.arma.ztw.restrezerv.services.restaurant.Restaurant;
import com.arma.ztw.restrezerv.services.table.Tables;
import com.arma.ztw.restrezerv.services.users.Users;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean empty;

    @Column
    private LocalDateTime reservationFrom;

    @Column
    private LocalDateTime reservationTo;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Tables table;

    @ManyToOne
    private Restaurant restaurant;

}
