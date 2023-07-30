package summer.book_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import summer.book_shop.domain.dto.OrderRequestDto;
import summer.book_shop.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    //주문 성공, 수정 성공, 삭제 성공
    private final OrderService orderService;

    //주문 성공
    @GetMapping("/order/new")
    public String OrderRequestDto(){
        return "templates/order";
    }
    @PostMapping("/order/save")
    public String orderSave(OrderRequestDto requestDto) {
        try {
            OrderRequestDto orderRequestDto = new OrderRequestDto();
            orderRequestDto.setOrder_name(orderRequestDto.getOrder_name());
            orderRequestDto.setShipping_address(orderRequestDto.getShipping_address());
            orderRequestDto.setBookCode(orderRequestDto.getBookCode());
            orderRequestDto.setProductCount(orderRequestDto.getProductCount());

            orderService.orderState(requestDto.toEntity());
            System.out.println("주문 접수 완료되었습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "주문 접수 오류.");
        }
        return "redirect:/";
    }
    //삭제 성공
    @PostMapping("/order/cancel")
    public void cancel(String orderCode) {
        try {
            orderService.cancelOrder(orderCode);
            System.out.println("delete ok");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "삭제 실패");
        }
    }
}
