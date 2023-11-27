package imd.ufrn.br.spotify.services.song.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.song.IFindAllSongsOfFolderUseCase;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

public class FindAllSongsOfFolderUseCaseImpl implements IFindAllSongsOfFolderUseCase {
    @Override
    public List<Song> execute(String path, UUID idPlaylist) {
        File folder = new File(path);
        ArrayList<Song> songs = new ArrayList<>();

        if (folder.isDirectory()) {
            File[] mp3Files = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String fileName) {
                    return fileName.toLowerCase().endsWith(".mp3");
                }
            });

            if (mp3Files != null) {
                for (File file : mp3Files) {
                    Song song = new Song(file.getName(), file.getPath(), idPlaylist);
                    songs.add(song);
                }
            }
        } else {
            System.out.println("O caminho fornecido não é um diretório válido.");
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
