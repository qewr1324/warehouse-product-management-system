package ir.nas.util;

import java.util.Comparator;

import ir.nas.model.Product;
import ir.nas.service.ProductService;

/**
 * Reports
 */
public class Reports
{
    private final ProductService pService;

    public Reports(final ProductService pService)
    {
        this.pService = pService;
    }

    public long totalNumberOfProducts()
    {
        return this.pService.allProducts()
                .stream()
                .count();
    }

    public double avarageProductPrice()
    {
        return this.pService.allProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public Product mostExpensiveProduct()
    {
        return this.pService.allProducts()
                .stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse((Product) null);
    }
}