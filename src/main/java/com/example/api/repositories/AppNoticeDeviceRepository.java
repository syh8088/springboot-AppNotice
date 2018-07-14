package com.example.api.repositories;

import com.example.api.entities.AppNotice;
import com.example.api.entities.AppNoticeDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

public interface AppNoticeDeviceRepository extends JpaRepository<AppNoticeDevice, Integer> {

    // NOTE 조금 복잡한 쿼리는 Mybatis 혹은 QueryDSL 을 사용하는게 편할 거에요.

    List<AppNoticeDevice> findByTypeAndAppNotice_CategoryAndAppNotice_ReserveAtBeforeOrderByAppNotice_ReserveAtDesc
            (AppNoticeDevice.Type type, AppNotice.Category category, LocalDateTime localDateTime, Pageable pageable);

    List<AppNoticeDevice> findByTypeAndPopupAllowedAndAppNotice_ReserveAtBeforeAndPopupStartDateBeforeAndPopupEndDateAfterOrderByAppNotice_ReserveAtDesc
            (AppNoticeDevice.Type type, boolean popup, LocalDateTime reserverDate, LocalDateTime popupStart, LocalDateTime popupEnd, Pageable pageable);
}
