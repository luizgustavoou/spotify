package imd.ufrn.br.spotify.apis;

import java.io.FileNotFoundException;
import java.util.List;

public interface ICSVApi {
    List<String[]> readFile();
    void writeFile(List<String[]> dataLines);

}
