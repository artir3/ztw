package com.arma.ztw.restrezerv.services.client;

import com.arma.ztw.restrezerv.services.category.Category;
import com.arma.ztw.restrezerv.services.liked_restaurants.LikedRestaurants;
import com.arma.ztw.restrezerv.services.localization.Localization;
import com.arma.ztw.restrezerv.services.users.Users;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Client")
public class Client implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String status;

    @Column()
    private String description;

    @ManyToOne
    private Users user;

    @OneToMany//(mappedBy = "client")
    private Set<Category> likedCategories;

    @OneToMany//(mappedBy = "client")
    private Set<Localization> likedLocalizations;

    @OneToMany(mappedBy = "client")
    private Set<LikedRestaurants> likedRestaurants;
}
