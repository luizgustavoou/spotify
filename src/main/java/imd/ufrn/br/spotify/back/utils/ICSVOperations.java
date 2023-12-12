package imd.ufrn.br.spotify.back.utils;

import java.util.List;

public interface ICSVOperations {
    List<String[]> readFile();
    void writeFile(List<String[]> dataLines);

}
