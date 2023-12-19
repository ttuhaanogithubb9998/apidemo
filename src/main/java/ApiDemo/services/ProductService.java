package ApiDemo.services;

import ApiDemo.entities.Product;
import ApiDemo.repositories.ProductRepository;
import ApiDemo.utils.FilesProcessor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product customer) {
        if (customer.getFile() != null) {
            String image = null;
            try {
                image = FilesProcessor.saveFileByMultiPart(customer.getFile(), "/files");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            customer.setImage(image);
        }
        return productRepository.save(customer);
    }

    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        try {
            FilesProcessor.deleteFile(product.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productRepository.deleteById(id);
    }
}
