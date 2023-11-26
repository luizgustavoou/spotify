package imd.ufrn.br.spotify.services;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.folder.IFindAllSongsOfFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.FindAllSongsOfFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.song.IFindAllSongsOfPlaylist;
import imd.ufrn.br.spotify.services.song.impl.FindAllSongsOfPlaylistImpl;

import java.util.List;
import java.util.UUID;

public class GetAllSongsOfPlaylistUseCaseImpl implements IGetAllSongsOfPlaylistUseCase{
    IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase;
    IFindAllSongsOfPlaylist findAllSongsOfPlaylist;

    GetAllSongsOfPlaylistUseCaseImpl(IFindAllSongsOfFolderUseCase findAllSongsOfFolderUseCase, IFindAllSongsOfPlaylist findAllSongsOfPlaylist) {
        this.findAllSongsOfFolderUseCase = findAllSongsOfFolderUseCase;
        this.findAllSongsOfPlaylist = findAllSongsOfPlaylist;
    }
    GetAllSongsOfPlaylistUseCaseImpl() {
        this.findAllSongsOfFolderUseCase = new FindAllSongsOfFolderUseCaseImpl();
        this.findAllSongsOfPlaylist = new FindAllSongsOfPlaylistImpl();
    }

    @Override
    public List<Song> execute(UUID playlistId) {
        return null;
    }
}
