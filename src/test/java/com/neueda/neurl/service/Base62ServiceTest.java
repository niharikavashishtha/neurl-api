package com.neueda.neurl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Base62ServiceTest {

    private Base62Service base62Service = new Base62Service();

    @Test
    public void test_encode_success() {
        assertEquals("Z", base62Service.encode(51));
    }

    @Test
    public void test_encode_moreThan62_success() {
        assertEquals("nhA", base62Service.encode(50432));
    }

    @Test
    public void test_decode_singleCharacter() {
        assertEquals(24, base62Service.decode("y"));
    }
}