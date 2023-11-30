package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.apis.CSVApiImpl;
import imd.ufrn.br.spotify.apis.ICSVApi;
import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.repositories.IFolderRepository;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CSVPlaylistRepositoryImpl extends CSVRepositoryImpl<Playlist> implements IPlaylistRepository {
    // private  final String CSV_FILE_NAME = "";
    // // Path joab
    private  final String CSV_FILE_NAME = "C:\\Users\\Joab\\IdeaProjects\\spotify\\db\\playlists.txt";

    // fellipe
     //private  final String CSV_FILE_NAME = "C:\\Users\\zanat\\IdeaProjects\\spotify\\db\\playlists.txt";

    // Path luiz
    //private final String CSV_FILE_NAME = "/home/luizgustavoou/Documentos/projects/spotify/db/playlists.txt";

    private final ICSVApi csvApi;

    public CSVPlaylistRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVPlaylistRepositoryImpl() {
        this.csvApi = new CSVApiImpl(CSV_FILE_NAME);
    }

    public List<Playlist> readFile() {
        List<String[]> playlistResponse = this.csvApi.readFile();

        return playlistResponse.stream().map(playlistArray -> {
            UUID id = UUID.fromString(playlistArray[0]);
            String name = playlistArray[1];
            UUID userId = UUID.fromString(playlistArray[2]);


            return new Playlist(id, name, userId);
        }).toList();
    }

    public void writeFile(List<Playlist> playlists) {
        List<String[]> playlistsResponse = playlists.stream().map(playlist -> {
            UUID id = playlist.getId();
            String name = playlist.getName();
            UUID userId = playlist.getUserId();

            return new String[]{id.toString(), name, userId.toString()};
        }).toList();


        csvApi.writeFile(playlistsResponse);
    }
    @Override
    public List<Playlist> findAllPlaylistOfUser(UUID userId) {
        ArrayList<Playlist> playlists = new ArrayList<>(this.readFile());

        return playlists.stream().filter(pl -> pl.getUserId().equals(userId)).toList();
    }


    public static void main(String[] args) throws EntityNotFoundException, UnauthorizedException {
        //IPlaylistRepository playlistRepository = new CSVPlaylistRepositoryImpl();

        // Test findAll
        //List<Playlist> playlists = playlistRepository.findAll();
        //playlists.forEach(playlist -> {
        //    System.out.println(playlist.toString());
        //});

        // Test create
        //Playlist newPlaylist = new Playlist("JazzHoop with ambience + rain", UUID.fromString("12b2a592-722b-44db-ad94-3540658abeab")); // Playlist
        //System.out.println(newPlaylist.toString());
        //playlistRepository.create(newPlaylist);


//         Test findOneById
        //System.out.println(playlistRepository.findOneById(UUID.fromString("e0e0c95d-9453-4c2e-8940-2fe3bf090097")));

//         Test remove
        //playlistRepository.remove(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9"));


        // Test update
        //playlistRepository.update(UUID.fromString("0485b478-11e3-4039-bde7-98365492c473"), new Playlist("Nu metal",UUID.fromString("137b0fe7-bb20-441b-89c4-40106cce22ff")));

        // Teste findAllPlaylistOfUser
        //ArrayList<Playlist> testeAllplaylists = new ArrayList<>(playlistRepository.findAllPlaylistOfUser(UUID.fromString("12b2a592-722b-44db-ad94-3540658abeab")));
        //System.out.println(testeAllplaylists);
       }
}
