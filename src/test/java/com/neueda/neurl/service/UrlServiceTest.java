package com.neueda.neurl.service;
import static org.junit.Assert.assertEquals;
import com.neueda.neurl.repository.UrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {
    @Mock
    private UrlRepository urlRepository;
    @Mock
    private Base62Service base62Service;
    @InjectMocks
    private UrlService urlService;

    @Test
    public void testGetLongUrl_success(){
        when(base62Service.decode("xyzabc")).thenReturn(123L);
        when(urlRepository.findById(123L)).thenReturn(Optional<>)
        String longUrl = urlService.getLongUrl("xyzabc");
        assertEquals("http://www.nueda.com", longUrl);
    }
}
