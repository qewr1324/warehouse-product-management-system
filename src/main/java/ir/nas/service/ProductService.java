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
}
