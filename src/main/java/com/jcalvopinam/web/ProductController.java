package com.jcalvopinam.web;

import com.jcalvopinam.domain.Product;
import com.jcalvopinam.dto.ProductDTO;
import com.jcalvopinam.service.ProductService;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private static final String WELCOME_PRODUCT_ENTITY = "Welcome to Envers example: Products Entity";

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String init() {
        return WELCOME_PRODUCT_ENTITY;
    }

    @RequestMapping(value = "/find-all-products", method = RequestMethod.GET)
    public List<Product> findAllProducts() {
        logger.info("Find all products");
        return productService.findAll();
    }

    @RequestMapping(value = "/find-by-product", method = RequestMethod.GET)
    public Product findByText(@RequestParam(value = "text") String text) {
        logger.info(String.format("Finding by: %s", text));
        return productService.findByText(text, text);
    }

    @RequestMapping(value = "/save-product", method = RequestMethod.POST)
    public String saveProduct(@RequestBody ProductDTO productDTO) {
        Validate.notNull(productDTO, "The product cannot be null");
        logger.info(String.format("Saving product: %s", productDTO.toString()));
        return productService.save(productDTO);
    }

    @RequestMapping(value = "/update-product", method = RequestMethod.POST)
    public String updateProduct(@RequestBody ProductDTO productDTO) {
        Validate.notNull(productDTO, "The product cannot be null");
        logger.info(String.format("Updating product: %s", productDTO.toString()));
        return productService.update(productDTO);
    }

    @RequestMapping(value = "/delete-product", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam(value = "id") int id) {
        logger.info(String.format("Deleting product: %s", id));
        return productService.deleteById(id);
    }

}
