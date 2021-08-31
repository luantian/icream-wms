package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.dto.task.CreateOrUpdateTaskDTO;
import io.github.talelin.latticy.model.TaskDO;

public interface TaskService extends IService<TaskDO> {
    /**
     * 创建任务
     * @param validator
     * @return
     */
    TaskDO create(CreateOrUpdateTaskDTO validator);

    /**
     * 删除任务
     * @param id 任务id
     * @return 布尔值
     */
    boolean deleteById(Integer id);

    TaskDO getById(Integer id);
}
