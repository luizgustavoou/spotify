package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.apis.CSVApiImpl;
import imd.ufrn.br.spotify.apis.ICSVApi;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.ISongRepository;

import java.util.*;

public class CSVSongRepositoryImpl extends CSVRepositoryImpl<Song> implements ISongRepository {

    // Path fellipe
    // private  final String CSV_FILE_NAME = "C:\Users\zanat\IdeaProjects\spotify\db\songs.txt";

    // Path Joab
//    private final String CSV_FILE_NAME = "C:\\Users\\Joab\\IdeaProjects\\spotify\\db\\songs.txt";

    // Path luiz
//     private final String CSV_FILE_NAME = "/home/luizgustavoou/Documentos/projects/spotify/db/songs.txt";

    // Path luiz windows
    private final String CSV_FILE_NAME = "D:\\Biblioteca\\Documentos\\projects\\spotify\\db\\songs.txt";
    private final ICSVApi csvApi;

    public CSVSongRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVSongRepositoryImpl() {
        this.csvApi = new CSVApiImpl(CSV_FILE_NAME);
    };

    public List<Song> readFile() {
        List<String[]> songsResponse = this.csvApi.readFile();


        return songsResponse.stream().map(songArray -> {
            UUID id = UUID.fromString(songArray[0]);
            String name = songArray[1];
            String path = songArray[2];
            UUID playlistId;

            if(songArray[3].equals("null")) {
                playlistId = null;
            }else {
                playlistId = UUID.fromString(songArray[3]);
            }


            return new Song(id, name, path, playlistId);
        }).toList();


    }

    public void writeFile(List<Song> songs) {
        List<String[]> songsResponse = songs.stream().map(song -> {
            UUID id = song.getId();
            String name = song.getName();
            String path = song.getPath();
            UUID playlistId = song.getPlaylistId();

            String playlistIdString = playlistId == null ? "null" : playlistId.toString();


            return new String[]{id.toString(), name, path, playlistIdString};
        }).toList();


        csvApi.writeFile(songsResponse);
    }

    @Override
    public List<Song> findAllSongsOfPlaylist(UUID playlistId) {
        return this.readFile().stream().filter(song -> song.getPlaylistId() == playlistId || (song.getPlaylistId() != null && song.getPlaylistId().equals(playlistId))).toList();
    }

    public static void main(String[] args) throws EntityNotFoundException {
        ISongRepository songRepository = new CSVSongRepositoryImpl();

        // Test findAll
//        List<Song> songs = songRepository.findAll();
//
//        songs.forEach(song -> {
//            System.out.println(song.toString());
//        });

//         Test create
//        Song newSong = new Song("musica ruim2", "caminho2", UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9")); // song pertence a playlist Metalzão
//        Song newSong = new Song("musica ruim", "caminho", null);
//        songRepository.create(newSong);

//         Test findOneById
//        System.out.println(songRepository.findOneById(UUID.fromString("fa77b617-6b67-4744-894c-fec4bbc597df")));

//         Test remove
//        songRepository.remove(UUID.fromString("fa77b617-6b67-4744-894c-fec4bbc597df"));


        // Test update
//        songRepository.update(UUID.fromString("7f32a9cb-23b9-47f2-b83d-b02af47d5969"), new Song(UUID.fromString("7f32a9cb-23b9-47f2-b83d-b02af47d5969"),"musicaruim", "caminho", null));

    // Test findAllSongsOfPlaylist
//        System.out.println(songRepository.findAllSongsOfUserByPlaylistId(null)); // pegar todos os folders da playlist Default
        System.out.println(songRepository.findAllSongsOfPlaylist(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9"))); // pegar todos os folders da playlist Metalzão


    }
}
