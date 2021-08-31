package io.github.talelin.latticy.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("task")
@EqualsAndHashCode(callSuper = true)
public class TaskDO extends BaseModel {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务状态
     * 1=>草稿, 2=>申请, 3=>拒绝，4=>完成
     */
    private Integer status;
}