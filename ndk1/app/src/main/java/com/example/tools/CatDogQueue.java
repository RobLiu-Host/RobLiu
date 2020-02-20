package com.example.tools;

import com.example.entity.Pet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列 要求满足 不修改原有的宠物类 能获取第一只i猫 第一只狗 第一只动物 队列是否有猫 是否有狗 是有有宠物
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

    public Pet pollAll(){
            if(!cat.isEmpty()&&!dog.isEmpty()) {
                if (cat.peek().getCount() < dog.peek().getCount()) {
                    return cat.poll().getPet();
                }else {
                    return dog.poll().getPet();
                }
            }else if(cat.isEmpty()&&!dog.isEmpty()){
                return dog.poll().getPet();
            }else if(!cat.isEmpty()&&dog.isEmpty()){
                return cat.poll().getPet();
            }else {
               throw new RuntimeException("this queue isEmpty") ;
            }
    }
    boolean isCatEmpty(){
        return cat.isEmpty();
    }


    boolean sDogEmpty(){
        return dog.isEmpty();
    }

    boolean isQueueEmpty(){
       return cat.isEmpty()&&dog.isEmpty();
    }







}
