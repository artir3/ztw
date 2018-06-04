package com.arma.ztw.restrezerv.services.liked_restaurants;

import com.arma.ztw.restrezerv.services.client.Client;
import com.arma.ztw.restrezerv.services.restaurant.Restaurant;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "liked_restaurants")
public class LikedRestaurants implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String opinion;

    @Column
    private Boolean liked;

    @Column
    private Short points;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Restaurant restaurant;
}
