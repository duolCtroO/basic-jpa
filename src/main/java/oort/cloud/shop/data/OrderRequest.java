package oort.cloud.shop.data;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import oort.cloud.shop.entity.item.Item;

@Data
public class OrderRequest {
    @NotNull(message = "회원의 Id는 필수 입니다.")
    private Long memberId;
    @NotNull(message = "아이템 Id는 필수 입니다")
    private Long itemId;
    @NotNull
    private int count;
}
