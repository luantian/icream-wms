package io.github.talelin.latticy.common.enumeration;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum TaskStatusEnum implements IEnum<Integer> {
    /**
     * 草稿
     */
    DRAFT(1),
    /**
     * 申请中
     */
    ASKING(2),
    /**
     * 已拒绝
     */
    REJECTED(3),
    /**
     * 已完成
     */
    COMPLETED(4);

    private final Integer value;

    TaskStatusEnum(Integer value) {
        this.value = value;
    }

    /**
     * MybatisEnumTypeHandler 转换时调用此方法
     *
     * @return 枚举对应的 code 值
     * @see com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler
     */
    @Override
    public Integer getValue() {
        return this.value;
    }
}
