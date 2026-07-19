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

    public void printReports()
    {
        System.out.println("Total Number Of Product: " + this.totalNumberOfProducts());
        System.out.println("Avarage Product Price: " + this.avarageProductPrice());
        System.out.println("Most Expensive Product: " + this.mostExpensiveProduct());
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