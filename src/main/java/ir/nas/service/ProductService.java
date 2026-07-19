package ir.nas.service;

import java.util.Set;

import ir.nas.exception.service.InvalidDataException;
import ir.nas.model.Product;
import ir.nas.repository.impl.ProductRepositoryImpl;
import ir.nas.util.validation.Validator;

public final class ProductService
{
    private final ProductRepositoryImpl pRepository;

    public ProductService(final ProductRepositoryImpl pRepository)
    {
        this.pRepository = pRepository;
    }

    private void validProduct(Product p) throws InvalidDataException
    {
        Validator.of()
                .requireNotNull(p.getName())
                .requireNotNegativeNumber(p.getPrice())
                .requireNotNegativeNumber(p.getQuantity())
                .validate();
    }

    private void validProductId(int id) throws InvalidDataException
    {
        Validator.of()
                .requireNotNegativeNumber(id)
                .validate();
    }

    public Set<Product> allProducts()
    {
        return this.pRepository.findAll();
    }

    public int addProduct(Product product)
    {
        try {
            this.validProduct(product);
            return this.pRepository.save(product).intValue();

        } catch (InvalidDataException e) {
            System.out.println("Service Add Product Validation Error: ".concat(e.getMessage()));
            return -1;
        }
    }

    public boolean updateProduct(Product product)
    {
        try {
            this.validProduct(product);
            return this.pRepository.update(product);

        } catch (InvalidDataException e) {
            System.out.println("Service Update Product Validation Error: ".concat(e.getMessage()));
            return false;
        }
    }

    public Product deleteProduct(int id)
    {
        try {
            this.validProductId(id);
            return this.pRepository.delete(id).orElseGet(() -> {
                System.out.println("Delete Error!");
                return (Product) null;
            });

        } catch (InvalidDataException e) {
            System.out.println("Service Delete Product Validation Error: ".concat(e.getMessage()));
            return (Product) null;
        }
    }
}
