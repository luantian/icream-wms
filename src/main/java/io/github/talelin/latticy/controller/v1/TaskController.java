package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.dto.task.CreateOrUpdateTaskDTO;
import io.github.talelin.latticy.model.TaskDO;
import io.github.talelin.latticy.service.TaskService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @LoginRequired
    @PostMapping("")
    public CreatedVO create(@RequestBody @Validated CreateOrUpdateTaskDTO validator) {
        taskService.create(validator);
        return new CreatedVO();
    }

    @LoginRequired
    @DeleteMapping("/{id}")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {

        TaskDO task = taskService.getById(id);

        if (task == null) {
            throw new NotFoundException(15000);
        }

        taskService.deleteById(task.getId());
        return new DeletedVO();
    }
}
