package ir.nas.repository;

import java.util.Optional;
import java.util.Set;

import ir.nas.model.Product;

public final class ProductRepository implements Repository<Integer, Product>
{
    @Override
    public Optional<Product> delete(Integer id)
    {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Set<Product> findAll()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Product> read(Integer id)
    {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Integer save(Product t)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int update(Product t)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
