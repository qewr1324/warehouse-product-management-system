package ir.nas.util.task;

import ir.nas.model.Product;
import ir.nas.service.ProductService;

public final class Task
{
    private final ProductService pService;

    public Task(final ProductService pService)
    {
        this.pService = pService;
    }

    public void startThread() throws InterruptedException
    {
        Runnable task_1 = () -> {
            System.out.println("Task_1 Start...");

            Product product = Product.builder()
                    .name("Iphone SE")
                    .price(450.67)
                    .quantity(4)
                    .build();

            this.pService.addProduct(product);

            System.out.println("Task_1 Done. Save Product.");
        };

        Runnable task_2 = () -> {
            System.out.println("Task_2 Start...");

            Product product = Product.builder()
                    .name("Sumsung S7")
                    .price(500.67)
                    .quantity(10)
                    .build();

            this.pService.addProduct(product);

            System.out.println("Task_2 Done. Save Product.");

        };

        Thread thread_1 = new Thread(task_1);
        Thread thread_2 = new Thread(task_2);

        System.out.println("Sleep 3 sec...");
        Thread.sleep(3000);
        thread_1.start();
        thread_1.join();

        System.out.println("Sleep 1 sec...");
        Thread.sleep(1000);
        thread_2.start();

        thread_2.join();

        System.out.println("Finish Threads. All products have been registered.");
    }
}
