package ir.nas.service;

import ir.nas.repository.impl.ProductRepositoryImpl;

public final class ProductService
{
    private final ProductRepositoryImpl pRepository;

    public ProductService(final ProductRepositoryImpl pRepository)
    {
        this.pRepository = pRepository;
    }

    
}
