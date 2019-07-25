package org.superbiz.moviefun.moviesapi.albums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class AlbumsClient {

    private final String albumsUrl;
    private final RestOperations restOperations;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AlbumsClient(String albumsUrl, RestOperations restOperations){
        this.albumsUrl = albumsUrl+"/repo";
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl, album, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.getForEntity(albumsUrl, List.class).getBody();
    }

    public AlbumInfo find(long albumId) {
        return restOperations.getForEntity(albumsUrl +"/"+ albumId, AlbumInfo.class).getBody();
    }
}
