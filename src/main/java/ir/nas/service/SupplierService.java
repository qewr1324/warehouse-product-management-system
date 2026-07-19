package ir.nas.service;

import java.util.Set;

import ir.nas.exception.service.InvalidDataException;
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

    private void validSupplier(Supplier p) throws InvalidDataException
    {
        Validator.of()
                .requireNotNull(p.getCompanyName())
                .requireNotNull(p.getPhone())
                .validate();
    }

    private void validSupplierId(int id) throws InvalidDataException
    {
        Validator.of()
                .requireNotNegativeNumber(id)
                .validate();
    }

    public Set<Supplier> allSuppliers()
    {
        return this.sRepository.findAll();
    }

    public int addSupplier(Supplier supplier)
    {
        try {
            this.validSupplier(supplier);
            return this.sRepository.save(supplier).intValue();

        } catch (InvalidDataException e) {
            System.out.println("Service Add Supplier Validation Error: ".concat(e.getMessage()));
            return -1;
        }
    }

    public Supplier findSupplier(int id)
    {
        try {
            this.validSupplierId(id);
            return this.sRepository.read(id).orElseGet(() -> {
                System.out.println("Supplier Not Find!");
                return (Supplier) null;
            });

        } catch (InvalidDataException e) {
            System.out.println("Service Update Supplier Validation Error: ".concat(e.getMessage()));
            return (Supplier) null;
        }
    }

    public boolean updateSupplier(Supplier supplier)
    {
        try {
            this.validSupplier(supplier);
            this.validSupplierId(supplier.getId());

            Supplier userSupplier = this.findSupplier(supplier.getId());
            if (userSupplier == null)
                return false;

            userSupplier.setCompanyName(supplier.getCompanyName());
            userSupplier.setPhone(supplier.getPhone());

            return this.sRepository.update(userSupplier);

        } catch (InvalidDataException e) {
            System.out.println("Service Update Supplier Validation Error: ".concat(e.getMessage()));
            return false;
        }
    }

    public Supplier deleteSupplier(int id)
    {
        try {
            this.validSupplierId(id);
            return this.sRepository.delete(id).orElseGet(() -> {
                System.out.println("Delete Error!");
                return (Supplier) null;
            });

        } catch (InvalidDataException e) {
            System.out.println("Service Delete Supplier Validation Error: ".concat(e.getMessage()));
            return (Supplier) null;
        }
    }
}
