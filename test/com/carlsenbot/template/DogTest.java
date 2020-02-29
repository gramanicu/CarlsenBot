package com.carlsenbot.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DogTest {

    @Test
    void speak() {
        Dog d = new Dog();
        assertEquals("wof", d.speak());
    }
}