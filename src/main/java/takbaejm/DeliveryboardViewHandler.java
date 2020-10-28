package takbaejm;

import takbaejm.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryboardViewHandler {


    @Autowired
    private DeliveryboardRepository deliveryboardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_CREATE_1 (@Payload Requested requested) {
        try {
            if (requested.isMe()) {
                // view 객체 생성
                Deliveryboard deliveryboard = new Deliveryboard();
                // view 객체에 이벤트의 Value 를 set 함
                deliveryboard.setRequestId(requested.getId());
                deliveryboard.setStatus(requested.getStatus());
                // view 레파지 토리에 save
                deliveryboardRepository.save(deliveryboard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReqCanceled_then_UPDATE_1(@Payload ReqCanceled reqCanceled) {
        try {
            if (reqCanceled.isMe()) {
                // view 객체 조회
                List<Deliveryboard> deliveryboardList = deliveryboardRepository.findByRequestId(reqCanceled.getId());
                for(Deliveryboard deliveryboard : deliveryboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryboard.setStatus(reqCanceled.getStatus());
                    // view 레파지 토리에 save
                    deliveryboardRepository.save(deliveryboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_2(@Payload Paid paid) {
        try {
            if (paid.isMe()) {
                // view 객체 조회
                List<Deliveryboard> deliveryboardList = deliveryboardRepository.findByRequestId(paid.getRequestId());
                for(Deliveryboard deliveryboard : deliveryboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryboard.setStatus(paid.getStatus());
                    // view 레파지 토리에 save
                    deliveryboardRepository.save(deliveryboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCanceled_then_UPDATE_3(@Payload PayCanceled payCanceled) {
        try {
            if (payCanceled.isMe()) {
                // view 객체 조회
                List<Deliveryboard> deliveryboardList = deliveryboardRepository.findByRequestId(payCanceled.getRequestId());
                for(Deliveryboard deliveryboard : deliveryboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryboard.setStatus(payCanceled.getStatus());
                    // view 레파지 토리에 save
                    deliveryboardRepository.save(deliveryboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_4(@Payload Delivered delivered) {
        try {
            if (delivered.isMe()) {
                // view 객체 조회
                List<Deliveryboard> deliveryboardList = deliveryboardRepository.findByRequestId(delivered.getRequestId());
                for(Deliveryboard deliveryboard : deliveryboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryboard.setStatus(delivered.getStatus());
                    deliveryboard.setLocation(delivered.getLocation());
                    deliveryboard.setCourierName(delivered.getCourierName());
                    deliveryboard.setLocation(delivered.getLocation());
                    // view 레파지 토리에 save
                    deliveryboardRepository.save(deliveryboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturned_then_UPDATE_5(@Payload Returned returned) {
        try {
            if (returned.isMe()) {
                // view 객체 조회
                List<Deliveryboard> deliveryboardList = deliveryboardRepository.findByRequestId(returned.getRequestId());
                for(Deliveryboard deliveryboard : deliveryboardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryboard.setReason(returned.getReason());
                    // view 레파지 토리에 save
                    deliveryboardRepository.save(deliveryboard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}