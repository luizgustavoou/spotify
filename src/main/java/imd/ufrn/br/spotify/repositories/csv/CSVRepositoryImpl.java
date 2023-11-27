package imd.ufrn.br.spotify.repositories.csv;

import imd.ufrn.br.spotify.entities.Entity;
import imd.ufrn.br.spotify.exceptions.EntityNotFoundException;
import imd.ufrn.br.spotify.repositories.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

abstract public class CSVRepositoryImpl<T extends Entity> implements IRepository<T> {
    abstract List<T> readFile();

    abstract void writeFile(List<T> values);

    @Override
    public T create(T value) {
        ArrayList<T> values = new ArrayList<T>(this.readFile());
        values.add(value);
        this.writeFile(values);

        return value;
    }

    @Override
    public void update(UUID id, T value) throws EntityNotFoundException {
        ArrayList<T> values = new ArrayList<T>(this.readFile());

        int indexToUpdate = -1;

        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getId().equals(id)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate == -1) {
            throw new EntityNotFoundException("Erro ao atualizar T: T não existe.");
        }

        values.set(indexToUpdate, value);
        this.writeFile(values);
    }

    @Override
    public void remove(UUID id) throws EntityNotFoundException {
        ArrayList<T> values = new ArrayList<T>(this.readFile());

        boolean removed = values.removeIf(value -> value.getId().equals(id));

        if(!removed) {
            throw new EntityNotFoundException("Erro ao remover T: T não existe.");
        }

        this.writeFile(values);

    }

    @Override
    public List<T> findAll() {
        return this.readFile();
    }

    @Override
    public T findOneById(UUID id) throws EntityNotFoundException {
        ArrayList<T> values = new ArrayList<T>(this.readFile());

        Optional<T> valueFinded = values.stream().filter(value -> value.getId().equals(id)).findFirst();

        if(valueFinded.isEmpty()) {
            throw new EntityNotFoundException("Erro ao buscar T: T não existe.");
        }

        return valueFinded.get();
    }
}
