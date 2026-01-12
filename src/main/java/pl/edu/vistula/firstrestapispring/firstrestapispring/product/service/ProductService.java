package pl.edu.vistula.firstrestapispring.firstrestapispring.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.api.request.ProductRequest;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.api.request.UpdateProductRequest;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.api.response.ProductResponse;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.domain.Product;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.repository.ProductRepository;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.support.ProductMapper;
import pl.edu.vistula.firstrestapispring.firstrestapispring.product.support.exception.ProductNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductResponse create(ProductRequest productRequest) {
        Product product = productRepository.save(productMapper.toProduct(productRequest));
        return productMapper.toProductResponse(product);
    }
    public ProductResponse find(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse update(Long id, UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(updateProductRequest.getName());
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }
}