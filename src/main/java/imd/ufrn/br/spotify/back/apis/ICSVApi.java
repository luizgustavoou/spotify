package imd.ufrn.br.spotify.back.apis;

import java.util.List;

public interface ICSVApi {
    List<String[]> readFile();
    void writeFile(List<String[]> dataLines);

}
