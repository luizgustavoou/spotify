package imd.ufrn.br.spotify.front.controllers;

import imd.ufrn.br.spotify.front.lib.Player;
import imd.ufrn.br.spotify.front.lib.PlayerImpl;
import imd.ufrn.br.spotify.back.entities.Playlist;
import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.front.services.IFolderService;
import imd.ufrn.br.spotify.front.services.IPlaylistService;
import imd.ufrn.br.spotify.front.services.ISongService;
import imd.ufrn.br.spotify.front.services.impl.FolderServiceImpl;
import imd.ufrn.br.spotify.front.services.impl.PlaylistServiceImpl;
import imd.ufrn.br.spotify.front.services.impl.SongServiceImpl;
import imd.ufrn.br.spotify.front.stores.*;
import imd.ufrn.br.spotify.front.utils.Navigator;
import imd.ufrn.br.spotify.front.utils.PathViews;
import imd.ufrn.br.spotify.front.utils.ShowModal;
import imd.ufrn.br.spotify.front.utils.TitleViews;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.util.Callback;


public class VipHomeControllerFXML implements Initializable {
    private final Player player;

    // váriaveis controllers
    private final ISongService songService;
    private final IPlaylistService playlistService;
    private final IFolderService folderService;

    // váriaveis de stores
    private final PlaylistsStore playlistsStore;
    private final SongsStore songsStore;
    private final UserStore userStore;

    // Variáveis de tocar música
    private final CurrentPlaylist currentPlaylist;
    private final CurrentSong currentSong;

    // variáveis para abrir o fs
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();

    // Variáveis da interface FXML
    @FXML
    private Text userFullName;
    @FXML
    private ProgressBar songProgressBar;
    @FXML
    private TableView<Song> tableSongs;
    @FXML
    private TableColumn<Song, String> songNameCol;
    @FXML
    private TableColumn<Song, String> songActionCol;
    @FXML
    private TableView<Playlist> tablePlaylists;
    @FXML
    private TableColumn<Playlist, String> playlistNameCol;
    @FXML
    private TableColumn<Playlist, String> playlistActionCol;

    // Váriavel para atualizar o tempo da música na interface
    private Timer timer;

    public VipHomeControllerFXML() {
        this.currentPlaylist = CurrentPlaylist.getInstance();
        this.currentSong = CurrentSong.getInstance();
        this.userStore = UserStore.getInstance();
        this.playlistsStore = PlaylistsStore.getInstance();
        this.songsStore = SongsStore.getInstance();

        this.songService = new SongServiceImpl();
        this.playlistService = new PlaylistServiceImpl();
        this.folderService = new FolderServiceImpl();

        this.player = new PlayerImpl(this::beginTimer, this::cancelTimer);

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


        this.songService.create(name, file.getPath(), playlistId);

        System.out.println("Música adicionada a playlist " + playlistId);

        this.playlistsStore.updateAllPlaylistsOfUser(this.userStore.getId());
    }

    @FXML
    void addFolder(MouseEvent event) {
        if(this.hasNotPlaylist()) return;

        UUID playlistId = this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId();

        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        this.folderService.create(folder.getAbsolutePath(), playlistId);

        System.out.println("Folder adicionado a playlist " + playlistId);
        this.playlistsStore.updateAllPlaylistsOfUser(this.userStore.getId());
    }

    @FXML
    public void previousSong() {
        if(this.hasNotSong()) return;

        this.player.previousSong();
        if(this.currentSong.getIndex() == 0) {
            this.updateIndexSong(songsStore.getSongs().size() -1);
        }
        else {
            this.updateIndexSong(this.currentSong.getIndex() - 1);
        }
    }
    @FXML
    public void nextSong() {
        if(this.hasNotSong()) return;

        this.player.nextSong();
        this.updateIndexSong(this.currentSong.getIndex() + 1);
    }

    @FXML
    public void playMedia() {
        this.player.playMedia();
    }

    @FXML
    public void pauseMedia() {
        this.player.pauseMedia();
    }

    @FXML
    public void logout() throws IOException {
        this.userStore.setUser(null);
        Stage stage = Navigator.getInstance().configure(userFullName, TitleViews.LOGIN_VIEW, PathViews.LOGIN_VIEW);
        Navigator.getInstance().execute(stage);
    }

    public void createPlaylist() throws IOException {
        ShowModal showModal = new ShowModal();

        Stage dialog = showModal.configure(songProgressBar, TitleViews.ADD_PLAYLIST_VIEW, PathViews.ADD_PLAYLIST_VIEW);

        showModal.execute(dialog);
    }

    void removePlaylist()  {
        if(this.hasNotPlaylist()) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta de confirmação de remoção de playlist");
            alert.setContentText("Você quer remover a playlist " + this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                this.playlistService.remove(playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId());
                this.playlistsStore.updateAllPlaylistsOfUser(userStore.getId());
            }


        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }


    public void updatePlaylist() throws IOException {
        if(hasNotPlaylist()) return;

        ShowModal showModal = new ShowModal();

        Stage dialog = showModal.configure(songProgressBar, TitleViews.UPDATE_PLAYLIST_VIEW, PathViews.UPDATE_PLAYLIST_VIEW);

        PlaylistEditControllerFXML controller =  showModal.getFxmlLoader().getController();

        controller.setPlaylist(this.playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()));

