package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.apis.CSVApiImpl;
import imd.ufrn.br.spotify.apis.ICSVApi;
import imd.ufrn.br.spotify.entities.User;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.exceptions.UnauthorizedException;
import imd.ufrn.br.spotify.repositories.IUserRepository;
import java.util.*;

public class CSVUserRepositoryImpl implements IUserRepository {
    private  final String CSV_FILE_NAME = "/home/luizgustavoou/Documentos/projects/spotify/db/users.txt";
    private ICSVApi csvApi = new CSVApiImpl(CSV_FILE_NAME);

    public CSVUserRepositoryImpl(ICSVApi csvApi) {
        this.csvApi = csvApi;
    }

    public CSVUserRepositoryImpl() {};

    private List<User> readFile() {
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

    private void writeFile(List<User> users) {
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
    public User create(User value) {
        ArrayList<User> users = new ArrayList<User>(this.readFile());
        users.add(value);
        this.writeFile(users);

        return value;
    }

    @Override
    public void update(UUID id, User value) throws EntityNotFoundException {
        ArrayList<User> users = new ArrayList<User>(this.readFile());

        int indexToUpdate = -1;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate == -1) {
            throw new EntityNotFoundException("Erro ao atualizar usuário: usuário não existe.");
        }

        users.set(indexToUpdate, value);
        this.writeFile(users);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        ArrayList<User> users = new ArrayList<User>(this.readFile());
        boolean removed = users.removeIf(user -> user.getId().equals(id));

        if(!removed) {
            throw new EntityNotFoundException("Erro ao remover usuário: usuário não existe.");
        }

        this.writeFile(users);

    }

    @Override
    public List<User> findAll() {
      return this.readFile();
    }

    @Override
    public User findOneById(UUID id) throws EntityNotFoundException {
        ArrayList<User> users = new ArrayList<User>(this.readFile());

        Optional<User> user = users.stream().filter(uu -> uu.getId().equals(id)).findFirst();

        if(user.isEmpty()) {
            throw new EntityNotFoundException("Erro ao buscar usuário: usuário não existe.");
        }

        return user.get();
    }

    @Override
    public User findUserByUsername(String username) throws EntityNotFoundException {
        ArrayList<User> users = new ArrayList<User>(this.readFile());

        Optional<User> user = users.stream().filter(us -> us.getUsername().equals(username)).findFirst();

        if(user.isEmpty()) {
            throw new EntityNotFoundException("Erro ao buscar usuário pelo username: usuário não encontrado.");
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
//
//        userRepository.create(newUser);

//         Test findOneById
//        System.out.println(userRepository.findOneById(UUID.fromString("3b3837b4-e6a0-4d0e-90e6-c54e2a5951db")));

//         Test remove
//        userRepository.remove(UUID.fromString("063a5809-e91b-4670-808a-9b5e3409fb98"));


        // Test update
//        userRepository.update(UUID.fromString("137b0fe8-bb20-441b-89c4-40106cce22ff"), new User(UUID.fromString("137b0fe8-bb20-441b-89c4-40106cce22ff"),"joabpato", "123", "Joab Pato Agiota", true));
    }
}
