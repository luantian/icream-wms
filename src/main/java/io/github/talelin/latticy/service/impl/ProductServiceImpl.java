package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.autoconfigure.exception.ParameterException;
import io.github.talelin.latticy.dto.product.CreateOrUpdateProductDTO;
import io.github.talelin.latticy.dto.product.InOrOutStoreProductDTO;
import io.github.talelin.latticy.mapper.ProductMapper;
import io.github.talelin.latticy.model.ProductDO;
import io.github.talelin.latticy.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductDO> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public ProductDO create(CreateOrUpdateProductDTO dto) {
        boolean exist = this.checkProductExistByName(dto.getName());
        if (exist) {
            throw new NotFoundException(11005);
        }

        ProductDO product = new ProductDO();
        BeanUtils.copyProperties(dto, product);
        productMapper.insert(product);

        return product;
    }

    @Transactional
    @Override
    public boolean deleteById(Integer id) {
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateProduct(ProductDO product, CreateOrUpdateProductDTO validator) {
        product.setName(validator.getName());
        product.setCount(validator.getCount());
        product.setDescription(validator.getDescription());
        product.setImg(validator.getImg());
        product.setPrice(validator.getPrice());
        return productMapper.updateById(product) > 0;
    }

    @Override
    public ProductDO getById(Integer id) {
        return productMapper.selectById(id);
    }

    @Override
    public List<ProductDO> findAll() {
        return productMapper.selectList(null);
    }

    @Override
    public List<ProductDO> getProductByKeyword(String q) {
        return productMapper.selectByNameLikeKeyword(q);
    }

    @Override
    public boolean inStore(ProductDO product, InOrOutStoreProductDTO validator) {
        product.setCount(product.getCount() + validator.getCount());
        return productMapper.updateById(product) > 0;
    }

    @Override
    public boolean outStore(ProductDO product, InOrOutStoreProductDTO validator) {
        int current = product.getCount() - validator.getCount();
        if (current < 0) {
            throw new ParameterException(11009);
        }
        product.setCount(current);
        return productMapper.updateById(product) > 0;
    }

    public boolean checkProductExistByName(String name) {
        int rows = productMapper.selectCountByName(name);
        return rows > 0;
    }

    public boolean checkProductExistById(Integer id) {
        int rows = productMapper.selectCountById(id);
        return rows > 0;
    }
}
