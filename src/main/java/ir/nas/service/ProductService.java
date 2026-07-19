package ir.nas.service;

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

    private void validProduct(Product p)
    {
        Validator.of()
                .requireNotNull(p.getName())
                .requireNotNegativeNumber(p.getPrice())
                .requireNotNegativeNumber(p.getQuantity())
                .validate();
    }

    private void validProductId(int id)
    {
        Validator.of()
                .requireNotNegativeNumber(id)
                .validate();
    }

    
}
