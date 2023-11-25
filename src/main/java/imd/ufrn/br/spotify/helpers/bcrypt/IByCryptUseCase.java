package imd.ufrn.br.spotify.helpers.bcrypt;

public interface IByCryptUseCase {
    String generateHash(String content);
    boolean compareContent(String content, String hashContent);
}
