package io.reflectoring.pastebinlite.service;

import io.reflectoring.pastebinlite.bean.Paste;
import io.reflectoring.pastebinlite.components.TimeProvider;
import io.reflectoring.pastebinlite.repository.PasteRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PasteService {

    @Autowired
    private PasteRepository repo;

    @Autowired
    private TimeProvider timeProvider;

    public Paste create(String content, Integer ttlSeconds, Integer maxViews, HttpServletRequest req) {
        Paste p = new Paste();
        p.setContent(content);
        p.setCreatedAt(timeProvider.now(req));
        p.setViews(0);
        p.setMaxViews(maxViews);

        if (ttlSeconds != null) {
            p.setExpiresAt(timeProvider.now(req).plusSeconds(ttlSeconds));
        }

        return repo.save(p);
    }

    public Paste fetch(UUID id, HttpServletRequest req) {
        Paste p = repo.findById(id).orElseThrow();

        Instant now = timeProvider.now(req);

        // TTL check
        if (p.getExpiresAt() != null && now.isAfter(p.getExpiresAt())) {
            throw new NoSuchElementException();
        }

        // View limit check
        if (p.getMaxViews() != null && p.getViews() >= p.getMaxViews()) {
            throw new NoSuchElementException();
        }

        // Increment views
        p.setViews(p.getViews() + 1);
        repo.save(p);

        return p;
    }

}

