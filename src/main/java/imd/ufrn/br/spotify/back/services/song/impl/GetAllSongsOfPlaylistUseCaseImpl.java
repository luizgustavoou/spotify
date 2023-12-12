package imd.ufrn.br.spotify.back.services.song.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.back.services.folder.IFindAllFoldersOfPlaylistUseCase;
import imd.ufrn.br.spotify.back.services.song.IFindAllSongsOfFolderUseCase;
import imd.ufrn.br.spotify.back.services.folder.impl.FindAllFoldersOfPlaylistImpl;
import imd.ufrn.br.spotify.back.services.song.IFindAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.back.services.song.IGetAllSongsOfPlaylistUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetAllSongsOfPlaylistUseCaseImpl implements IGetAllSongsOfPlaylistUseCase {


    IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylistUseCase;
    IFindAllFoldersOfPlaylistUseCase findAllFoldersOfPlaylist;
    IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase;

    GetAllSongsOfPlaylistUseCaseImpl(IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase, IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylist, IFindAllFoldersOfPlaylistUseCase findAllFoldersOfPlaylist) {
        this.findAllSongsOfPlaylistUseCase = findAllSongsOfPlaylist;
        this.findAllFoldersOfPlaylist = findAllFoldersOfPlaylist;
        this.findAllSongsOfFolderUseCase = findAllSongsOfFolderUseCase;
    }
    public GetAllSongsOfPlaylistUseCaseImpl() {
        this.findAllSongsOfPlaylistUseCase = new FindAllSongsOfPlaylistUseCaseImpl();
        this.findAllFoldersOfPlaylist = new FindAllFoldersOfPlaylistImpl();
        this.findAllSongsOfFolderUseCase = new FindAllSongsOfFolderUseCaseImpl();
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
