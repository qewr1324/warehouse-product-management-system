package ir.nas.repository;

import java.util.Optional;
import java.util.Set;

import ir.nas.model.Supplier;

public final class SupplierRepository implements Repository<Integer, Supplier>
{
    @Override
    public Optional<Supplier> delete(Integer id)
    {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Set<Supplier> findAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Supplier> read(Integer id)
    {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Integer save(Supplier t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(Supplier t)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
