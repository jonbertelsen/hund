package app.entities;

import app.enums.Breed;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dog {
    private Long id;
    private String name;
    private Breed breed;

    public Dog() {
    }

    public Dog(Long id, String name, Breed breed) {
        this.id = id;
        this.name = name;
        this.breed = breed;
    }

    public Dog(String name, Breed breed) {
        this.name = name;
        this.breed = breed;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Breed getBreed() {
        return breed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed=" + breed +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        Dog dog = (Dog) o;
        return Objects.equals(getId(), dog.getId()) && Objects.equals(getName(), dog.getName()) && getBreed() == dog.getBreed();
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getId());
        result = 31 * result + Objects.hashCode(getName());
        result = 31 * result + Objects.hashCode(getBreed());
        return result;
    }
}
