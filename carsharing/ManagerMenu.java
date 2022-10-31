package carsharing;

import java.util.List;
import java.util.Scanner;

public class ManagerMenu {
    private Kompany kompany;
    private Scanner scanner;

    public ManagerMenu() {
        scanner = new Scanner(System.in);
        kompany = new KompanyImpl();
    }

    public void mainMenu() {
        System.out.println("1. Log in as a manager");
        System.out.println("0. Exit");
        int choice = scanner.nextInt();
        if (choice == 1) {
            managerMenu();
        } else if (choice == 0) {
            kompany.exit();
        } else {
            mainMenu();
        }
    }

    private void managerMenu() {
        System.out.println();
        System.out.println("1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back");
        int choice = scanner.nextInt();
        if (choice == 1) {
            companyList();
        } else if (choice == 2) {
            createCompany();
        } else if (choice == 0) {
            mainMenu();
        } else {
            managerMenu();
        }
    }

    private void companyList() {
        System.out.println();
        List<Company> companies = kompany.getAllCompanies();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            for (Company company : companies) {
                System.out.println(company.getId() + ". " + company.getName());
            }
        }
        managerMenu();
    }

    private void createCompany() {
        System.out.println();
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            name = scanner.nextLine();
        }
        kompany.createCompany(name);
        System.out.println("The company was created!");
        managerMenu();
    }
}