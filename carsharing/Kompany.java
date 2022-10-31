package carsharing;

import java.util.List;

public interface Kompany {
//    list of companies, create,xit
     public List<Company> getAllCompanies();
    void createCompany(String name);
    void exit();

}
