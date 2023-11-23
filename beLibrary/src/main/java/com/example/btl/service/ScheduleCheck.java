package com.example.btl.service;

import com.example.btl.entity.BorrowQueueDetail;
import com.example.btl.entity.Notification;
import com.example.btl.entity.QueueBorrow;
import com.example.btl.repository.CallCardRepository;
import com.example.btl.repository.FineRepository;
import com.example.btl.repository.NotificationRepository;
import com.example.btl.repository.QueueDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleCheck {
    private final QueueDetailRepository queueDetailRepository;
    private final CallCardRepository callCardRepository;
    private final FineRepository fineRepository;
    private final NotificationRepository notificationRepository;

    @Scheduled(cron = "0 0 8 * * ?") // 8h
    public void checkQueueSchedule() {
        Calendar calendar = Calendar.getInstance();
        String yesterday = calendar.get(Calendar.DAY_OF_MONTH) - 1 + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        String today = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        List<BorrowQueueDetail> queueDetailList = queueDetailRepository.findByOverdueDate(yesterday);
        for (BorrowQueueDetail queueDetail : queueDetailList) {

            queueDetailRepository.delete(queueDetail);
            queueDetailRepository.increasePosition(queueDetail.getQueueBorrowId().getId().intValue());
            QueueBorrow queueBorrow = queueDetail.getQueueBorrowId();

            if (callCardRepository.findByBorrowDateAndBookId(yesterday,
                    Long.valueOf(queueBorrow.getBook().getId()),
                    Long.valueOf(queueDetail.getUser().getId())) == null) {

                fineRepository.increaseMissingBorrow(Long.valueOf(queueDetail.getUser().getId()));


                Notification notification = new Notification();
                notification.setUserId(Long.valueOf(queueDetail.getUser().getId()));
                notification.setContent("Bạn đã bỏ lỡ mượn sách " + queueBorrow.getBook().getTitle() + " vào ngày " + yesterday);
                notification.setDate(today);
                notification.setTitle("Thông báo");
                notificationRepository.save(notification);
            }

        }
        List<BorrowQueueDetail> queueDetailList1 = queueDetailRepository.findByOverdueDate(today);
        for (BorrowQueueDetail queueDetail : queueDetailList1) {

            QueueBorrow queueBorrow = queueDetail.getQueueBorrowId();

            if (callCardRepository.findByBorrowDateAndBookId(today,
                    Long.valueOf(queueBorrow.getBook().getId()),
                    Long.valueOf(queueDetail.getUser().getId())) == null) {

                Notification notification = new Notification();
                notification.setUserId(Long.valueOf(queueDetail.getUser().getId()));
                notification.setContent("Bạn có lịch hẹn mượn sách " + queueBorrow.getBook().getTitle() + " vào hôm nay. Vui lòng đến thư viện để mượn sách.");
                notification.setDate(today);
                notification.setTitle("Thông báo");
                notificationRepository.save(notification);
            }

        }

    }


}
