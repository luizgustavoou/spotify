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

public class CSVFolderRepositoryImpl implements IFolderRepository {
    // private  final String CSV_FILE_NAME = "";
    // // Path joab
//    private  final String CSV_FILE_NAME = "C:\\folders\\Joab\\Desktop\\spotify\\db\\folders.txt";

    // Path luiz
    private  final String CSV_FILE_NAME = "/home/luizgustavoou/Documentos/projects/spotify/db/folders.txt";

    private ICSVApi csvApi = new CSVApiImpl(CSV_FILE_NAME);

    public CSVFolderRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVFolderRepositoryImpl() {}

    private List<Folder> readFile() {
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

    private void writeFile(List<Folder> folders) {
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
    public Folder create(Folder value) {
        ArrayList<Folder> folders = new ArrayList<>(this.readFile());
        folders.add(value);
        this.writeFile(folders);

        return value;
    }

    @Override
    public void update(UUID id, Folder value) throws EntityNotFoundException {
        ArrayList<Folder> folders = new ArrayList<>(this.readFile());

        int indexToUpdate = -1;

        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getId().equals(id)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate == -1) {
            throw new EntityNotFoundException("Erro ao atualizar diretório: diretório não existe.");
        }

        folders.set(indexToUpdate, value);
        this.writeFile(folders);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        ArrayList<Folder> folders = new ArrayList<>(this.readFile());
        boolean removed = folders.removeIf(folder -> folder.getId().equals(id));

        if(!removed) {
            throw new EntityNotFoundException("Erro ao remover diretório: diretório não existe.");
        }

        this.writeFile(folders);

    }

    @Override
    public List<Folder> findAll() {
        return this.readFile();
    }

    @Override
    public Folder findOneById(UUID id) throws EntityNotFoundException {
        ArrayList<Folder> folders = new ArrayList<>(this.readFile());

        Optional<Folder> folder = folders.stream().filter(uu -> uu.getId().equals(id)).findFirst();

        if(folder.isEmpty()) {
            throw new EntityNotFoundException("Erro ao buscar diretório: diretório não existe.");
        }

        return folder.get();
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
//        Folder newFolder = new Folder("caminho", UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9")); // diretorio pertence a playlist Metalzão
//        System.out.println(newFolder.toString());
//        folderRepository.create(newFolder);


//         Test findOneById
//        System.out.println(folderRepository.findOneById(UUID.fromString("1a961bb4-6378-43db-82cd-29a8d199e5de")));

//         Test remove
//        folderRepository.remove(UUID.fromString("054b32bf-5c64-4dad-b7a9-42e39b693a23"));


        // Test update
//        folderRepository.update(UUID.fromString("511d0812-e8ed-4d0c-a6f8-ac943fe89d3d"), new Folder(UUID.fromString("511d0812-e8ed-4d0c-a6f8-ac943fe89d3d"),"/user/joabpato/folder"));

        // Test findAllFoldersOfPlaylist
        System.out.println(folderRepository.findAllFoldersOfPlaylist(null)); // pegar todos os folders da playlist Default

//        System.out.println(folderRepository.findAllFoldersOfPlaylist(UUID.fromString("ab2acce0-30ca-4aa9-98cb-315781d0c2b9"))); // pegar todos os folders da playlist Metalzão
    }

}
