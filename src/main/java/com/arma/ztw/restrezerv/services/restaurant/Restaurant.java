package com.arma.ztw.restrezerv.services.restaurant;

import com.arma.ztw.restrezerv.services.category.Category;
import com.arma.ztw.restrezerv.services.employee.Employee;
import com.arma.ztw.restrezerv.services.liked_restaurants.LikedRestaurants;
import com.arma.ztw.restrezerv.services.localization.Localization;
import com.arma.ztw.restrezerv.services.table.Tables;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String menu;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    private Localization localization;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Tables> tables;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_employee",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    )
    private Set<Employee> employees;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "restaurant_category",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    )
    private Set<Category> categories;

    @OneToMany(mappedBy = "restaurant")
    private Set<LikedRestaurants> likedRestaurants;
}
