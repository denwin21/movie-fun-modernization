package org.superbiz.moviefun.moviesapi.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviesClient {

    private final String moviesUrl;
    private final RestOperations restOperations;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MoviesClient(String moviesUrl, RestOperations restOperations){
        this.moviesUrl = moviesUrl;
        this.restOperations = restOperations;
    }

    public MovieInfo find(Long id) {
        return restOperations.getForEntity(moviesUrl + "/" + id, MovieInfo.class).getBody();
    }


    public void addMovie(MovieInfo movie) {
        restOperations.postForEntity(moviesUrl, movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {
        restOperations.delete(moviesUrl+ "/" + id);
    }

    public List<MovieInfo> getMovies() {
        return restOperations.getForEntity(moviesUrl, List.class).getBody();
    }

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("firstResult", firstResult);
        params.put("maxResults", maxResults);
        return restOperations.getForEntity(moviesUrl, List.class, params).getBody(); // +"?firstResult={}&maxResult={}"
    }

    public int countAll() {
        return restOperations.getForEntity(moviesUrl+"/count", Integer.class).getBody();
    }

    public int count(String field, String searchTerm) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("field", field);
        params.put("searchTerm", searchTerm);
        return restOperations.getForEntity(moviesUrl+"/count", Integer.class, params).getBody();
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {
        logger.info("############################TEAPOT {},{},{},{}",field,searchTerm,firstResult,maxResults);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(moviesUrl)
                // Add query parameter
                .queryParam("field", field)
                .queryParam("searchTerm", searchTerm)
                .queryParam("firstResult", String.valueOf(firstResult))
                .queryParam("maxResults", String.valueOf(maxResults));
        return restOperations.getForEntity(builder.toUriString(), List.class).getBody();
    }

    public void clean() {
        restOperations.delete(moviesUrl);
    }
}
