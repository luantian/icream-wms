package io.github.talelin.latticy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.latticy.dto.product.CreateOrUpdateProductDTO;
import io.github.talelin.latticy.dto.product.InOrOutStoreProductDTO;
import io.github.talelin.latticy.model.ProductDO;

import java.util.List;

public interface ProductService extends IService<ProductDO> {

    /**
     * 创建产品
     * @param validator
     * @return
     */
    ProductDO create(CreateOrUpdateProductDTO validator);

    /**
     * 删除产品
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    boolean updateProduct(ProductDO product, CreateOrUpdateProductDTO validator);

    boolean inStore(ProductDO product, InOrOutStoreProductDTO validator);
    boolean outStore(ProductDO product, InOrOutStoreProductDTO validator);

//    boolean isExist(Integer id);

    /**
     * 查询产品 通过id
     */
    ProductDO getById(Integer id);

    List<ProductDO> findAll();

    List<ProductDO> getProductByKeyword(String keyword);

}
