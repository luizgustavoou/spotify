package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.CreateFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.ICreatePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.IRemovePlaylistUseCase;
import imd.ufrn.br.spotify.services.playlist.impl.CreatePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.impl.RemovePlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.song.ICreateSongUseCase;
import imd.ufrn.br.spotify.services.song.IRemoveSongUseCase;
import imd.ufrn.br.spotify.services.song.impl.CreateSongUseCaseImpl;
import imd.ufrn.br.spotify.services.song.impl.RemoveSongUseCaseImpl;
import imd.ufrn.br.spotify.stores.*;
import imd.ufrn.br.spotify.utils.PathViews;
import imd.ufrn.br.spotify.utils.ShowModal;
import imd.ufrn.br.spotify.utils.TitleViews;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class HomeController implements Initializable {
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;
    private final ICreateSongUseCase createSongUseCase;
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final ICreateFolderUseCase createFolderUseCase;
    private final IRemovePlaylistUseCase removePlaylistUseCase;
    private final IRemoveSongUseCase removeSongUseCase;


    // Variáveis de tocar música
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    private CurrentPlaylist currentPlaylist;
    private CurrentSong currentSong;
    private Media media;
    private MediaPlayer mediaPlayer;
    private boolean running;

    // Variáveis da interface
    @FXML
    private ListView<Playlist> listViewPlaylists;
    @FXML
    private ListView<Song> listViewSongs;
    @FXML
    private ProgressBar songProgressBar;
    private Timer timer;
    private TimerTask task;

    @FXML
    private TextField playlistName;



    public HomeController() {
        this.currentPlaylist = CurrentPlaylist.getInstance();
        this.currentSong = CurrentSong.getInstance();
        this.createSongUseCase = new CreateSongUseCaseImpl();
        this.createPlaylistUseCase = new CreatePlaylistUseCaseImpl();
        this.createFolderUseCase = new CreateFolderUseCaseImpl();
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();
        this.removePlaylistUseCase = new RemovePlaylistUseCaseImpl();
        this.removeSongUseCase = new RemoveSongUseCaseImpl();
    }

    @FXML
    public void addPlaylist() {
        String strNamePlaylist = playlistName.getText();

        if(strNamePlaylist.isEmpty()) {
            System.out.println("Nome da playlist vázia!");
            return;
        }

        Playlist playlist = new Playlist(strNamePlaylist, userStore.getId());

        this.createPlaylistUseCase.execute(playlist);

        this.playlistsStore.updateAllPlaylistsOfUser(userStore.getId());

    }

    @FXML
    void addSong(MouseEvent event) {
        UUID playlistId = this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId();
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

        this.playlistsStore.updateAllPlaylistsOfUser(this.userStore.getId());
    }

    @FXML
    void addFolder(MouseEvent event) {
        if(this.currentPlaylist.getIndex() < 0) return;
        // TODO: Selecionar o playlistId atual do usuário
        UUID playlistId = this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId();

        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        Folder newFolder = new Folder(folder.getAbsolutePath(), playlistId);

        createFolderUseCase.execute(newFolder);

        System.out.println("Folder adicionado a playlist " + playlistId);
        this.playlistsStore.updateAllPlaylistsOfUser(this.userStore.getId());
    }

    @FXML
    void removePlaylist(MouseEvent event)  {
        if(this.currentPlaylist.getIndex() < 0) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta de confirmação de remoção de playlist");
            alert.setContentText("Você quer remover a playlist " + this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                this.removePlaylistUseCase.execute(playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId());
                this.playlistsStore.updateAllPlaylistsOfUser(userStore.getId());
            }


        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void removeSong(MouseEvent event)  {
        if(this.currentSong.getIndex() < 0) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta de confirmação de remoção de música");
            alert.setContentText("Você quer remover a música " + this.songsStore.getSongs().get(this.currentSong.getIndex()).getName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                this.removeSongUseCase.execute(songsStore.getSongs().get(this.currentSong.getIndex()).getId());
                this.songsStore.updateAllSongsOfPlaylist(playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId());
            }

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public void updateIndexPlaylist(int index) {
        Platform.runLater(() -> {
            if(playlistsStore.getPlaylists().isEmpty()) {
                this.currentPlaylist.setIndex(-1);
                this.listViewPlaylists.getSelectionModel().select(-1);
                return;
            }

            int newIndex = index % playlistsStore.getPlaylists().size();
            this.currentPlaylist.setIndex(newIndex);
            this.listViewPlaylists.getSelectionModel().select(newIndex);
        });
    }

    public void updateIndexSong(int index) {
        Platform.runLater(() -> {
            if(songsStore.getSongs().isEmpty()) {
                this.currentSong.setIndex(-1);
                this.listViewSongs.getSelectionModel().select(-1);
                return;

            }

            int newIndex = index % songsStore.getSongs().size();

            this.currentSong.setIndex(newIndex);

            this.listViewSongs.getSelectionModel().select(newIndex);
        });

    }

    @FXML
    public void previousSong() {
        if(this.currentSong.getIndex() == -1) return;
        if(this.currentSong.getIndex() == 0) {
            this.stopMusic();
            this.updateIndexSong(songsStore.getSongs().size() -1);
            this.playMusic();
        }
        else {
            this.stopMusic();
            this.updateIndexSong(this.currentSong.getIndex() - 1);
            this.playMusic();
        }
    }
    @FXML
    public void nextSong() {
        if(this.currentSong.getIndex() == -1) return;
        this.stopMusic();
        this.updateIndexSong(this.currentSong.getIndex() + 1);
        this.playMusic();
    }


    @FXML
    public void playMedia() {
        if(this.currentSong.getIndex() == -1) return;
        if(this.running == true) {
            this.stopMusic();
        }
        else {
            this.playMusic();
        }
    }

    public void stopMusic() {
        if(this.running == false) return;
        this.running = false;
        this.mediaPlayer.stop();
        this.cancelTimer();
        return;
    }

    public void playMusic() {
        this.running = true;
        File file = new File(songsStore.getSongs().get(this.currentSong.getIndex()).getPath());
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        this.beginTimer();
    }

    public void beginTimer() {

        timer = new Timer();

        task = new TimerTask() {

            public void run() {
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current/end);

                if(current/end == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }

    @FXML
    public void createPlaylist(MouseEvent event) throws IOException {
        ShowModal.getInstance().execute(playlistName, TitleViews.ADD_PLAYLIST_VIEW, PathViews.ADD_PLAYLIST_VIEW);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPlaylists.itemsProperty().bindBidirectional(playlistsStore.getObservablePlaylist());

        listViewSongs.itemsProperty().bindBidirectional(songsStore.getObservableSong());

        listViewPlaylists.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPlaylist, newPlaylist) -> {
            if(newPlaylist == null) {
                this.updateIndexPlaylist(-1);
            }else {
            this.updateIndexPlaylist(listViewPlaylists.getSelectionModel().getSelectedIndex());
            this.songsStore.updateAllSongsOfPlaylist(newPlaylist.getId());
            this.updateIndexSong(0);
            }
        });

        listViewSongs.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSong, newSong) -> {
            if(newSong == null) {
                this.updateIndexSong(-1);

            }else {
                this.updateIndexSong(listViewSongs.getSelectionModel().getSelectedIndex());

            }

        });


        playlistsStore.addListener((observableValue, oldPlaylists, newPlaylists) -> {
            if(newPlaylists.isEmpty()) {
                this.updateIndexPlaylist(-1);
                return;
            }

            this.updateIndexPlaylist(0);
        });

        songsStore.addListener((observableValue, oldSongs, newSongs) -> {
            if(newSongs.isEmpty()) {
                this.updateIndexSong(-1);
                return;
            }

            this.updateIndexSong(0);
        });

        this.playlistsStore.updateAllPlaylistsOfUser(userStore.getId());


    }
}
