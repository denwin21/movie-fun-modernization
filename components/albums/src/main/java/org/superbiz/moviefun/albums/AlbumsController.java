package org.superbiz.moviefun.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums/repo")
public class AlbumsController {
    private final AlbumsRepository albumsRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumsController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }


    @PostMapping
    public ResponseEntity add(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Album>> getAll() {
        logger.info("############################COFFEEPOT ");
        return new ResponseEntity<>(albumsRepository.getAlbums(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> find(@PathVariable Long id) {
        logger.info("############################COFFEEPOT ");
        return new ResponseEntity<>(albumsRepository.find(id), HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity catchException(Exception ex) {
        logger.error("############################COFFEEPOT FOUND: ERROR#", ex);
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }

}
