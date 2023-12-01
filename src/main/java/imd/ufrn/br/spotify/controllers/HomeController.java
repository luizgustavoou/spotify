package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.services.folder.IRemoveFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.CreateFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.folder.impl.RemoveFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.IFindAllPlaylistOfUserUseCase;
import imd.ufrn.br.spotify.services.playlist.IRemovePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.impl.FindAllPlaylistOfUserUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.impl.RemovePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.song.ICreateSongUseCase;
import imd.ufrn.br.spotify.services.song.IGetAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.IRemoveSongUseCase;
import imd.ufrn.br.spotify.services.song.impl.CreateSongUseCaseImpl;
import imd.ufrn.br.spotify.services.song.impl.GetAllSongsOfPlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.song.impl.RemoveSongUseCaseImpl;
import imd.ufrn.br.spotify.stores.PlaylistsStore;
import imd.ufrn.br.spotify.stores.SongsStore;
import imd.ufrn.br.spotify.stores.UserStore;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.TextFieldListCell;
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
import javafx.util.Callback;
import javafx.util.StringConverter;


public class HomeController implements Initializable {
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    private final ICreateSongUseCase createSongUseCase;
    private final IRemoveSongUseCase removeSongUseCase;
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final IRemovePlaylistUseCase removePlaylistUseCase;
    private final ICreateFolderUseCase createFolderUseCase;
    private final IRemoveFolderUseCase removeFolderUseCase;

    // Variáveis de tocar música
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    private final SimpleIntegerProperty currentPlaylist = new SimpleIntegerProperty(-1);
    private final SimpleIntegerProperty currentSong = new SimpleIntegerProperty(-1);
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
        this.removeSongUseCase = new RemoveSongUseCaseImpl();
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
        this.removePlaylistUseCase = new RemovePlaylistUseCaseImpl();
        this.createFolderUseCase = new CreateFolderUseCaseImpl();
        this.removeFolderUseCase = new RemoveFolderUseCaseImpl();
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

