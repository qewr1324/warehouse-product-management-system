package ir.nas.repository.impl;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import ir.nas.model.Supplier;
import ir.nas.repository.Repository;
import ir.nas.util.db.DatabaseConnection;

public final class SupplierRepositoryImpl implements Repository<Integer, Supplier>
{
    @Override
    public Optional<Supplier> delete(final Integer id)
    {
        final String DELETE_QUERY_STRING = "DELETE FROM supplier WHERE id = ?;";

        return DatabaseConnection.excuteQuery(DELETE_QUERY_STRING, (ps) -> {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return Optional.of(
                            Supplier.builder()
                                    .id(rs.getInt("id"))
                                    .companyName(rs.getString("company_name"))
                                    .phone(rs.getString("phone"))
                                    .build());

                return Optional.empty();
            }
        });
    }

    @Override
    public Set<Supplier> findAll()
    {
        Set<Supplier> suppliers = new HashSet<>();
        final String FIND_ALL_QUERY_STRING = "SELECT * FROM supplier;";

        return DatabaseConnection.excuteQuery(FIND_ALL_QUERY_STRING, (ps) -> {
            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next())
                    suppliers.add(
                            Supplier.builder()
                                    .id(rs.getInt("id"))
                                    .companyName(rs.getString("company_name"))
                                    .phone(rs.getString("phone"))
                                    .build());

                return suppliers;
            }
        });
    }

    @Override
    public Optional<Supplier> read(final Integer id)
    {
        final String READ_QUERY_STRING = "SELECT * FROM supplier WHERE id = ?;";

        return DatabaseConnection.excuteQuery(READ_QUERY_STRING, (ps) -> {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next())
                    return Optional.of(
                            Supplier.builder()
                                    .id(rs.getInt("id"))
                                    .companyName(rs.getString("company_name"))
                                    .phone(rs.getString("phone"))
                                    .build());

                return Optional.empty();
            }
        });
    }

    @Override
    public Integer save(final Supplier t)
    {
        final String SAVE_QUERY_STRING = "INSERT INTO supplier (company_name, phone) VALUES (?, ?);";

        return DatabaseConnection.excuteQueryWithGenerateKey(SAVE_QUERY_STRING, (ps) -> {

            ps.setString(1, t.getCompanyName());
            ps.setString(2, t.getPhone());

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
    public boolean update(final Supplier t)
    {
        final String UDPATE_QUERY_STRING = "UPDATE supplier SET (company_name, phone) = (?, ?);";

        return DatabaseConnection.excuteQuery(UDPATE_QUERY_STRING, (ps) -> {

            ps.setString(1, t.getCompanyName());
            ps.setString(2, t.getPhone());

            return ps.executeUpdate() > 0;
        });
    }
}
