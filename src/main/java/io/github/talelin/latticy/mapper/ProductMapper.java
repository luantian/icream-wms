package io.github.talelin.latticy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.talelin.latticy.model.ProductDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<ProductDO> {
    int selectCountById(Integer id);
    int selectCountByName(String name);
    List<ProductDO> selectByNameLikeKeyword(@Param("q") String q);
}
