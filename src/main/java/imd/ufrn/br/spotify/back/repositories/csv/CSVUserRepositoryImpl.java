package imd.ufrn.br.spotify.back.repositories.csv;

import imd.ufrn.br.spotify.back.apis.CSVApiImpl;
import imd.ufrn.br.spotify.back.apis.ICSVApi;
import imd.ufrn.br.spotify.back.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.back.repositories.IUserRepository;
import java.util.*;

public class CSVUserRepositoryImpl extends CSVRepositoryImpl<User> implements IUserRepository {
    private  final String CSV_FILE_NAME = "db/users.txt";

    private final ICSVApi csvApi;

    public CSVUserRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVUserRepositoryImpl() {
        this.csvApi = new CSVApiImpl(CSV_FILE_NAME);
    };

    public List<User> readFile() {
        List<String[]> usersResponse = this.csvApi.readFile();


        return usersResponse.stream().map(userArray -> {
            UUID id = UUID.fromString(userArray[0]);
            String username = userArray[1];
            String password = userArray[2];
            String fullName = userArray[3];

            Boolean isVip = Objects.equals(userArray[4], "1");


            return new User(id, username, password, fullName, isVip);
        }).toList();
    }

    public void writeFile(List<User> users) {
        List<String[]> usersResponse = users.stream().map(user -> {
            UUID id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();
            String fullName = user.getFullName();
            String isVip = user.getVip() ? "1" : "0";


            return new String[]{id.toString(), username, password, fullName, isVip};
        }).toList();


        csvApi.writeFile(usersResponse);
    }

    @Override
    public User findUserByUsername(String username) throws EntityNotFoundException {
        ArrayList<User> users = new ArrayList<User>(this.readFile());

        Optional<User> user = users.stream().filter(us -> us.getUsername().equals(username)).findFirst();

        if(user.isEmpty()) {
            throw new EntityNotFoundException("Digite um nome de usuário válido.");
        }
        return user.get();
    }

    public static void main(String[] args) throws EntityNotFoundException, UnauthorizedException {
        IUserRepository userRepository = new CSVUserRepositoryImpl();

        // Test findAll
//        List<User> users = userRepository.findAll();
//        users.forEach(user -> {
//            System.out.println(user.toString());
//        });

        // Test create
//        User newUser = new User("joao", "123", "João da Silva", false);
//        userRepository.create(newUser);

//         Test findOneById
//        System.out.println(userRepository.findOneById(UUID.fromString("3b3837b4-e6a0-4d0e-90e6-c54e2a5951db")));

//         Test remove
//        userRepository.remove(UUID.fromString("063a5809-e91b-4670-808a-9b5e3409fb98"));


        // Test update
//        userRepository.update(UUID.fromString("137b0fe8-bb20-441b-89c4-40106cce22ff"), new User(UUID.fromString("137b0fe8-bb20-441b-89c4-40106cce22ff"),"joabpato", "123", "Joab Pato Agiota", true));
    }
}
