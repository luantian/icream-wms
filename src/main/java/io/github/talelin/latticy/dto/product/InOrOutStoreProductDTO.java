package io.github.talelin.latticy.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class InOrOutStoreProductDTO {

    @NotNull(message = "{product.count.not-null}")
    @Positive(message = "{product.count.positive}")
    private Integer count;

}
