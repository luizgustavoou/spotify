package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.apis.CSVApiImpl;
import imd.ufrn.br.spotify.apis.ICSVApi;
import imd.ufrn.br.spotify.entities.Folder;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.repositories.IFolderRepository;

import java.util.List;
import java.util.UUID;

public class CSVFolderRepositoryImpl implements IFolderRepository {
    // private  final String CSV_FILE_NAME = "";
    private  final String CSV_FILE_NAME = "C:\\Users\\Joab\\Desktop\\spotify\\db\\folders.txt";
    private ICSVApi csvApi = new CSVApiImpl(CSV_FILE_NAME);

    public CSVFolderRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVFolderRepositoryImpl() {}

    private List<Folder> readFile() {
        List<String[]> usersResponse = this.csvApi.readFile();

        return usersResponse.stream().map(userArray -> {
            UUID id = UUID.fromString(userArray[0]);
            String path = userArray[1];


            return new Folder(id, path);
        }).toList();
    }

    private void writeFile(List<Folder> folders) {
        List<String[]> usersResponse = folders.stream().map(folder -> {
            UUID id = folder.getId();
            String path = folder.getPath();

            return new String[]{id.toString(), path};
        }).toList();


        csvApi.writeFile(usersResponse);
    }

    @Override
    public List<Folder> findAllFoldersOfUser(UUID id) {
        return null;
    }

    @Override
    public Folder create(Folder value) {
        return null;
    }

    @Override
    public void update(UUID id, Folder value) throws EntityNotFoundException {

    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {

    }

    @Override
    public List<Folder> findAll() {
        return null;
    }

    @Override
    public Folder findOneById(UUID id) throws EntityNotFoundException {
        return null;
    }

    public static void main(String[] args) throws EntityNotFoundException, UnauthorizedException {
        IFolderRepository folderRepository = new CSVFolderRepositoryImpl();

        // Test findAll
        List<Folder> folders = folderRepository.findAll();
        folders.forEach(folder -> {
            System.out.println(folder.toString());
        });

        // Test create
        Folder newFolder = new Folder(null, "caminho");
        System.out.println(newFolder.toString());
        folderRepository.create(newFolder);


//         Test findOneById
        System.out.println(folderRepository.findOneById(UUID.fromString("3b3837b4-e6a0-4d0e-90e6-c54e2a5951db")));

//         Test remove
        folderRepository.remove(UUID.fromString("063a5809-e91b-4670-808a-9b5e3409fb98"));


        // Test update
        folderRepository.update(UUID.fromString("137b0fe8-bb20-441b-89c4-40106cce22ff"), new Folder(UUID.fromString("137b0fe8-bb20-441b-89c4-40106cce22ff"),"/user/joabpato/folder"));
    }
}
