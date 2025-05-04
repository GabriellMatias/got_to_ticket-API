package com.gototicket.api.domain.coupon;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public record CouponRequestDTO(

        @NotBlank(message = "O código do cupom é obrigatório")
        String code,

        @NotNull(message = "O desconto é obrigatório")
        @Min(value = 1, message = "O desconto mínimo é 1%")
        @Max(value = 100, message = "O desconto máximo é 100%")
        Integer discount,

        @NotNull(message = "A data de validade é obrigatória")
        @Future(message = "A data de validade deve ser no futuro")
        Date valid,

        @NotNull(message = "O ID do evento é obrigatório")
        UUID eventId

) {}
