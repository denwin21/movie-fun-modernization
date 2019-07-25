package org.superbiz.moviefun.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    private final MoviesRepository moviesRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> find(@PathVariable Long id) {
        return new ResponseEntity<>(moviesRepository.find(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Movie movie) {
        moviesRepository.addMovie(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}") //test ohne "/{id}"
    public ResponseEntity deleteMovieId(@PathVariable Long id) {
        moviesRepository.deleteMovieId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count(@RequestParam(required = false) String field, @RequestParam(required = false) String searchTerm) {
        if (field != null && searchTerm != null)
            return new ResponseEntity<Integer>(moviesRepository.count(field, searchTerm), HttpStatus.OK);
        return new ResponseEntity<Integer>(moviesRepository.countAll(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findRange(@RequestParam(name="field", required = false) String field, @RequestParam(required = false) String searchTerm, @RequestParam(required = false) Integer firstResult, @RequestParam(required = false) Integer maxResults) {
        logger.info("############################TEAPOT {},{},{},{}",field,searchTerm,firstResult,maxResults);
        if (field != null && searchTerm != null && firstResult != null && maxResults != null)
            return new ResponseEntity<List<Movie>>(moviesRepository.findRange(field, searchTerm, firstResult, maxResults), HttpStatus.OK);
        else if (firstResult != null && maxResults != null)
            return new ResponseEntity<>(moviesRepository.findAll(firstResult, maxResults), HttpStatus.OK);
        return new ResponseEntity<>(moviesRepository.getMovies(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity clean() {
        moviesRepository.clean();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity catchException(Exception ex) {
        logger.error("############################TEAPOT FOUND: ERROR#", ex);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

}
