package controller;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpController{
    static Connection con;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/qsp", "postgres", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addEmp(Employee e) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("insert into emp values(?, ?, ?, ?)");
        stmt.setInt(1, e.getId());
        stmt.setString(2, e.getName());
        stmt.setLong(3, e.getPh());
        stmt.setDouble(4, e.getSal());
        stmt.execute();
        System.out.println("Employee added.");
    }
    public boolean removeEmp(int id) throws SQLException{
        if(getEmp(id).getId() == id){
            PreparedStatement stmt = con.prepareStatement("delete from emp where empid = ?");
            stmt.setInt(1, id);
            stmt.execute();
            System.out.println("Employee removed.");
            return true;
        }
        System.out.println("Employee not removed.");
        return false;
    }
    public Employee getEmp(int id) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("select * from emp where empid = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        Employee e = new Employee();
        while (rs.next()){
            e.setId(rs.getInt("empid"));
            e.setName(rs.getString("ename"));
            e.setPh(rs.getLong("ph"));
            e.setSal(rs.getDouble("sal"));
        }
        return e;
    }
    public boolean updateEmpName(int id, String name)throws SQLException{
        if(getEmp(id).getId() == id){
            PreparedStatement stmt = con.prepareStatement("update emp set ename = ? where empid = ?");
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.execute();
            System.out.println("Name updated.");
            return true;
        }
        System.out.println("Name not updated.");
        return false;
    }
    public boolean updateEmpPhone(int id, long ph)throws SQLException{
        if(getEmp(id).getId() == id){
            PreparedStatement stmt = con.prepareStatement("update emp set ph = ? where empid = ?");
            stmt.setLong(1, ph);
            stmt.setInt(2, id);
            stmt.execute();
            System.out.println("Phone number updated.");
            return true;
        }
        System.out.println("Phone number not updated.");
        return false;
    }
    public boolean updateEmpSal(int id, double sal)throws SQLException{
        if(getEmp(id).getId() == id){
            PreparedStatement stmt = con.prepareStatement("update emp set sal = ? where empid = ?");
            stmt.setDouble(1, sal);
            stmt.setInt(2, id);
            stmt.execute();
            System.out.println("Salary updated.");
            return true;
        }
        System.out.println("Salary not updated.");
        return false;
    }

    public List<Employee> getAllEmp() throws SQLException{
        List<Employee> list = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("select * from emp");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Employee e = new Employee();
            e.setId(rs.getInt("empid"));
            e.setName(rs.getString("ename"));
            e.setPh(rs.getLong("ph"));
            e.setSal(rs.getDouble("sal"));
            list.add(e);
        }
        return list;
    }

}
