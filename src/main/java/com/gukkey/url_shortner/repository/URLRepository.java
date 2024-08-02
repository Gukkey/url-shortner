package com.gukkey.url_shortner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gukkey.url_shortner.model.ShortURL;

@Repository
public interface URLRepository extends JpaRepository<ShortURL, Long> {
    ShortURL findByShortCode(String shortCode);

    ShortURL findByoriginalURL(String originalURL);
}
