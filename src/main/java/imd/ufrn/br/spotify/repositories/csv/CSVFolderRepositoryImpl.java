package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.apis.CSVApiImpl;
import imd.ufrn.br.spotify.apis.ICSVApi;
import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.repositories.IFolderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CSVFolderRepositoryImpl extends CSVRepositoryImpl<Folder> implements IFolderRepository {
    private  final String CSV_FILE_NAME = "db/folders.txt";
    private final ICSVApi csvApi;

    public CSVFolderRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVFolderRepositoryImpl() {
        this.csvApi = new CSVApiImpl(CSV_FILE_NAME);
    }

    public List<Folder> readFile() {
        List<String[]> foldersResponse = this.csvApi.readFile();

        return foldersResponse.stream().map(folderArray -> {
            UUID id = UUID.fromString(folderArray[0]);
            String path = folderArray[1];
            UUID playlistId;

            if(folderArray[2].equals("null")) {
                playlistId = null;
            }else {
                playlistId = UUID.fromString(folderArray[2]);
            }


            return new Folder(id, path, playlistId);
        }).toList();
    }

    public void writeFile(List<Folder> folders) {
        List<String[]> foldersResponse = folders.stream().map(folder -> {
            UUID id = folder.getId();
            String path = folder.getPath();
            UUID playlistId = folder.getPlaylistId();

            String playlistIdString = playlistId == null ? "null" : playlistId.toString();

            return new String[]{id.toString(), path, playlistIdString};
        }).toList();


        csvApi.writeFile(foldersResponse);
    }

    @Override
    public List<Folder> findAllFoldersOfPlaylist(UUID playlistId) {
        return this.readFile().stream().filter(folder -> folder.getPlaylistId() == playlistId || (folder.getPlaylistId() != null && folder.getPlaylistId().equals(playlistId))).toList();
    }


    public static void main(String[] args) throws EntityNotFoundException, UnauthorizedException {
        IFolderRepository folderRepository = new CSVFolderRepositoryImpl();

        // Test findAll
//        List<Folder> folders = folderRepository.findAll();
//        folders.forEach(folder -> {
//            System.out.println(folder.toString());
//        });

        // Test create
        Folder newFolder = new Folder("/home/luizgustavoou/Área de Trabalho/testmusicas", UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9")); // diretorio pertence a playlist Metalzão
        System.out.println(folderRepository.create(newFolder));


//         Test findOneById
//        System.out.println(folderRepository.findOneById(UUID.fromString("1a961bb4-6378-43db-82cd-29a8d199e5de")));

//         Test remove
//        folderRepository.remove(UUID.fromString("054b32bf-5c64-4dad-b7a9-42e39b693a23"));


        // Test update
//        folderRepository.update(UUID.fromString("511d0812-e8ed-4d0c-a6f8-ac943fe89d3d"), new Folder(UUID.fromString("511d0812-e8ed-4d0c-a6f8-ac943fe89d3d"),"/user/joabpato/folder"));

        // Test findAllFoldersOfPlaylist
//        System.out.println(folderRepository.findAllFoldersOfPlaylist(null)); // pegar todos os folders da playlist Default

//        System.out.println(folderRepository.findAllFoldersOfPlaylist(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9"))); // pegar todos os folders da playlist Metalzão
    }

}
