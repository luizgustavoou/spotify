package imd.ufrn.br.spotify.services.playlist.impl;

import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IPlaylistRepository;
import imd.ufrn.br.spotify.repositories.csv.CSVPlaylistRepositoryImpl;
import imd.ufrn.br.spotify.services.folder.IFindAllFoldersOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.folder.IRemoveFolderUseCase;
import imd.ufrn.br.spotify.services.folder.impl.FindAllFoldersOfPlaylistImpl;
import imd.ufrn.br.spotify.services.folder.impl.RemoveFolderUseCaseImpl;
import imd.ufrn.br.spotify.services.playlist.IRemovePlaylistUseCase;
import imd.ufrn.br.spotify.services.song.IFindAllSongsOfPlaylistUseCase;
import imd.ufrn.br.spotify.services.song.IRemoveSongUseCase;
import imd.ufrn.br.spotify.services.song.impl.FindAllSongsOfPlaylistUseCaseImpl;
import imd.ufrn.br.spotify.services.song.impl.RemoveSongUseCaseImpl;

import java.util.ArrayList;
import java.util.UUID;

public class RemovePlaylistUseCaseImpl implements IRemovePlaylistUseCase {
    IPlaylistRepository playlistRepository;
    IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylistUseCase;
    IRemoveSongUseCase removeSongUseCase;
    IFindAllFoldersOfPlaylistUseCase findAllFoldersOfPlaylistUseCase;
    IRemoveFolderUseCase removeFolderUseCase;
    public RemovePlaylistUseCaseImpl() {
        this.playlistRepository = new CSVPlaylistRepositoryImpl();
        this.findAllSongsOfPlaylistUseCase = new FindAllSongsOfPlaylistUseCaseImpl();
        this.removeSongUseCase = new RemoveSongUseCaseImpl();
        this.findAllFoldersOfPlaylistUseCase = new FindAllFoldersOfPlaylistImpl();
        this.removeFolderUseCase = new RemoveFolderUseCaseImpl();
    }

    public RemovePlaylistUseCaseImpl(IPlaylistRepository playlistRepository, IFindAllSongsOfPlaylistUseCase findAllSongsOfPlaylistUseCase, IRemoveSongUseCase removeSongUseCase, IFindAllFoldersOfPlaylistUseCase findAllFoldersOfPlaylistUseCase, IRemoveFolderUseCase removeFolderUseCase) {
        this.playlistRepository = playlistRepository;
        this.findAllSongsOfPlaylistUseCase = findAllSongsOfPlaylistUseCase;
        this.removeSongUseCase = removeSongUseCase;
        this.findAllFoldersOfPlaylistUseCase = findAllFoldersOfPlaylistUseCase;
        this.removeFolderUseCase = removeFolderUseCase;
    }
    @Override
    public void execute(UUID id) throws EntityNotFoundException {
        this.playlistRepository.remove(id);

        ArrayList<Song> songs = new ArrayList<>(findAllSongsOfPlaylistUseCase.execute(id));

        songs.forEach(song -> {
            try{
                this.removeSongUseCase.execute(song.getId());
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });

        ArrayList<Folder> folders = new ArrayList<>(findAllFoldersOfPlaylistUseCase.execute(id));

        folders.forEach(folder -> {
            try{
                this.removeFolderUseCase.execute(folder.getId());
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void main(String[] args) throws EntityNotFoundException {
        IRemovePlaylistUseCase removePlaylistUseCase = new RemovePlaylistUseCaseImpl();

        removePlaylistUseCase.execute(UUID.fromString("69c06b05-262b-4fe8-bcf0-c9a035f910bb"));
    }
}
