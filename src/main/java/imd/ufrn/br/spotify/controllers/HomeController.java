package imd.ufrn.br.spotify.controllers;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Playlist;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.services.folder.ICreateFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.CreateFolderUseCaseImpl;
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
import javafx.scene.control.ListView;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.UUID;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ProgressBar;
import java.util.TimerTask;


public class HomeController implements Initializable {
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;
    private final IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase;
    private final IFindAllPlaylistOfUserUseCase findAllPlaylistOfUserUseCase;
    private final ICreateSongUseCase createSongUseCase;
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final ICreateFolderUseCase createFolderUseCase;
    private final IRemovePlaylistUseCase removePlaylistUseCase;
    private final IRemoveSongUseCase removeSongUseCase;


    // Variáveis de tocar música
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    private int currentPlaylist = -1;
    private int currentSong = -1;
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
        this.getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();
        this.findAllPlaylistOfUserUseCase = new FindAllPlaylistOfUserUseCaseImpl();
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

        Playlist playlist = new Playlist(strNamePlaylist, userStore.getUser().getId());

        this.createPlaylistUseCase.execute(playlist);

        this.getAllPlaylistsOfUser(userStore.getUser().getId());

    }

    @FXML
    void addSong(MouseEvent event) {
        UUID playlistId = this.playlistsStore.getPlaylists().get(this.currentPlaylist).getId();
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

        this.getAllPlaylistsOfUser(this.userStore.getUser().getId());
    }

    @FXML
    void addFolder(MouseEvent event) {
        if(this.currentPlaylist < 0) return;
        // TODO: Selecionar o playlistId atual do usuário
        UUID playlistId = this.playlistsStore.getPlaylists().get(this.currentPlaylist).getId();

        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        Folder newFolder = new Folder(folder.getAbsolutePath(), playlistId);

        createFolderUseCase.execute(newFolder);

        System.out.println("Folder adicionado a playlist " + playlistId);
        this.getAllPlaylistsOfUser(this.userStore.getUser().getId());
    }

    @FXML
    void removePlaylist(MouseEvent event)  {
        if(this.currentPlaylist < 0) return;

        try {
            this.removePlaylistUseCase.execute(playlistsStore.getPlaylists().get(currentPlaylist).getId());
            this.getAllPlaylistsOfUser(userStore.getUser().getId());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    void removeSong(MouseEvent event)  {
        if(this.currentSong < 0) return;

        try {
            this.removeSongUseCase.execute(songsStore.getSongs().get(currentSong).getId());
            this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(currentPlaylist).getId());
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateIndexPlaylist(int index) {
        if(playlistsStore.getPlaylists().isEmpty()) {
            this.currentPlaylist = -1;
            this.listViewPlaylists.getSelectionModel().select(-1);
            return;
        }

        int newIndex = index % playlistsStore.getPlaylists().size();
        this.currentPlaylist = newIndex;
        this.listViewPlaylists.getSelectionModel().select(newIndex);

    }

    public void updateIndexSong(int index) {
        if(songsStore.getSongs().isEmpty()) {
            this.currentSong = -1;
            this.listViewSongs.getSelectionModel().select(-1);
            return;

        }

        int newIndex = index % songsStore.getSongs().size();

        this.currentSong = newIndex;
        this.listViewSongs.getSelectionModel().select(newIndex);
    }

    public void getAllPlaylistsOfUser(UUID userId) {
        playlistsStore.clear();
        playlistsStore.addPlaylists(findAllPlaylistOfUserUseCase.execute(userId));

        if(playlistsStore.getPlaylists().isEmpty()) {
            this.updateIndexPlaylist(-1);
            return;
        }

        this.updateIndexPlaylist(0);

        this.getAllSongsOfPlaylist(this.playlistsStore.getPlaylists().get(currentPlaylist).getId());

    }

    public void getAllSongsOfPlaylist(UUID playlistId) {
        songsStore.clear();
        songsStore.addSongs(this.getAllSongsOfPlaylistUseCase.execute(playlistId));

        if(songsStore.getSongs().isEmpty()) {
            this.updateIndexSong(-1);
            return;
        }

        this.updateIndexSong(0);
    }

    @FXML
    public void previousSong() {
        if(this.currentSong == -1) return;
        if(this.currentSong == 0) {
            this.stopMusic();
            this.updateIndexSong(songsStore.getSongs().size() -1);
            this.playMusic();
        }
        else {
            this.stopMusic();
            this.updateIndexSong(this.currentSong - 1);
            this.playMusic();
        }
    }
    @FXML
    public void nextSong() {
        if(this.currentSong == -1) return;
        this.stopMusic();
        this.updateIndexSong(this.currentSong + 1);
        this.playMusic();
    }


    @FXML
    public void playMedia() {
        if(this.currentSong == -1) return;
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
        File file = new File(songsStore.getSongs().get(currentSong).getPath());
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPlaylists.itemsProperty().bindBidirectional(playlistsStore.getObservablePlaylist());

        listViewSongs.itemsProperty().bindBidirectional(songsStore.getObservableSong());

        listViewPlaylists.getSelectionModel().selectedItemProperty().addListener((observableValue, playlist, t1) -> {
            if(t1 == null) {
                this.updateIndexPlaylist(-1);
            }else {
            this.updateIndexPlaylist(listViewPlaylists.getSelectionModel().getSelectedIndex());
            this.getAllSongsOfPlaylist(t1.getId());
            this.updateIndexSong(0);
            }
        });

        listViewSongs.getSelectionModel().selectedItemProperty().addListener((observableValue, playlist, t1) -> {
            if(t1 == null) {
                this.updateIndexSong(-1);

            }else {
                this.updateIndexSong(listViewSongs.getSelectionModel().getSelectedIndex());

            }

        });


//        playlistsStore.addListener((observableValue, oldPlaylists, newPlaylists) -> {
//
//        });
//
//        songsStore.addListener((observableValue, oldSongs, newSongs) -> {
//
//        });

        this.getAllPlaylistsOfUser(userStore.getUser().getId());


    }
}
