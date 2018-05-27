package com.example.api.repositories;

import com.example.api.entities.AppNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppNoticeRepository extends JpaRepository<AppNotice, Long> {
}

