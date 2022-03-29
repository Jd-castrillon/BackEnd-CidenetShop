package com.cidenetshop.service.impl;

import com.cidenetshop.model.dto.GetAdminProductDTO;
import com.cidenetshop.model.dto.GetProductDTO;
import com.cidenetshop.model.dto.NewProductDTO;
import com.cidenetshop.model.entity.ExistingQuantity;
import com.cidenetshop.model.entity.Product;
import com.cidenetshop.repository.ExistingQuantityRepository;
import com.cidenetshop.repository.ProductRepository;
import com.cidenetshop.service.api.GenderServiceAPI;
import com.cidenetshop.service.api.PictureServiceAPI;
import com.cidenetshop.service.api.ProductServiceAPI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceAPI {

    private final ProductRepository productRepository;

    private final GenderServiceAPI genderServiceAPI;

    private final PictureServiceAPI pictureServiceAPI;

    private final ExistingQuantityRepository existingQuantityRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, GenderServiceAPI genderServiceAPI,
                              PictureServiceAPI pictureServiceAPI, ExistingQuantityRepository existingQuantityRepository,
                              ModelMapper modelMapper) {
        super();
        this.productRepository = productRepository;
        this.genderServiceAPI = genderServiceAPI;
        this.pictureServiceAPI = pictureServiceAPI;
        this.existingQuantityRepository = existingQuantityRepository;
        this.modelMapper = modelMapper;
    }

    private GetProductDTO convertProductToDTO(Product product) throws Exception {

        ModelMapper modelMapper = new ModelMapper();

        GetProductDTO getProductDTO = modelMapper.map(product, GetProductDTO.class);

        return getProductDTO;
    }

    public Product findById(Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new Exception("Producto no encontrado para el id " + productId);

        }
        return product.get();
    }

    ;

    @Transactional
    @Override
    public GetProductDTO findProductById(Long productId) throws Exception {

        final Optional<Product> repoResponse = this.productRepository.findById(productId);

        if (repoResponse.isEmpty()) {
            throw new Exception("Producto no encontrado para el id " + productId);
        }

        final Product productFound = repoResponse.get();

        Long first = (long) 1;

        if (productFound.getSearches() != null) {
            productFound.setSearches(productFound.getSearches() + 1);
        } else {
            productFound.setSearches(first);
        }

        return convertProductToDTO(productFound);
    }

    @Override
    public void deleteProductById(Long productId) throws Exception {

        List<ExistingQuantity> existingQuantities = existingQuantityRepository.findByProductId(productId);

        if (!existingQuantities.isEmpty())
            throw new Exception("Don't delete product with stock");

        Product product = findById(productId);

        this.productRepository.delete(product);

    }

    @Override
    public List<GetAdminProductDTO> getAllProducts() {

        List<GetAdminProductDTO> productsDTO = new ArrayList<>();
        this.productRepository.findAll().forEach(obj -> {
            try {
                GetAdminProductDTO product = modelMapper.map(obj, GetAdminProductDTO.class);

                productsDTO.add(product);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return productsDTO;
    }

    public List<GetAdminProductDTO> getProductByGender(String gender) {

        List<GetAdminProductDTO> productsDTO = new ArrayList<>();

        this.productRepository.findAllByGender(gender).forEach(obj -> {
            try {

                GetAdminProductDTO product = modelMapper.map(obj, GetAdminProductDTO.class);
                productsDTO.add(product);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return productsDTO;
    }

    @Override
    public List<GetProductDTO> RankingOfProducts() {

        List<Product> AllProducts = (List<Product>) productRepository.findAll();

        AllProducts.sort((p1, p2) -> p1.getSearches().compareTo(p2.getSearches()));

        Collections.reverse(AllProducts);

        List<GetProductDTO> productsRankingDTO = new ArrayList<>();

        AllProducts.forEach(obj -> {
            try {
                if (obj.getActive().equals(1)) {
                    productsRankingDTO.add(convertProductToDTO(obj));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return productsRankingDTO.subList(0, 8);
    }

    @Override
    public List<GetProductDTO> getActiveProductByGender(String gender) {

        List<GetProductDTO> productsDTO = new ArrayList<>();

        this.productRepository.findAllByGender(gender).forEach(obj -> {
            try {
                if (obj.getActive().equals(1)) {

                    productsDTO.add(convertProductToDTO(obj));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return productsDTO;
    }

    @Override
    public void saveNewProduct(NewProductDTO newProduct, MultipartFile newPicture) throws Exception {

        if (findByName(newProduct.getName()).isPresent())
            throw new Exception("Product already exists");

        final Product product = new Product();

        if (newProduct.getName().equals(null) || newProduct.getName().equals(""))
            throw new Exception("Name needed");

        if (newProduct.getDescription().equals(null) || newProduct.getDescription().equals(""))
            throw new Exception("Description needed");

        if (newProduct.getBrand().equals(null) || newProduct.getBrand().equals(""))
            throw new Exception("Brand needed");

        if (newProduct.getColor().equals(null) || newProduct.getColor().equals(""))
            throw new Exception("color needed");

        if (newProduct.getPrice().equals(null) || newProduct.getPrice() <= 0)
            throw new Exception("incorrect price");

        product.setName(newProduct.getName());

        product.setDescription(newProduct.getDescription());

        product.setColor(newProduct.getColor());

        product.setPrice(newProduct.getPrice());

        product.setBrand(newProduct.getBrand());

        product.setGender(genderServiceAPI.findByGender(newProduct.getGender()));

        product.setSearches((long) 0);

        product.setActive(0);

        pictureServiceAPI.savePicture(product, newPicture);

        productRepository.save(product);

    }

    @Transactional
    @Override
    public void updateProduct(NewProductDTO updateProduct, MultipartFile updatePicture, Long idProduct)
            throws Exception {

        if (updateProduct.getName().equals(null) || updateProduct.getName().equals(""))
            throw new Exception("Name needed");

        if (updateProduct.getDescription().equals(null) || updateProduct.getDescription().equals(""))
            throw new Exception("Description needed");

        if (updateProduct.getBrand().equals(null) || updateProduct.getBrand().equals(""))
            throw new Exception("Brand needed");

        if (updateProduct.getColor().equals(null) || updateProduct.getColor().equals(""))
            throw new Exception("color needed");

        if (updateProduct.getPrice().equals(null) || updateProduct.getPrice() <= 0)
            throw new Exception("incorrect price");

        Product product = findById(idProduct);

        product.setName(updateProduct.getName());
        product.setDescription(updateProduct.getDescription());
        product.setColor(updateProduct.getColor());
        product.setBrand(updateProduct.getBrand());
        product.setGender(genderServiceAPI.findByGender(updateProduct.getGender()));
        product.setPrice(updateProduct.getPrice());

        if (!updatePicture.isEmpty()) {
            pictureServiceAPI.updatePicture(idProduct, updatePicture);
        }

    }

    @Override
    public Optional<Product> findByName(String name) throws Exception {

        if (name.equals(null) || name.equals(""))
            throw new Exception("Product name is invalid");

        Optional<Product> response = productRepository.findByName(name);

        return response;
    }

    @Override
    public List<GetProductDTO> getActiveProducts() {

        List<GetProductDTO> productsDTO = new ArrayList<>();
        this.productRepository.findAll().forEach(obj -> {
            try {
                if (obj.getActive().equals(1)) {
                    productsDTO.add(convertProductToDTO(obj));
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return productsDTO;
    }



}
