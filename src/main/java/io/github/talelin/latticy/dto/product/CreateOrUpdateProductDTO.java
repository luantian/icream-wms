package io.github.talelin.latticy.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreateOrUpdateProductDTO {
    private Integer cid;

    @NotBlank(message = "{product.name.not-blank}")
    private String name;

    @NotNull(message = "{product.count.not-null}")
    @Positive(message = "{product.count.positive}")
    private Integer count;

    @NotBlank(message = "{product.unit.not-blank}")
    private String unit;

    @NotNull(message = "{product.price.not-null}")
    @Positive(message = "{product.price.positive}")
    private Integer price;

    private String img;
    private String description;
}
