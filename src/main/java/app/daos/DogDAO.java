package app.daos;

import app.entities.Dog;

import java.util.*;

public class DogDAO {

    private final Map<Long, Dog> dogs = new HashMap<>();
    private long nextId = 1L;

    public Dog create(Dog dog){
        long id = nextId++;
        dog.setId(id);
        dogs.put(id, dog);
        return dog;
    }

    public List<Dog> getAll(){
        return new ArrayList<>(dogs.values());
    }

    public Optional<Dog> getById(long id){
        return Optional.ofNullable(dogs.get(id));
    }


}
