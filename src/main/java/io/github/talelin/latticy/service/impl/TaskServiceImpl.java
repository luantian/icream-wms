package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.latticy.common.LocalUser;
import io.github.talelin.latticy.common.enumeration.TaskStatusEnum;
import io.github.talelin.latticy.dto.task.CreateOrUpdateTaskDTO;
import io.github.talelin.latticy.mapper.TaskMapper;
import io.github.talelin.latticy.model.TaskDO;
import io.github.talelin.latticy.model.UserDO;
import io.github.talelin.latticy.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskDO> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public TaskDO create(CreateOrUpdateTaskDTO dto) {
        UserDO user = LocalUser.getLocalUser();
        int userId = user.getId();

        boolean exist = checkTaskExistByTitle(dto.getTitle(), userId);
        if (exist) {
            throw new NotFoundException(15001);
        }

        TaskDO task = new TaskDO();
        task.setUserId(userId);
        task.setStatus(TaskStatusEnum.DRAFT.getValue());
        BeanUtils.copyProperties(dto, task);
        taskMapper.insert(task);
        return task;
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        return taskMapper.deleteById(id) > 0;
    }

    @Override
    public TaskDO getById(Integer id) {
        UserDO user = LocalUser.getLocalUser();
        int userId = user.getId();
        return taskMapper.selectByIdAndUserId(id, userId);
    }

    public boolean checkTaskExistByTitle(String title, Integer userId) {
        int rows = taskMapper.selectCountByTitleAndUserId(title, userId);
        return rows > 0;
    }

    public boolean checkTaskExistById(Integer id, Integer userId) {
        int rows = taskMapper.selectCountByIdAndUserId(id, userId);
        return rows > 0;
    }

}
