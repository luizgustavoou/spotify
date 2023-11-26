package imd.ufrn.br.spotify.services.folder.impl;

import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.services.folder.IFindAllSongsOfFolderUseCase;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

public class FindAllSongsOfFolderUseCaseImpl implements IFindAllSongsOfFolderUseCase {
    @Override
    public List<Song> execute(String path, UUID idPlaylist) {
        File diretorio = new File(path);
        ArrayList<Song> songs = new ArrayList<Song>();

        if (diretorio.isDirectory()) {
            File[] arquivosMP3 = diretorio.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String nomeArquivo) {
                    return nomeArquivo.toLowerCase().endsWith(".mp3");
                }
            });

            if (arquivosMP3 != null) {
                for (File arquivo : arquivosMP3) {
                    Song newSong = new Song(arquivo.getName(), arquivo.getPath(), idPlaylist);
                    songs.add(newSong);
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
        songs.forEach(song -> {
            System.out.println(song);
        });
    }
}
