package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("product")
@EqualsAndHashCode(callSuper = true)
public class ProductDO extends BaseModel {
    /**
     * 唯一
     */
    private String name;

    /**
     * 产品数量
     */
    private Integer count;

    /**
     * 产品单位
     */
    private String unit;

    /**
     * 产品价格
     */
    private Integer price;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 产品图片
     */
    private String img;

}
