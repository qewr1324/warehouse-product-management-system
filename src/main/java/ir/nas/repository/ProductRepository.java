package ir.nas.repository;

import java.util.Set;

import ir.nas.model.Product;

public interface ProductRepository extends Repository<Integer, Product>
{
    Set<Product> findBelowsByQuantity(int quantity);
}
