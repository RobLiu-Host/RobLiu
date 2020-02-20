package com.example.tools;

import com.example.entity.Pet;

public class PetEnter {

    private Pet pet;
    private long count;

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public PetEnter(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }
}
