package ir.nas.service;

import ir.nas.repository.impl.SupplierRepositoryImpl;

public final class SupplierService
{
    private final SupplierRepositoryImpl sRepository;

    public SupplierService(final SupplierRepositoryImpl sRepository)
    {
        this.sRepository = sRepository;
    }

    
}
