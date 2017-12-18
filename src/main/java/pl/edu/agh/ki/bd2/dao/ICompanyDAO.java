package pl.edu.agh.ki.bd2.dao;

import pl.edu.agh.ki.bd2.entity.Address;
import pl.edu.agh.ki.bd2.entity.Company;

public interface ICompanyDAO {
    public Company getCompany(String name);

    public Address getAddress(Company company);
}
