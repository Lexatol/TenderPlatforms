package ru.lexp00.platform.tptenders.services.tenders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lexp00.platform.tpcommon.dtos.chat.NewMessage;

@FeignClient("tp-chat")
public interface ChatClient {

    @PutMapping("api/v1/chat/send")
    public void send(@RequestBody NewMessage message);
}
