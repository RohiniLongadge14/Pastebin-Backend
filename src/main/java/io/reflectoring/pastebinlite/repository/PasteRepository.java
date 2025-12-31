package io.reflectoring.pastebinlite.repository;

import io.reflectoring.pastebinlite.bean.Paste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasteRepository extends JpaRepository<Paste, UUID> {
}
