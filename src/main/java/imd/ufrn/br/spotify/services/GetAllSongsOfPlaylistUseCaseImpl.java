package imd.ufrn.br.spotify.services;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.folder.IFindAllSongsOfFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.FindAllSongsOfFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.song.IFindAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.impl.FindAllSongsOfPlaylistUseCaseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetAllSongsOfPlaylistUseCaseImpl implements IGetAllSongsOfPlaylistUseCase{
    IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase;
    IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylistUseCase;

    GetAllSongsOfPlaylistUseCaseImpl(IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase, IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylist) {
        this.findAllSongsOfFolderUseCase = findAllSongsOfFolderUseCase;
        this.findAllSongsOfPlaylistUseCase = findAllSongsOfPlaylist;
    }
    GetAllSongsOfPlaylistUseCaseImpl() {
        this.findAllSongsOfFolderUseCase = new FindAllSongsOfFolderUseCaseImpl();
        this.findAllSongsOfPlaylistUseCase = new FindAllSongsOfPlaylistUseCaseImpl();
    }

    @Override
    public List<Song> execute(UUID playlistId) {
        ArrayList<Song> songs;

        ArrayList<Song> songsOfPlaylist = new ArrayList<>(this.findAllSongsOfPlaylistUseCase.execute(playlistId));
//        ArrayList<Song> songsOfFolder = new ArrayList<>(this.findAllSongsOfFolderUseCase());


        return songsOfPlaylist;


    }

    public static void main(String[] args) {
        IGetAllSongsOfPlaylistUseCase getAllSongsOfPlaylistUseCase = new GetAllSongsOfPlaylistUseCaseImpl();

        ArrayList<Song> songs = new ArrayList<>(getAllSongsOfPlaylistUseCase.execute(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9")));

        System.out.println(songs);

    }
}
