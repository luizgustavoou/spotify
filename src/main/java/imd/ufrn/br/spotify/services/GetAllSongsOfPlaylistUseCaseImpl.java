package imd.ufrn.br.spotify.services;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.folder.IFindAllFoldersOfPlaylist;
import imd.ufrn.br.spotify.services.folder.IFindAllSongsOfFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.FindAllFoldersOfPlaylistImpl;
import imd.ufrn.br.spotify.services.folder.impl.FindAllSongsOfFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.song.IFindAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.FindAllSongsOfPlaylistUseCaseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetAllSongsOfPlaylistUseCaseImpl implements IGetAllSongsOfPlaylistUseCase{

    IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase;
    IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylistUseCase;
    IFindAllFoldersOfPlaylist findAllFoldersOfPlaylist;

    GetAllSongsOfPlaylistUseCaseImpl(IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase, IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylist, IFindAllFoldersOfPlaylist findAllFoldersOfPlaylist) {
        this.findAllSongsOfFolderUseCase = findAllSongsOfFolderUseCase;
        this.findAllSongsOfPlaylistUseCase = findAllSongsOfPlaylist;
        this.findAllFoldersOfPlaylist = findAllFoldersOfPlaylist;
    }
    GetAllSongsOfPlaylistUseCaseImpl() {
        this.findAllSongsOfFolderUseCase = new FindAllSongsOfFolderUseCaseImpl();
        this.findAllSongsOfPlaylistUseCase = new FindAllSongsOfPlaylistUseCaseImpl();
        this.findAllFoldersOfPlaylist = new FindAllFoldersOfPlaylistImpl();
    }

    @Override
    public List<Song> execute(UUID playlistId) {
        ArrayList<Song> songs = new ArrayList<>();

        ArrayList<Song> songsOfPlaylist = new ArrayList<>(this.findAllSongsOfPlaylistUseCase.execute(playlistId));

        songs.addAll(songsOfPlaylist);

        findAllFoldersOfPlaylist.execute(playlistId).forEach(folder -> {
            ArrayList<Song> songsOfFolder = new ArrayList<>(this.findAllSongsOfFolderUseCase.execute(folder.getPath(), folder.getId()));
            songs.addAll(songsOfFolder);
        });

        return songs;


    }

    public static void main(String[] args) {
        IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();

        ArrayList<Song> songs = new ArrayList<>(getAllSongsOfPlaylistUseCase.execute(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9")));

        System.out.println(songs);

    }
}
