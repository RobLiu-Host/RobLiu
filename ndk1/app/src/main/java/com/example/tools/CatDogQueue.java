package com.example.tools;

import com.example.entity.Pet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列 要求满足 不修改原有的宠物类 能获取第一个猫 第一支🐶 第一支动物
 *
 * 实现思路通过计数器获得当前宠物的进去顺序 通过判断两个队列的顶部的count就能判断那个宠物应该被弹出
 */
public class CatDogQueue {


        Queue<PetEnter>cat;
        Queue<PetEnter>dog;
        long count;
    public CatDogQueue() {
        this.dog = new LinkedList<>();
        this.dog=new LinkedList<>();
    }

    void add(Pet pet){
        if("cat".equals(pet.getType())){
            cat.add(new PetEnter(pet,count++));
        }else {
            dog.add(new PetEnter(pet,count++));
        }
    }

    void pollAll(){

    }

}
