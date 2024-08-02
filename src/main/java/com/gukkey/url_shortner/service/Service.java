package com.gukkey.url_shortner.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gukkey.url_shortner.model.ShortURL;
import com.gukkey.url_shortner.repository.URLRepository;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    ShortURL shortURL;

    @Autowired
    URLRepository urlRepository;
    
    public String generateShortCode(String url) {
        ShortURL newShortURL = new ShortURL();
        if(checkifURLexists(url)){
            int shortCode = Math.abs(url.hashCode());
            newShortURL.setOriginalURL(url);
            newShortURL.setShortCode(Integer.toString(shortCode));
            urlRepository.save(newShortURL);
            return newShortURL.getShortCode();
        }
        return "This URL is already present in the db: " + urlRepository.findByoriginalURL(url).getOriginalURL();
    }

    private boolean checkifURLexists(String url) {
        ShortURL currenShortURL = urlRepository.findByoriginalURL(url);
        if(currenShortURL == null) {
            return true;
        }
        return false;
    }

    public String retrieveURLMappings(String shortCode) {
        ShortURL currentShortURL = urlRepository.findByShortCode(shortCode);
        String url = currentShortURL.getOriginalURL();
        long accessCount = currentShortURL.getAccessCount() + 1;
        currentShortURL.setAccessCount(accessCount);
        urlRepository.save(currentShortURL);
        return url;
    }

    public long returnAccessCount(String shortCode) {
        ShortURL shortURL = urlRepository.findByShortCode(shortCode);
        long accessCount = shortURL.getAccessCount();
        return accessCount;
    }
}
