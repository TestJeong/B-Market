package com.side.bmarket.domain.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDto {
    @Size(min =1, message = "Id값이 최소 한개는 있어야 합니다.")
    private List<Long> cartItemId;
}
