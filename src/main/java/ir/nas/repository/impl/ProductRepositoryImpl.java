package ir.nas.repository.impl;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import ir.nas.model.Product;
import ir.nas.repository.ProductRepository;
import ir.nas.util.db.DatabaseConnection;

public final class ProductRepositoryImpl implements ProductRepository
{
    @Override
    public Optional<Product> delete(final Integer id)
    {
        final String DELETE_QUERY_STRING = "DELETE FROM product WHERE id = ? RETURNING *;";

        return DatabaseConnection.excuteQuery(DELETE_QUERY_STRING, (ps) -> {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return Optional.of(
                            Product.builder()
                                    .id(rs.getInt("id"))
                                    .name(rs.getString("name"))
                                    .price(rs.getDouble("price"))
                                    .quantity(rs.getInt("quantity"))
                                    .build());

                return Optional.empty();
            }
        });
    }

    @Override
    public Set<Product> findAll()
    {
        Set<Product> products = new HashSet<>();
        final String FIND_ALL_QUERY_STRING = "SELECT * FROM product;";

        return DatabaseConnection.excuteQuery(FIND_ALL_QUERY_STRING, (ps) -> {
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next())
                    products.add(
                            Product.builder()
                                    .id(rs.getInt("id"))
                                    .name(rs.getString("name"))
                                    .price(rs.getDouble("price"))
                                    .quantity(rs.getInt("quantity"))
                                    .build());

                return products;
            }
        });
    }

    @Override
    public Optional<Product> read(final Integer id)
    {
        final String READ_QUERY_STRING = "SELECT * FROM product WHERE id = ?;";

        return DatabaseConnection.excuteQuery(READ_QUERY_STRING, (ps) -> {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return Optional.of(
                            Product.builder()
                                    .id(rs.getInt("id"))
                                    .name(rs.getString("name"))
                                    .price(rs.getDouble("price"))
                                    .quantity(rs.getInt("quantity"))
                                    .build());

                return Optional.empty();
            }
        });
    }

    @Override
    public Integer save(final Product t)
    {
        final String SAVE_QUERY_STRING = "INSERT INTO product (name, price, quantity) VALUES (?, ?, ?);";

        return DatabaseConnection.excuteQueryWithGenerateKey(SAVE_QUERY_STRING, (ps) -> {

            ps.setString(1, t.getName());
            ps.setDouble(2, t.getPrice());
            ps.setInt(3, t.getQuantity());

            int excute = ps.executeUpdate();

            if (excute > 0)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next())
                        return rs.getInt("id");
                }

            return -1;
        });
    }

    @Override
    public boolean update(final Product t)
    {
        final String UDPATE_QUERY_STRING = "UPDATE product SET (name, price, quantity) = (?, ?, ?);";

        return DatabaseConnection.excuteQuery(UDPATE_QUERY_STRING, (ps) -> {

            ps.setString(1, t.getName());
            ps.setDouble(2, t.getPrice());
            ps.setInt(3, t.getQuantity());

            return ps.executeUpdate() > 0;
        });
    }

    @Override
    public Set<Product> findBelowsByQuantity(int quantity)
    {
        Set<Product> products = new HashSet<>();
        final String FIND_BELOWS_BY_QUANTITY_STRING = "SELECT * FROM product WHERE quantity < ?;";

        return DatabaseConnection.excuteQuery(FIND_BELOWS_BY_QUANTITY_STRING, (ps) -> {
            ps.setInt(1, quantity);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next())
                    products.add(
                            Product.builder()
                                    .id(rs.getInt("id"))
                                    .name(rs.getString("name"))
                                    .price(rs.getDouble("price"))
                                    .quantity(rs.getInt("quantity"))
                                    .build());

                return products;
            }
        });
    }
}
