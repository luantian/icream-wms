package io.github.talelin.latticy.controller.v1;

import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import io.github.talelin.latticy.dto.product.CreateOrUpdateProductDTO;
import io.github.talelin.latticy.dto.product.InOrOutStoreProductDTO;
import io.github.talelin.latticy.model.ProductDO;
import io.github.talelin.latticy.service.ProductService;
import io.github.talelin.latticy.vo.CreatedVO;
import io.github.talelin.latticy.vo.DeletedVO;
import io.github.talelin.latticy.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/product")
@PermissionModule(value = "产品")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GroupRequired
    @PostMapping("")
    @PermissionMeta(value = "创建产品")
    public CreatedVO create(@RequestBody @Validated CreateOrUpdateProductDTO validator) {
        productService.create(validator);
        return new CreatedVO();
    }

    @DeleteMapping("/{id}")
    @GroupRequired
    @PermissionMeta(value = "删除产品")
    public DeletedVO delete(@PathVariable @Positive(message = "{id.positive}") Integer id) {
        ProductDO product = productService.getById(id);
        if (product == null) {
            throw new NotFoundException(15000);
        }
        productService.deleteById(product.getId());
        return new DeletedVO();
    }

    @PutMapping("/{id}")
    @GroupRequired
    public UpdatedVO update(
            @PathVariable @Positive(message = "{id.positive}") Integer id,
            @RequestBody @Validated CreateOrUpdateProductDTO validator
    ) {
        ProductDO product = productService.getById(id);
        if (product == null) {
            throw new NotFoundException(11004);
        }
        productService.updateProduct(product, validator);
        return new UpdatedVO(11006);
    }

    @PutMapping("/inStore/{id}")
    @GroupRequired
    public UpdatedVO inStore(
            @PathVariable @Positive(message = "{id.positive}") Integer id,
            @RequestBody @Validated InOrOutStoreProductDTO validator
    ) {
        ProductDO product = productService.getById(id);
        if (product == null) {
            throw new NotFoundException(11004);
        }
        productService.inStore(product, validator);
        return new UpdatedVO(11007);
    }

    @PutMapping("/outStore/{id}")
    @GroupRequired
    public UpdatedVO outStore(
            @PathVariable @Positive(message = "{id.positive}") Integer id,
            @RequestBody @Validated InOrOutStoreProductDTO validator
    ) {
        ProductDO product = productService.getById(id);
        if (product == null) {
            throw new NotFoundException(11004);
        }
        productService.outStore(product, validator);
        return new UpdatedVO(11008);
    }

    @GetMapping("/{id}")
    public ProductDO getProduct(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        ProductDO product = productService.getById(id);
        if (product == null) {
            throw new NotFoundException(11004);
        }
        return product;
    }

    @GetMapping("")
    public List<ProductDO> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/search")
    public List<ProductDO> searchProduct(@RequestParam(value = "q", required = false, defaultValue = "") String q) {
        return productService.getProductByKeyword("%" + q + "%");
    }

}
