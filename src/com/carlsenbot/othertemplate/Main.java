package com.carlsenbot.othertemplate;

import com.carlsenbot.template.Cat;
import com.carlsenbot.template.Dog;
import com.carlsenbot.template.IAnimal;

public class Main {
    public static void main(String[] args) {
        IAnimal a1, a2;
        a1 = new Dog();
        a2 = new Cat();

        System.out.println(a1.speak());
        System.out.println(a2.speak());
    }
}
