package com.neueda.neurl.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Base62ServiceTest {

    private Base62Service base62Service = new Base62Service();

    @Test
    public void encode_lessThan62() {
        assertEquals("k", base62Service.encode(10));
    }

    @Test
    public void encode_moreThan62() {
        assertEquals("bq", base62Service.encode(78));
    }

    @Test
    public void decode_singleCharacter() {
        assertEquals(11, base62Service.decode("l"));
    }
}
