package pl.edu.agh.ki.bd2.dao;

import pl.edu.agh.ki.bd2.entity.Product;
import pl.edu.agh.ki.bd2.entity.Supplier;

import java.util.List;

public interface ISupplierDAO extends ICompanyDAO {
    public int getBankAccountNumber(Supplier supplier);

    public List<Product> getProductsSupplied(Supplier supplier);

    @Override
    public Supplier getCompany(String name);
}
