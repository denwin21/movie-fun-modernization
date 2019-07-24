package org.superbiz.moviefun.moviesapi;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviesClient {

    private final RestTemplate restTemplate;
    private String moviesUrl;

    public MoviesClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public MovieInfo find(Long id) {
        return restTemplate.postForEntity(moviesUrl, id, MovieInfo.class).getBody();
    }


    public void addMovie(MovieInfo movie) {
        restTemplate.postForEntity(moviesUrl, movie, MovieInfo.class);
    }

    public void updateMovie(MovieInfo movie) {
        restTemplate.postForEntity(moviesUrl, movie, MovieInfo.class);
    }

    public void deleteMovie(MovieInfo movie) {
        restTemplate.delete(moviesUrl, movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {
        restTemplate.delete(moviesUrl, id, MovieInfo.class);
    }

    public List<MovieInfo> getMovies() {
        restTemplate.getForEntity(moviesUrl, List.class);
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("firstResult", firstResult);
        params.put("maxResults", maxResults);
        return restTemplate.getForEntity(moviesUrl, List.class, params).getBody(); // +"?firstResult={}&maxResult={}"
    }

    public int countAll() {
      
    }

    public int count(String field, String searchTerm) {
      
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        
    }

    public void clean() {
        
    }
}
