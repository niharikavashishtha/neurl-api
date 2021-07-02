package com.neueda.neurl.service;

import com.neueda.neurl.dto.LongUrlDTO;
import com.neueda.neurl.entity.UrlEntity;
import com.neueda.neurl.repository.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;


@Service
@AllArgsConstructor
public class UrlService {
    private UrlRepository urlRepository;
    private Base62Service base62Service;
    public String getLongUrl(String shortUrl){
       Long id = base62Service.decode(shortUrl);
        UrlEntity urlEntity =
        urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));
       return urlEntity.getLongUrl();
    }

    public String toShortUrl(LongUrlDTO longURLDto){
        UrlEntity urlEntity = UrlEntity.builder().longUrl(longURLDto.getLongUrl())
                .createdDate(new Date()).build();
        urlEntity = urlRepository.save(urlEntity);
        return base62Service.encode(urlEntity.getId());

    }
}
