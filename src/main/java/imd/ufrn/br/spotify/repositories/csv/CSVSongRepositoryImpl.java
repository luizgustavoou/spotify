package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.apis.CSVApiImpl;
import imd.ufrn.br.spotify.apis.ICSVApi;
import imd.ufrn.br.spotify.entities.Song;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.ISongRepository;

import java.util.*;

public class CSVSongRepositoryImpl implements ISongRepository {
    private  final String CSV_FILE_NAME = "/home/luizgustavoou/Documentos/study_java/spotify/db/songs.txt";
    private ICSVApi csvApi = new CSVApiImpl(CSV_FILE_NAME);

    public CSVSongRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVSongRepositoryImpl() {};

    private List<Song> readFile() {
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

    private void writeFile(List<Song> songs) {
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
    public Song create(Song value) {
        ArrayList<Song> songs = new ArrayList<Song>(this.readFile());
        songs.add(value);
        this.writeFile(songs);

        return value;
    }

    @Override
    public void update(UUID id, Song value) throws EntityNotFoundException {
        ArrayList<Song> songs = new ArrayList<Song>(this.readFile());

        int indexToUpdate = -1;

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getId().equals(id)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate == -1) {
            throw new EntityNotFoundException("Erro ao atualizar música: música não existe.");
        }

        songs.set(indexToUpdate, value);
        this.writeFile(songs);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        ArrayList<Song> songs = new ArrayList<Song>(this.readFile());
        boolean removed = songs.removeIf(song -> song.getId().equals(id));

        if(!removed) {
            throw new EntityNotFoundException("Erro ao remover música: música não existe.");
        }

        this.writeFile(songs);

    }

    @Override
    public List<Song> findAll() {
        return this.readFile();
    }

    @Override
    public Song findOneById(UUID id) throws EntityNotFoundException {
        ArrayList<Song> songs = new ArrayList<Song>(this.readFile());

        Optional<Song> song = songs.stream().filter(uu -> uu.getId().equals(id)).findFirst();

        if(song.isEmpty()) {
            throw new EntityNotFoundException("Erro ao buscar música: música não existe.");
        }

        return song.get();
    }


    @Override
    public List<Song> findAllSongsOfUserByPlaylistId(UUID playlistId) {
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

    // Test findAllSongsOfUserByPlaylistId
        System.out.println(songRepository.findAllSongsOfUserByPlaylistId(null));


    }
}
