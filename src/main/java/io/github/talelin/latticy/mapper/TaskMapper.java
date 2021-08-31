package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.ProductDO;
import io.github.talelin.latticy.model.TaskDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskMapper extends BaseMapper<TaskDO> {
    TaskDO selectByIdAndUserId(Integer id, Integer userId);
    int selectCountByIdAndUserId(Integer id, Integer userId);
    int selectCountByTitleAndUserId(String title, Integer userId);
    List<ProductDO> selectByTitleLikeKeyword(@Param("q") String q);
}