        this.getAllPlaylistsOfUser(userStore.getUser().getId().toString());

    }

    @FXML
    void addSong(MouseEvent event) {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos MP3 (*.mp3)", "*.mp3");
        musicFileChooser.getExtensionFilters().add(extFilter);
        musicFileChooser.setTitle("Escolha uma música");
        File file = musicFileChooser.showOpenDialog(new Stage());

        if(file == null) return;

        String[] parts = file.getName().split("\\.");
        String name = parts[0];
        String extension = parts[1];

        Song newSong = new Song(name, file.getPath(), playlistsStore.getPlaylists().get(currentPlaylist.get()).getId());

        createSongUseCase.execute(newSong);

        System.out.println("Música adicionada a playlist " + playlistsStore.getPlaylists().get(currentPlaylist.get()));

        this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(currentPlaylist.get()).getId().toString());

    }

    @FXML
    void addFolder(MouseEvent event) {
        // TODO: Selecionar o playlistId atual do usuário
        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        Folder newFolder = new Folder(folder.getAbsolutePath(), playlistsStore.getPlaylists().get(currentPlaylist.get()).getId());

        createFolderUseCase.execute(newFolder);

        System.out.println("Folder adicionado a playlist " + playlistsStore.getPlaylists().get(currentPlaylist.get()));
        this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(currentPlaylist.get()).getId().toString());


    }

    void removePlaylist() throws EntityNotFoundException {
        String playlistId = "2fee7c36-cc4a-40fa-bb85-958904e1fca3";
        this.removePlaylistUseCase.execute(UUID.fromString(playlistId));
    }

    void removeSong() throws EntityNotFoundException {
        String songId = "7f32a9cb-23b9-47f2-b83d-b02af47d5969";
        this.removeSongUseCase.execute(UUID.fromString(songId));
    }

    void removeFolder() throws EntityNotFoundException {
        String folderId = "1a961bb4-6378-43db-82cd-29a8d199e5de";
        this.removeFolderUseCase.execute(UUID.fromString(folderId));
    }

    @FXML
    public void nextPlaylist() {
//        this.mediaStop();
        this.updateCurrentPlaylist(currentPlaylist.get() + 1);
        listViewPlaylists.getSelectionModel().select(currentPlaylist.get());
    }

    @FXML
    public void nextSong() {
        this.updateCurrentSong(currentSong.get() + 1);
        listViewSongs.getSelectionModel().select(currentSong.get());

    }

    private void updateCurrentPlaylist(int index) {
        if(playlistsStore.getPlaylists().isEmpty()) return;

        currentPlaylist.set(index % playlistsStore.getPlaylists().size());

    }

    private void updateCurrentSong(int index) {
        if(songsStore.getSongs().isEmpty()) return;
        currentSong.set(index % songsStore.getSongs().size());
    }

    public void mediaStop() {
        if(!this.running || this.mediaPlayer == null) return;

        this.running = false;
        mediaPlayer.stop();
    }

    @FXML
    public void playMedia() {
        int indexSong = currentSong.get();

        System.out.println(indexSong);

        if(songsStore.getSongs().isEmpty()) return;

        if(!this.running) this.running = true;

        this.mediaStop();

        File file = new File(songsStore.getSongs().get(indexSong).getPath());

        media = new Media(file.toURI().toString());

        //beginTimer();
        //changeSpeed(null);
        //mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);

        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();

        System.out.println("tocando musica: " + songsStore.getSongs().get(indexSong));
    }

    public void loadedNewPlaylists() {
        this.updateCurrentPlaylist(0);
    }

    public void loadedNewSongs() {
        this.updateCurrentSong(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPlaylists.itemsProperty().bindBidirectional(playlistsStore.getObservablePlaylist());

        listViewSongs.itemsProperty().bindBidirectional(songsStore.getObservableSong());

        listViewPlaylists.getSelectionModel().selectedItemProperty().addListener((observableValue, playlist, t1) -> {
            int playlistIndex = listViewPlaylists.getSelectionModel().getSelectedIndex();

            this.updateCurrentPlaylist(playlistIndex);

        });

        listViewSongs.getSelectionModel().selectedItemProperty().addListener((observableValue, playlist, t1) -> {
            int songIndex = listViewSongs.getSelectionModel().getSelectedIndex();

            this.updateCurrentSong(songIndex);
        });

        // Lógica de tocar música
        playlistsStore.addListener((observableValue, oldPlaylists, newPlaylists) -> {
            System.out.println("observable de playlistsStore");
            if(newPlaylists.isEmpty()) return;

            this.loadedNewPlaylists();
            this.getAllSongsOfPlaylist(newPlaylists.get(currentPlaylist.get()).getId().toString());
        });

        songsStore.addListener((observableValue, oldSongs, newSongs) -> {
            System.out.println("observable de songsStore");
            if(newSongs.isEmpty()) return;

            this.loadedNewSongs();
        });

        currentPlaylist.addListener((observableValue, oldCurrentPlaylist, newCurrentPlaylist) -> {
            System.out.println("observable de currentPlaylist");
            int indexPlaylist = newCurrentPlaylist.intValue();

            this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(indexPlaylist).getId().toString());
        });

        currentSong.addListener((observableValue, oldCurrentSong, newCurrentSong) -> {
            System.out.println("observable de currentSong");
            if(songsStore.getSongs().isEmpty()) return;
        });

        this.getAllPlaylistsOfUser(userStore.getUser().getId().toString());

        listViewPlaylists.getSelectionModel().select(0);
        listViewSongs.getSelectionModel().select(0);
    }

    @FXML
    void testeClick(MouseEvent event) {
        this.getAllPlaylistsOfUser(userStore.getUser().getId().toString());
    }

    public static void main(String[] args) throws EntityNotFoundException {

    }
}
