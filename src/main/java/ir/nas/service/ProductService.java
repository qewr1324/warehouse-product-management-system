package ir.nas.service;

import java.util.Set;

import ir.nas.exception.service.InvalidDataException;
import ir.nas.model.Product;
import ir.nas.repository.ProductRepository;
import ir.nas.util.validation.Validator;

public final class ProductService
{
    private final ProductRepository pRepository;

    public ProductService(final ProductRepository pRepository)
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

    public Product findProduct(int id)
    {
        try {
            this.validProductId(id);
            return this.pRepository.read(id).orElseGet(() -> {
                System.out.println("Product Not Find!");
                return (Product) null;
            });

        } catch (InvalidDataException e) {
            System.out.println("Service Update Product Validation Error: ".concat(e.getMessage()));
            return (Product) null;
        }
    }

    public boolean updateProduct(Product product)
    {
        try {
            this.validProduct(product);
            this.validProductId(product.getId());

            Product userProduct = this.findProduct(product.getId());
            if (userProduct == null)
                return false;

            userProduct.setName(product.getName());
            userProduct.setPrice(product.getPrice());
            userProduct.setQuantity(product.getQuantity());

            return this.pRepository.update(userProduct);

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
