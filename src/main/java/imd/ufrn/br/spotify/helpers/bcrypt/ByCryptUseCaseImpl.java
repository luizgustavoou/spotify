package imd.ufrn.br.spotify.helpers.bcrypt;

import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;

public class ByCryptUseCaseImpl implements IByCryptUseCase {
    @Override
    public String generateHash(String content) {
        return  BCrypt.hashpw(content, BCrypt.gensalt());
    }

    @Override
    public boolean compareContent(String content, String hashContent) {
        return BCrypt.checkpw(content, hashContent);
    }

    public static void main(String[] args) {
        ByCryptUseCaseImpl byCryptUseCase = new ByCryptUseCaseImpl();

        String passwordHash = byCryptUseCase.generateHash("senha");

        System.out.println(passwordHash);

        boolean compare = byCryptUseCase.compareContent("senha", "$2a$10$rZG5AT1HoK0CtEHyczl/5uhjcPcvOXRqvk2.HH6q94XrNcV3Qcz5C");

        System.out.println(compare);


    }
}
