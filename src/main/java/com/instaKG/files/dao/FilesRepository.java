package com.instaKG.files.dao;

import com.instaKG.files.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Long> {
}
