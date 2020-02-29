package com.carlsenbot.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CatTest {

    @Test
    void speak() {
        Cat c = new Cat();
        assertEquals("meow", c.speak());
    }
}