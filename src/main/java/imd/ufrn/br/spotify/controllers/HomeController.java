package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;



public class HomeController implements Initializable {
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    private final ICreateSongUseCase createSongUseCase;
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final ICreateFolderUseCase createFolderUseCase;


    // Variáveis de tocar música
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    private final SimpleIntegerProperty currentPlaylist = new SimpleIntegerProperty();
    private final SimpleIntegerProperty currentSong = new SimpleIntegerProperty();
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean running;

    // Variáveis da interface
    @FXML
    private ListView<Playlist> listViewPlaylists;
    @FXML
    private ListView<Song> listViewSongs;



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

    @FXML
    public void addPlaylist() {
        String strNamePlaylist = "playlist teste";

        Playlist playlist = new Playlist(strNamePlaylist, userStore.getUser().getId());

        this.createPlaylistUseCase.execute(playlist);

    }

    @FXML
    void addSong(MouseEvent event) {
        UUID playlistId = UUID.fromString("a71b1741-ff6b-4ed3-b9e2-0c798ea679e2");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos MP3 (*.mp3)", "*.mp3");
        musicFileChooser.getExtensionFilters().add(extFilter);
        musicFileChooser.setTitle("Escolha uma música");
        File file = musicFileChooser.showOpenDialog(new Stage());

        if(file == null) return;

        String[] parts = file.getName().split("\\.");
        String name = parts[0];
        String extension = parts[1];

        Song newSong = new Song(name, file.getPath(), playlistId);

        createSongUseCase.execute(newSong);

        System.out.println("Música adicionada a playlist " + playlistId);

    }

    @FXML
    void addFolder(MouseEvent event) {
        // TODO: Selecionar o playlistId atual do usuário
        UUID playlistId = UUID.fromString("a71b1741-ff6b-4ed3-b9e2-0c798ea679e2");

        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        Folder newFolder = new Folder(folder.getAbsolutePath(), playlistId);

        createFolderUseCase.execute(newFolder);

        System.out.println("Folder adicionado a playlist " + playlistId);
    }

    @FXML
    public void previousSong() {

    }
    @FXML
    public void nextSong() {

    }







    @FXML
    public void playMedia() {
//        File file = new File(songsStore.getSongs().get(indexSong).getPath());

//        media = new Media(file.toURI().toString());


//        mediaPlayer = new MediaPlayer(media);

//        mediaPlayer.play();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPlaylists.itemsProperty().bindBidirectional(playlistsStore.getObservablePlaylist());

        listViewSongs.itemsProperty().bindBidirectional(songsStore.getObservableSong());

        listViewPlaylists.getSelectionModel().selectedItemProperty().addListener((observableValue, playlist, t1) -> {


        });

        listViewSongs.getSelectionModel().selectedItemProperty().addListener((observableValue, playlist, t1) -> {

        });


        playlistsStore.addListener((observableValue, oldPlaylists, newPlaylists) -> {

        });

        songsStore.addListener((observableValue, oldSongs, newSongs) -> {

        });

        currentPlaylist.addListener((observableValue, oldCurrentPlaylist, newCurrentPlaylist) -> {

        });

        currentSong.addListener((observableValue, oldCurrentSong, newCurrentSong) -> {

        });



    }

    public static void main(String[] args) throws EntityNotFoundException {

    }
}
