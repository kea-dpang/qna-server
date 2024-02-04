package kea.dpang.qna.client;

import kea.dpang.qna.base.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "item-server")
public interface ItemServiceClient {
    @GetMapping("api/items/findName")
    ResponseEntity<SuccessResponse<String>> getItemName(@RequestParam Long itemId);
}
