package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.stores.*;
import imd.ufrn.br.spotify.utils.PathViews;
import imd.ufrn.br.spotify.utils.ShowModal;
import imd.ufrn.br.spotify.utils.TitleViews;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Callback;


public class HomeControllerFXML implements Initializable {
    // váriaveis controllers
    SongController songController;
    PlaylistController playlistController;
    FolderController folderController;

    // váriaveis de stores
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;

    // Variáveis de tocar música
    private final CurrentPlaylist currentPlaylist;
    private final CurrentSong currentSong;
    private Media media;
    private MediaPlayer mediaPlayer;

    // variáveis para abrir o fs
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();

    // Variáveis da interface FXML
    @FXML
    private Text userFullName;
    @FXML
    private ListView<Playlist> listViewPlaylists;
    @FXML
    private ListView<Song> listViewSongs;
    @FXML
    private ProgressBar songProgressBar;
    private Timer timer;

    public HomeControllerFXML() {
        this.currentPlaylist = CurrentPlaylist.getInstance();
        this.currentSong = CurrentSong.getInstance();
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();

        this.songController = new SongController();
        this.playlistController = new PlaylistController();
        this.folderController = new FolderController();

    }

    // Funções da interface XML
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


        this.songController.create(name, file.getPath(), playlistId);

        System.out.println("Música adicionada a playlist " + playlistId);

        this.playlistsStore.updateAllPlaylistsOfUser(this.userStore.getId());
    }

    @FXML
    void addFolder(MouseEvent event) {
        if(this.hasNotSong()) return;

        UUID playlistId = this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId();

        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        this.folderController.create(folder.getAbsolutePath(), playlistId);

        System.out.println("Folder adicionado a playlist " + playlistId);
        this.playlistsStore.updateAllPlaylistsOfUser(this.userStore.getId());
    }

    @FXML
    void removePlaylist(MouseEvent event)  {
        if(this.hasNotPlaylist()) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta de confirmação de remoção de playlist");
            alert.setContentText("Você quer remover a playlist " + this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                this.playlistController.remove(playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId());
                this.playlistsStore.updateAllPlaylistsOfUser(userStore.getId());
            }


        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    void removeSong(MouseEvent event)  {
        if(this.hasNotSong()) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta de confirmação de remoção de música");
            alert.setContentText("Você quer remover a música " + this.songsStore.getSongs().get(this.currentSong.getIndex()).getName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                this.songController.remove(songsStore.getSongs().get(this.currentSong.getIndex()).getId());
                this.songsStore.updateAllSongsOfPlaylist(playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId());
            }

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    public void previousSong() {
        if(this.hasNotSong()) return;

        if(this.currentSong.getIndex() == 0) {
            this.updateIndexSong(songsStore.getSongs().size() -1);
            this.startMusicPlayback();
        }
        else {
            this.updateIndexSong(this.currentSong.getIndex() - 1);
            this.startMusicPlayback();
        }
    }
    @FXML
    public void nextSong() {
        if(this.hasNotSong()) return;

        this.updateIndexSong(this.currentSong.getIndex() + 1);
        this.startMusicPlayback();
    }


    @FXML
    public void playMedia() {
        if(this.hasNotSong()) return;

        this.startMusicPlayback();

    }

    @FXML
    public void pauseMedia() {
        if(this.mediaPlayer == null) return;

        if(this.mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            this.mediaPlayer.play();
        }else if(this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            this.mediaPlayer.pause();
        }
    }

    @FXML
    public void createPlaylist(MouseEvent event) throws IOException {
        ShowModal.getInstance().execute(songProgressBar, TitleViews.ADD_PLAYLIST_VIEW, PathViews.ADD_PLAYLIST_VIEW);
    }

    @FXML
    public void updatePlaylist(MouseEvent event) throws IOException {
        ShowModal.getInstance().execute(songProgressBar, TitleViews.UPDATE_PLAYLIST_VIEW, PathViews.UPDATE_PLAYLIST_VIEW);
    }

    @FXML
    void updateSong(MouseEvent event) throws IOException {
        ShowModal.getInstance().execute(songProgressBar, TitleViews.UPDATE_SONG_VIEW, PathViews.UPDATE_SONG_VIEW);
    }

    // Funções de lógica

    public boolean hasNotSong() {
        return this.songsStore.getSongs().isEmpty();
    }

    public boolean hasNotPlaylist() {
        return this.playlistsStore.getPlaylists().isEmpty();
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

    public void stopMedia() {
        if(this.mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) return;

        this.mediaPlayer.stop();
        this.cancelTimer();
    }

    public void startMusicPlayback() {
        Platform.runLater(() -> {
            if(this.mediaPlayer != null && this.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                this.stopMedia();
            }

            File file = new File(songsStore.getSongs().get(this.currentSong.getIndex()).getPath());
            media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            this.beginTimer();

            this.mediaPlayer.setOnEndOfMedia(this::nextSong);

        });

    }

    public void beginTimer() {

        timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current / end);

                if (current / end == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userFullName.setText(userStore.getFullName());
        listViewPlaylists.itemsProperty().bindBidirectional(playlistsStore.getObservablePlaylist());

        listViewSongs.itemsProperty().bindBidirectional(songsStore.getObservableSong());

        listViewPlaylists.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Playlist> call(ListView<Playlist> playlistListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Playlist item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

        listViewSongs.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Song> call(ListView<Song> songListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Song item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

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
