package pl.edu.agh.ki.bd2.service;

import pl.edu.agh.ki.bd2.entity.Supplier;

import java.util.List;

public interface ISupplierService {
    public List<Supplier> getAllSuppliers();

    public Supplier getSupplierById(int id);

    public void addSupplier(Supplier supplier);

    public void deleteSupplier(Supplier supplier);
}
