package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.CreateFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import imd.ufrn.br.spotify.services.song.ICreateSongUseCase;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.CreateSongUseCaseImpl;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;
import imd.ufrn.br.spotify.stores.PlaylistsStore;
import imd.ufrn.br.spotify.stores.SongsStore;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class HomeController implements Initializable {
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    private final ICreateSongUseCase createSongUseCase;
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final ICreateFolderUseCase createFolderUseCase;


    public HomeController(IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase, IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase, ICreateSongUseCase createSongUseCase, ICreatePlaylistUseCase createPlaylistUseCase, ICreateFolderUseCase createFolderUseCase) {
        this.getAllSongsOfPlaylistUseCase = getAllSongsOfPlaylistUseCase;
        this.findAllPlaylistOfUserUseCase = findAllPlaylistOfUserUseCase;
        this.createSongUseCase = createSongUseCase;
        this.createPlaylistUseCase = createPlaylistUseCase;
        this.createFolderUseCase = createFolderUseCase;
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();
    }

    public HomeController() {
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
        this.createSongUseCase = new CreateSongUseCaseImpl();
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
        this.createFolderUseCase = new CreateFolderUseCaseImpl();
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();
    }

    public void getAllPlaylistsOfUser(String userId) {
        playlistsStore.clear();

        playlistsStore.addPlaylists(findAllPlaylistOfUserUseCase.execute(UUID.fromString(userId)));
    }

    public void getAllSongsOfPlaylist(String playlistId) {
        songsStore.clear();

        songsStore.addSongs(getAllSongsOfPlaylistUseCase.execute(UUID.fromString(playlistId)));
    }

    public void createSong() {
        String strPlaylistId = "ab2acce0-30ca-4aa9-98cb-315781d0c2b9";
        String strNameSong = "musicateste";
        String strPathSong = "/musicateste.txt";

        Song song = new Song(strNameSong, strPathSong, UUID.fromString(strPlaylistId));

        this.createSongUseCase.execute(song);

    }

    public void createPlaylist() {
        String strNamePlaylist = "playlist teste";
        String userId = "12b2a592-722b-44db-ad94-3540658abeab";

        Playlist playlist = new Playlist(strNamePlaylist, UUID.fromString(userId));

        this.createPlaylistUseCase.execute(playlist);

    }

    public void createFolder() {
        String strPlaylistId = "ab2acce0-30ca-4aa9-98cb-315781d0c2b9";
        String strPathFolder = "/folderteste/";

        Folder folder = new Folder(strPathFolder, UUID.fromString(strPlaylistId));

        this.createFolderUseCase.execute(folder);


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        User user = userStore.getUser();
//        if (user != null) {
//            this.getAllPlaylistsOfUser(user.getId().toString());
//
//
//            if(!playlistsStore.getPlaylists().isEmpty()) {
//                this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(0).getId().toString());
//                System.out.println(playlistsStore.getPlaylists());
//                System.out.println(songsStore.getSongs());
//
//            }
//
//        } else {
//            System.out.println("User is null");
//        }
    }

    public static void main(String[] args) {
        HomeController homeController = new HomeController();

//        homeController.createSong();
//        homeController.createPlaylist();
        homeController.createFolder();
    }
}
