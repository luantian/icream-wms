package io.github.talelin.latticy.dto.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateOrUpdateTaskDTO {
    @NotBlank(message = "{task.title.not-blank}")
    private String title;
}
