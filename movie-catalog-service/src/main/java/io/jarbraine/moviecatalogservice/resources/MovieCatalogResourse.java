package io.jarbraine.moviecatalogservice.resources;

import io.jarbraine.moviecatalogservice.models.CatalogItem;
import io.jarbraine.moviecatalogservice.models.Movie;
import io.jarbraine.moviecatalogservice.models.Rating;
import io.jarbraine.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResourse {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder builder;
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
//        RestTemplate restTemplate=new RestTemplate();
        UserRating ratings=restTemplate.getForObject("http://localhost:8082/ratingData/users/"+userId, UserRating.class);

        System.out.println(ratings);
         return ratings.getRatingList().stream().map(rating->{
                 Movie movie=restTemplate.getForObject("http://localhost:8081/movies/"+rating.getMovieId(), Movie.class);
//          Movie movie=builder.build()
//                  .get()
//                  .uri("http://localhost:8081/movies/"+rating.getMovieId())
//                  .retrieve()
//                  .bodyToMono(Movie.class)
//                  .block();
                 return new CatalogItem(movie.getName(),"s",rating.getRating());})
                 .collect(Collectors.toList());
//        return Collections.singletonList(new CatalogItem("Tranfomer","Test",4))
    }
}