        showModal.execute(dialog);
    }
    void removeSong()  {
        if(this.hasNotSong()) return;

        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta de confirmação de remoção de música");
            alert.setContentText("Você quer remover a música " + this.songsStore.getSongs().get(this.currentSong.getIndex()).getName() + " ?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                this.songService.remove(songsStore.getSongs().get(this.currentSong.getIndex()).getId());
                this.songsStore.updateAllSongsOfPlaylist(playlistsStore.getPlaylists().get(this.currentPlaylist.getIndex()).getId());
            }

        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void updateSong() throws IOException {
        if(hasNotSong()) return;

        ShowModal showModal = new ShowModal();

        Stage dialog = showModal.configure(songProgressBar, TitleViews.UPDATE_SONG_VIEW, PathViews.UPDATE_SONG_VIEW);
        SongEditControllerFXML controller = showModal.getFxmlLoader().getController();

        controller.setSong(this.songsStore.getSongs().get(this.currentSong.getIndex()));

        showModal.execute(dialog);
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
            if(hasNotPlaylist()) {
                this.currentPlaylist.setIndex(-1);
                this.tablePlaylists.getSelectionModel().select(-1);
                return;
            }

            int newIndex = index % playlistsStore.getPlaylists().size();
            this.currentPlaylist.setIndex(newIndex);
            this.tablePlaylists.getSelectionModel().select(newIndex);
        });
    }

    public void updateIndexSong(int index) {
        if(hasNotSong()) {
            this.currentSong.setIndex(-1);
            this.tableSongs.getSelectionModel().select(-1);
            return;

        }

        int newIndex = index % songsStore.getSongs().size();

        this.currentSong.setIndex(newIndex);

        this.tableSongs.getSelectionModel().select(newIndex);
    }

    public void beginTimer() {

        timer = new Timer();

        TimerTask task = new TimerTask() {

            public void run() {
                double current = player.getCurrentTime();
                double duration = player.getDuration();
                songProgressBar.setProgress(current / duration);

                if (current / duration == 1) {

                    cancelTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }

    private void loadPlaylists() {
        playlistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        Callback<TableColumn<Playlist, String>, TableCell<Playlist, String>> cellFactory = (TableColumn<Playlist, String> param) -> {
            // make cell containing buttons
            final TableCell<Playlist, String> cell = new TableCell<Playlist, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Text deleteIcon = new Text();
                        deleteIcon.setText("Remover");

                        Text editIcon = new Text();
                        editIcon.setText("Editar");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                          removePlaylist();
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                updatePlaylist();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };

        this.playlistActionCol.setCellFactory(cellFactory);
        this.tablePlaylists.setItems(this.playlistsStore.getPlaylists());
    }

    private void loadSongs() {
        songNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));


        Callback<TableColumn<Song, String>, TableCell<Song, String>> cellFactory = (TableColumn<Song, String> param) -> {
            // make cell containing buttons
            final TableCell<Song, String> cell = new TableCell<Song, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Text deleteIcon = new Text();
                        deleteIcon.setText("Remover");

                        Text editIcon = new Text();
                        editIcon.setText("Editar");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            removeSong();
                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                updateSong();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }
            };
            return cell;
        };

        songActionCol.setCellFactory(cellFactory);

        this.tableSongs.setItems(this.songsStore.getSongs());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userFullName.setText(userStore.getFullName());

        // Escutar mudança de playlist na GUI
        this.tablePlaylists.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPlaylist, newPlaylist) -> {
            if(newPlaylist == null) {
                this.updateIndexPlaylist(-1);
            }else {
            this.updateIndexPlaylist(this.tablePlaylists.getSelectionModel().getSelectedIndex());
            this.songsStore.updateAllSongsOfPlaylist(newPlaylist.getId());
            this.updateIndexSong(0);
            this.player.selectSong(0);
            }
        });

        // Escutar mudança de Song na GUI
        this.tableSongs.getSelectionModel().selectedItemProperty().addListener((observableValue, oldSong, newSong) -> {
            if(newSong == null) {
                this.updateIndexSong(-1);
                this.player.selectSong(0);
            }else {
                this.updateIndexSong(this.tableSongs.getSelectionModel().getSelectedIndex());
                this.player.selectSong(this.tableSongs.getSelectionModel().getSelectedIndex());
            }
        });


        // Escutar mudança das Playlists
        playlistsStore.addListener((observableValue, oldPlaylists, newPlaylists) -> {
            if(newPlaylists.isEmpty()) {
                this.updateIndexPlaylist(-1);
                return;
            }

            this.updateIndexPlaylist(0);
        });

        // Escutar mudança dos Songs
        songsStore.addListener((observableValue, oldSongs, newSongs) -> {
            this.player.setSongs(newSongs);

            if(newSongs.isEmpty()) {
                this.updateIndexSong(-1);
                this.player.selectSong(-1);
                return;
            }

            this.updateIndexSong(0);
            this.player.selectSong(0);
        });

        // Carregar todas as playlists do usuário
        this.playlistsStore.updateAllPlaylistsOfUser(userStore.getId());

        // Setar songs no player
        this.player.setSongs(songsStore.getSongs());

        // Carregar tabela de song
        this.loadSongs();


        // Carregar tabela de playlist
        this.loadPlaylists();

    }
}
