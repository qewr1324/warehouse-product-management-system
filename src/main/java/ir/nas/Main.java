package ir.nas;

import java.util.Scanner;
import java.util.Set;

import ir.nas.model.Product;
import ir.nas.model.Supplier;
import ir.nas.repository.ProductRepository;
import ir.nas.repository.impl.ProductRepositoryImpl;
import ir.nas.repository.impl.SupplierRepositoryImpl;
import ir.nas.service.ProductService;
import ir.nas.service.SupplierService;
import ir.nas.util.Reports;
import ir.nas.util.task.Task;

public class Main
{
    private static boolean isRunning = true;
    private static Scanner scanner = new Scanner(System.in);

    private static ProductRepository pRepository = new ProductRepositoryImpl();
    private static SupplierRepositoryImpl sRepository = new SupplierRepositoryImpl();

    private static ProductService pService = new ProductService(pRepository);
    private static SupplierService sService = new SupplierService(sRepository);

    private static Reports reports = new Reports(pService);
    private static Task task = new Task(pService);

    public static void main(String[] args)
    {
        while (isRunning) {
            try {

                System.out.println("""
                        Please Enter Your Operation Number:
                            1. Add Product
                            2. Show All Products
                            3. Update Product
                            4. Delete Product
                            5. Add Supplier
                            6. Show All Suppliers
                            7. Update Supplier
                            8. Delete Supplier
                            9. Reports
                            10. Thread Simulation
                            11. Exit
                            """);

                int userOperation = scanner.nextInt();

                switch (userOperation) {
                    case 1 -> addProduct(); // 1. Add Product
                    case 2 -> showAllProduct(); // 2. Show All Products
                    case 3 -> updateProduct(); // 3. Update Product
                    case 4 -> deleteProduct(); // 4. Delete Product
                    case 5 -> addSupplier(); // 5. Add Supplier
                    case 6 -> showAllSupplier(); // 6. Show All Suppliers
                    case 7 -> updateSupplier(); // 7. Update Supplier
                    case 8 -> deleteSupplier(); // 8. Delete Supplier
                    case 9 -> reports.printReports(); // 9. Reports
                    case 10 -> task.startThread(); // 10. Thread Simulation
                    case 11 -> isRunning = false; // 11. Exit
                    default -> System.out.println("Wrong Number! [1-9]");
                }
            } catch (Exception e) {
                e.printStackTrace();
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void deleteSupplier()
    {
        showAllSupplier();

        int id = userInput("Enter Your Supplier-ID: ", () -> scanner.nextInt());
        Supplier supplier = sService.deleteSupplier(id);

        System.out.println("Deleted Supplier: ".concat(supplier.getCompanyName()));
    }

    private static void updateSupplier()
    {
        showAllSupplier();

        int id = userInput("Enter Your Supplier-ID: ", () -> scanner.nextInt());

        System.out.println("Enter Your Supplier Info: ");
        Supplier userSupplier = Supplier.builder()
                .id(id)
                .companyName(userInput("Enter Your Supplier-Company-Name [string]: ", () -> scanner.nextLine()))
                .phone(userInput("Enter Your Supplier-Phone-Number [number-11]: ", () -> scanner.nextLine()))
                .build();

        boolean isUpdate = sService.updateSupplier(userSupplier);
        if (isUpdate)
            System.out.println("Supplier Updated!");
        else
            System.out.println("Updated Error!");
    }

    private static void showAllSupplier()
    {
        Set<Supplier> suppliers = sService.allSuppliers();
        System.out.println(suppliers);
    }

    private static void addSupplier()
    {
        Supplier supplier = Supplier.builder()
                .companyName(userInput("Enter Your Supplier-Company-Name [string]: ", () -> scanner.nextLine()))
                .phone(userInput("Enter Your Supplier-Phone-Number [number-11]: ", () -> scanner.nextLine()))
                .build();

        int id = sService.addSupplier(supplier);
        System.out.println("Added Supplier: " + id);
    }

    private static void deleteProduct()
    {
        showAllProduct();

        int id = userInput("Enter Your Product-ID: ", () -> scanner.nextInt());
        Product product = pService.deleteProduct(id);

        System.out.println("Deleted Product: ".concat(product.getName()));
    }

    private static void updateProduct()
    {
        showAllProduct();

        int id = userInput("Enter Your Product-ID: ", () -> scanner.nextInt());

        System.out.println("Enter Your Product Info: ");
        Product userProduct = Product.builder()
                .id(id)
                .name(userInput("Enter Your Product-Name [string]: ", () -> scanner.nextLine()))
                .price(userInput("Enter Your Product-Price [double]: ", () -> scanner.nextDouble()))
                .quantity(userInput("Enter Your Product-Quantity [int]: ", () -> scanner.nextInt()))
                .build();

        boolean isUpdate = pService.updateProduct(userProduct);
        if (isUpdate)
            System.out.println("Product Updated!");
        else
            System.out.println("Updated Error!");
    }

    private static void showAllProduct()
    {
        Set<Product> products = pService.allProducts();
        System.out.println(products);
    }

    private static void addProduct()
    {
        Product product = Product.builder()
                .name(userInput("Enter Your Product-Name [string]: ", () -> scanner.nextLine()))
                .price(userInput("Enter Your Product-Price [double]: ", () -> scanner.nextDouble()))
                .quantity(userInput("Enter Your Product-Quantity [int]: ", () -> scanner.nextInt()))
                .build();

        int id = pService.addProduct(product);
        System.out.println("Added Product: " + id);
    }

    private static <T> T userInput(String message, java.util.function.Supplier<T> supplier)
    {
        scanner.nextLine();
        System.out.println(message);
        return supplier.get();
    }
}