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
    private final IRemoveSongUseCase removeSongUseCase;
    private final ICreatePlaylistUseCase createPlaylistUseCase;
    private final IRemovePlaylistUseCase removePlaylistUseCase;
    private final ICreateFolderUseCase createFolderUseCase;
    private final IRemoveFolderUseCase removeFolderUseCase;

    //
    FileChooser musicFileChooser = new FileChooser();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    private final SimpleIntegerProperty currentPlaylist = new SimpleIntegerProperty();
    private final SimpleIntegerProperty currentSong = new SimpleIntegerProperty();
    private Media media;
    private MediaPlayer mediaPlayer;


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

    public void addPlaylist() {
        String strNamePlaylist = "playlist teste";
        String userId = "d2add4ac-5509-45ef-87a5-d6c407f29a30";

        Playlist playlist = new Playlist(strNamePlaylist, UUID.fromString(userId));

        this.createPlaylistUseCase.execute(playlist);

    }

    @FXML
    void addSong(MouseEvent event) {
        // TODO: Selecionar o playlistId atual do usuário
        String strPlaylistId = "2fee7c36-cc4a-40fa-bb85-958904e1fca3";
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivos MP3 (*.mp3)", "*.mp3");
        musicFileChooser.getExtensionFilters().add(extFilter);
        musicFileChooser.setTitle("Escolha uma música");
        File file = musicFileChooser.showOpenDialog(new Stage());

        if(file == null) return;

        String[] parts = file.getName().split("\\.");
        String name = parts[0];
        String extension = parts[1];

        Song newSong = new Song(name, file.getPath(), UUID.fromString(strPlaylistId));

        createSongUseCase.execute(newSong);

        System.out.println("Música adicionada a playlist " + strPlaylistId);

    }

    @FXML
    void addFolder(MouseEvent event) {
        // TODO: Selecionar o playlistId atual do usuário
        String strPlaylistId = "2fee7c36-cc4a-40fa-bb85-958904e1fca3";

        directoryChooser.setTitle("Escolha um diretório");
        directoryChooser.setInitialDirectory(new java.io.File("."));

        File folder = directoryChooser.showDialog(new Stage());
        if (folder == null) return;

        Folder newFolder = new Folder(folder.getAbsolutePath(), UUID.fromString(strPlaylistId));

        createFolderUseCase.execute(newFolder);

        System.out.println("Folder adicionado a playlist " + strPlaylistId);

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
        mediaPlayer.stop();
        currentPlaylist.set(currentPlaylist.get() + 1);
    }

    @FXML
    public void nextSong() {
        mediaPlayer.stop();
        currentSong.set(currentSong.get() + 1);
    }

    public void playMedia(int index) {
        File file = new File(songsStore.getSongs().get(index).getPath());

        media = new Media(file.toURI().toString());

        //beginTimer();
        //changeSpeed(null);
        //mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);

        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
    }

    public void loadNewPlaylist() {
        currentSong.set(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playlistsStore.addListener((observableValue, oldPlaylists, newPlaylists) -> {
            if(newPlaylists.isEmpty()) return;

            System.out.println("observable de playlistsStore");


            currentPlaylist.set(0);
            currentSong.set(0);
            this.getAllSongsOfPlaylist(newPlaylists.get(currentPlaylist.get()).getId().toString());
        });

        songsStore.addListener((observableValue, oldSongs, newSongs) -> {
            System.out.println("observable de songsStore");
            if(newSongs.isEmpty()) {
                System.out.println("Tem nenhuma musica!");
                return;

            }

            // tocar musica...
            int indexSong = currentSong.get() % newSongs.size();
            System.out.println(songsStore.getSongs());
            System.out.println("tocando musica: " + songsStore.getSongs().get(indexSong));

            this.playMedia(indexSong);


        });

        currentPlaylist.addListener((observableValue, oldCurrentPlaylist, newCurrentPlaylist) -> {
            System.out.println("observable de currentPlaylist");
            int indexPlaylist = newCurrentPlaylist.intValue() % playlistsStore.getPlaylists().size();
            this.loadNewPlaylist();
            this.getAllSongsOfPlaylist(playlistsStore.getPlaylists().get(indexPlaylist).getId().toString());
        });

        currentSong.addListener((observableValue, oldCurrentSong, newCurrentSong) -> {
            System.out.println("observable de currentSong");
            if(songsStore.getSongs().isEmpty()) {
                System.out.println("Tem nenhuma musica!");
                return;
            }

            // chamaria a funcao de tocar musica aqui...
            int index = currentSong.get() % songsStore.getSongs().size();
            System.out.println("tocando musica: " + songsStore.getSongs().get(index));

            this.playMedia(index);
        });

        this.getAllPlaylistsOfUser(userStore.getUser().getId().toString());
    }





    public static void main(String[] args) throws EntityNotFoundException {

    }
}
