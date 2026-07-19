package ir.nas.service;

import ir.nas.model.Supplier;
import ir.nas.repository.impl.SupplierRepositoryImpl;
import ir.nas.util.validation.Validator;

public final class SupplierService
{
    private final SupplierRepositoryImpl sRepository;

    public SupplierService(final SupplierRepositoryImpl sRepository)
    {
        this.sRepository = sRepository;
    }

    private void validProduct(Supplier p)
    {
        Validator.of()
                .requireNotNull(p.getCompanyName())
                .requireNotNull(p.getPhone())
                .validate();
    }

    private void validProductId(int id)
    {
        Validator.of()
                .requireNotNegativeNumber(id)
                .validate();
    }
}
