package imd.ufrn.br.spotify.back.services.song.impl;

import imd.ufrn.br.spotify.back.entities.Song;
import imd.ufrn.br.spotify.back.helpers.Extensions;
import imd.ufrn.br.spotify.back.services.song.IFindAllSongsOfFolderUseCase;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.util.UUID;

public class FindAllSongsOfFolderUseCaseImpl implements IFindAllSongsOfFolderUseCase {
    @Override
    public List<Song> execute(String path, UUID idPlaylist) {
        File folder = new File(path);
        ArrayList<Song> songs = new ArrayList<>();

        if (folder.isDirectory()) {
            File[] mp3Files = folder.listFiles((File file, String fileName) -> fileName.toLowerCase().endsWith(Extensions.MP3_EXTENSION));

            if (mp3Files != null) {
                for (File file : mp3Files) {
                    Song song = new Song(file.getName(), file.getPath(), idPlaylist);
                    songs.add(song);
                }
            }
        } else {
//            System.out.println("O caminho fornecido não é um diretório válido.");
        }
        return songs;
    }

    public static void main(String[] args) {
        IFindAllSongsOfFolderUseCase findSongsOfFolderUseCase = new FindAllSongsOfFolderUseCaseImpl();
        // Luiz
        String pathFolder = "/home/luizgustavoou/Área de Trabalho/testmusicas";

        // Joab
//        String pathFolder = "C:\\Users\\Joab\\Desktop\\teste";

        ArrayList<Song> songs = new ArrayList<>(findSongsOfFolderUseCase.execute(pathFolder, UUID.fromString("0b0b5ce5-c100-4e18-8197-8b3b81b22aae")));

//        ArrayList<Song> songs = new ArrayList<>(findSongsOfFolderUseCase.execute(pathFolder, UUID.fromString("0b0b5ce5-c100-4e18-8197-8b3b81b22aae")));

        System.out.println(songs);
    }
}
