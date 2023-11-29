package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.entities.User;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
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

    //
    FileChooser musicFileChooser = new FileChooser();



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

        List<Song> newSongs = getAllSongsOfPlaylistUseCase.execute(UUID.fromString(playlistId));

        songsStore.addSongs(newSongs);
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

    //


    @FXML
    void loadFileMusic(MouseEvent event) {
        // TODO: Selecionar o playlistId atual do usuário
        String plalistId = "ab2acce0-30ca-4aa9-98cb-315781d0c2b9";
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos MP3 (*.mp3)", "*.mp3");
        musicFileChooser.getExtensionFilters().add(extFilter);
        File file = musicFileChooser.showOpenDialog(new Stage());

        if(file == null) return;

        Song newSong = new Song(file.getName(), file.getPath(), UUID.fromString(plalistId));

        createSongUseCase.execute(newSong);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static void main(String[] args) {
        HomeController homeController = new HomeController();

//        homeController.createSong();
//        homeController.createPlaylist();
//        homeController.createFolder();
    }
}
