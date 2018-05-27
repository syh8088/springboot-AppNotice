package com.example.api.service;

import com.example.api.entities.AppNotice;
import com.example.api.entities.AppNoticeDevice;
import com.example.api.repositories.AppNoticeDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private AppNoticeDeviceRepository appNoticeDeviceRepository;

    public List<AppNoticeDevice> getAppNoticeList(AppNoticeDevice.Type deviceType, AppNotice.Category category, Pageable pageable, boolean popup) throws Exception {

        System.out.println(deviceType);
        System.out.println(category);
        System.out.println(pageable);
        System.out.println(popup);


        List<AppNoticeDevice> noticeList;
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        noticeList =
                appNoticeDeviceRepository.
                        findByTypeAndAppNotice_CategoryAndAppNotice_ReserveAtBeforeOrderByAppNotice_ReserveAtDesc
                                (deviceType, category, localDateTime, pageable);

//
//        noticeList = appNoticeDeviceRepository.findByTypeAndPopupAllowedAndAppNotice_ReserveAtBeforeAndPopupStartDateBeforeAndPopupEndDateAfterOrderByAppNotice_ReserveAtDesc
//                (deviceType, popup, localDateTime, localDateTime, localDateTime, pageable);

        System.out.println(noticeList);
        return noticeList;
    }


}
