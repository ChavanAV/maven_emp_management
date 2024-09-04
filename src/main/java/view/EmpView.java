package view;

import controller.EmpController;
import model.Employee;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class EmpView {
    static boolean alter = false;
    static EmpController controller = new EmpController();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            while (!alter){
                System.out.println();
                System.out.println("1. Add employee");
                System.out.println("2. Update employee");
                System.out.println("3. Remove employee");
                System.out.println("4. View employee details");
                System.out.println("5. View all employee details");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int c = sc.nextInt();
                switch (c){
                    case 1:
                        addEmp();
                        break;

                    case 2:
                        updateEmp();
                        break;

                    case 3:
                        removeEmp();
                        break;

                    case 4:
                        getEmp();
                        break;

                    case 5:
                        getAllEmp();
                        break;

                    case 6:
                        alter = !alter;
                        break;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }catch (SQLException | RuntimeException e){
            System.out.println();
            System.out.println("Something wrong happened..!!!");
            System.out.println(e);
        }
    }

    private static void getAllEmp() throws SQLException {
        List<Employee> list = controller.getAllEmp();
        if(list.isEmpty()){
            System.out.println("Empty database.");
            return;
        }
        for(Employee e : list)
            System.out.println(e);
    }

    private static void getEmp() throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        if(controller.getEmp(id).getName() == null){
            System.out.println("No Data found.");
        }else{
            System.out.println(controller.getEmp(id));
        }
    }

    private static void removeEmp() throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        controller.removeEmp(id);
    }

    private static void updateName() throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter employee name: ");
        String name = sc.nextLine();
        controller.updateEmpName(id, name);
    }
    private static void updatePhone() throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter employee phone: ");
        long ph = sc.nextLong();
        controller.updateEmpPhone(id, ph);
    }
    private static void updateSal() throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter employee salary: ");
        double sal = sc.nextDouble();
        controller.updateEmpSal(id, sal);
    }

    private static void addEmp() throws SQLException {
        System.out.print("Enter employee id: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter employee name: ");
        String name = sc.nextLine();
        System.out.print("Enter employee phone: ");
        long ph = sc.nextLong();
        System.out.print("Enter employee salary: ");
        double sal = sc.nextDouble();
        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setPh(ph);
        e.setSal(sal);
        controller.addEmp(e);
    }
    private static void updateEmp() throws SQLException{
        while (true){
            System.out.println();
            System.out.println("1. Update name");
            System.out.println("2. Update phone");
            System.out.println("3. Update salary");
            System.out.println("4. Back");
            System.out.print("Enter your choise: ");
            int c = sc.nextInt();
            switch (c){
                case 1:
                    updateName();
                    break;

                case 2:
                    updatePhone();
                    break;

                case 3:
                    updateSal();
                    break;

                case 4:
                    return;
                default:
                    System.out.println("Enter correct choice.");
            }}
    }
}
