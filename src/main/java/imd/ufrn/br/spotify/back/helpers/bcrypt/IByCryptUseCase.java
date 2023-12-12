package imd.ufrn.br.spotify.back.helpers.bcrypt;

public interface IByCryptUseCase {
    String generateHash(String content);
    boolean compareContent(String content, String hashContent);
}
