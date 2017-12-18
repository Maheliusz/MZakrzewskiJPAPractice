package pl.edu.agh.ki.bd2.service;

import pl.edu.agh.ki.bd2.entity.Company;

import java.util.List;

public interface ICompanyService {
    public List<Company> getCompanyList();

    public Company getCompanyById(int id);

    public void addCompany(Company company);

    public void deleteCompany(Company company);
}
