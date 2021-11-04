package com.cidenetshop.service.impl;

import com.cidenetshop.model.Product;
import com.cidenetshop.repository.ProductRepository;
import com.cidenetshop.service.api.ProductServiceAPI;
import dto.GetProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceAPI {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) throws Exception {

        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new Exception("El producto requiere un precio mayor que cero.");
        }

        try {
            return this.productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Ha ocurrido un error inesperado al guardar el producto en la base de datos.");
        }
    }

    @Override
    public GetProductDTO findProductById(Long productId) throws Exception {
        final Optional<Product> repoResponse = this.productRepository.findById(productId);

        if (repoResponse.isEmpty()) {
            throw new Exception("Producto no encontrado para el id " + productId);
        }

        final Product productFound = repoResponse.get();

        final GetProductDTO dto = new GetProductDTO();
        dto.setName(productFound.getName());
        dto.setSize(productFound.getSize().getShortText());

        return dto;
    }

    @Override
    public Boolean deleteProductById(Long productId) throws Exception {

        if (findProductById(productId) != null) {
            try {
                this.productRepository.deleteById(productId);
                return true;
            } catch (Exception e) {
                throw new Exception("Hubo un error eliminando el producto, intentalo nuevamente mas tarde");

            }
        }
        return false;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
