package com.arma.ztw.restrezerv.services.liked_restaurants;

import com.google.common.collect.Sets;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public interface LikedRestaurantsRepository extends CrudRepository<LikedRestaurants, Long> {

    default LikedRestaurants saveOrUpdate(LikedRestaurants likedRestaurants) {
        LikedRestaurants ls;
        if (likedRestaurants.getId() == null) {
            ls = new LikedRestaurants();
        } else ls = findById(likedRestaurants.getId()).get();
        ls.setClient(likedRestaurants.getClient());
        ls.setLiked(likedRestaurants.getLiked());
        ls.setOpinion(likedRestaurants.getOpinion());
        ls.setPoints(likedRestaurants.getPoints());
        ls.setRestaurant(likedRestaurants.getRestaurant());
        return save(ls);
    }

    default Set<LikedRestaurants> saveOrUpdate(Set<LikedRestaurants> list) {
        Set<LikedRestaurants> result = new HashSet<>();
        for (LikedRestaurants likedRestaurants : list) {
            result.add(saveOrUpdate(likedRestaurants));
        }
        return Sets.newHashSet(saveAll(result));
    }
}
