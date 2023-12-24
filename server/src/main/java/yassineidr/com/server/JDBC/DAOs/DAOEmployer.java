package yassineidr.com.server.JDBC.DAOs;

import yassineidr.com.server.JDBC.Classes.Departement;
import yassineidr.com.server.JDBC.Classes.Employer;
import yassineidr.com.server.JDBC.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOEmployer  {
    private final Connection myConn = DAOFactory.getConnection();

    public boolean Create(Employer emp) {
        String stmt = "INSERT INTO EMPLOYER (NomEMP, SALAIRE, AGE, REFDEPT) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt;
        try{
            pstmt = myConn.prepareStatement(stmt);
            pstmt.setString(1, emp.getNomEmp());
            pstmt.setFloat(2, emp.getSalaire());
            pstmt.setInt(3, emp.getAge());
            pstmt.setInt(4, emp.getRefDept());

            int Lig = pstmt.executeUpdate();
            if (Lig == 1)
                System.out.println(" Employer added successfully");
            else
                System.out.println(" Something went wrong!!!");
            return true ;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Employer> All() {
        List<Employer> employers = new ArrayList<Employer>();
        String stmt = "SELECT * FROM EMPLOYER";
        PreparedStatement pstmt;
        try{
            pstmt = myConn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                employers.add(new Employer(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5) ));

            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return employers;
    }

    public List<Employer> EmpDept(Integer RefDept) {
        List<Employer> employers = new ArrayList<Employer>();
        String stmt = "SELECT * FROM EMPLOYER WHERE REFDEPT = ?";
        PreparedStatement pstmt;
        try{
            pstmt = myConn.prepareStatement(stmt);
            pstmt.setInt(1, RefDept);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                employers.add(new Employer(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5) ));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return employers;
    }

    public Optional<Employer> Read(Integer Id) {
        String stmt = "SELECT * FROM EMPLOYER WHERE IDEMP = ?";
        ResultSet rs;
        try{
            PreparedStatement pstmt = myConn.prepareStatement(stmt);
            pstmt.setInt(1,Id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println("Employer found!!");
                Employer employer = Employer.builder()
                        .IdEmp(rs.getInt(1))
                        .NomEmp(rs.getString(2))
                        .Salaire(rs.getFloat(3))
                        .Age(rs.getInt(4))
                        .RefDept(rs.getInt(5))
                        .build();
                return Optional.of(employer);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    public boolean Update(Employer employer, Integer Id) {
        String query = "UPDATE EMPLOYER SET NOMEMP = ?, SALAIRE = ? , AGE = ?, REFDEPT = ? where IDEMP = ?" ;
        try {
            PreparedStatement pst = myConn.prepareStatement(query);
            pst.setString(1, employer.getNomEmp());
            pst.setFloat(2, employer.getSalaire());
            pst.setInt(3, employer.getAge());
            pst.setInt(4, employer.getRefDept());
            pst.setInt(2, employer.getIdEmp());
            pst.executeUpdate();
            return true ;
        }
        catch(SQLException ex)
        {
            System.err.println("Error in the request UPDATE"+ex.getMessage());
        }
        return false ;
    }

    public boolean Delete(Integer Id) {
        String sql = "DELETE FROM EMPLOYER WHERE IDEMP = ?";
        PreparedStatement Pst;
        try {
            Pst = myConn.prepareStatement(sql);
            Pst.setInt(1, Id);
            int Lig= Pst.executeUpdate();
            if (Lig != 0)
                System.out.println(Lig + " Employer delete with success");
            else
                System.out.println("No Employer deleted");
            return true ;
        } catch (SQLException e) {
            System.err.println("Error in the request delete employer " + e.getMessage());
        }return false ;
    }

    public boolean UpdateDept(Integer iddept, Integer idemp) {
        String query = " UPDATE Employee SET RefDept = ? WHERE IdEmp = ?";
        try {
            PreparedStatement pst = myConn.prepareStatement(query);
            pst.setInt(1, iddept);
            pst.setInt(2, idemp);
            pst.executeUpdate();
            return true ;
        }
        catch(SQLException ex)
        {
            System.err.println("Error in the request UPDATE"+ex.getMessage());
        }
        return false ;
    }

    public Long MasseSalEnt() {
        String sql = "SELECT SUM(Salaire) AS Masse_Salariale_Entreprise FROM Employee;";
        try
        {
            Statement St = myConn.createStatement();
            ResultSet resultSet = St.executeQuery(sql);
            if(resultSet.next()) return resultSet.getLong(1);
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0L ;
    }
    public Long MasseSalDept(Integer idDept) {
        String sql = "SELECT SUM(e.Salaire) AS Masse_Salariale_Departement FROM Departement d LEFT JOIN Employee e ON d.IdDept = e.RefDept GROUP BY d.IdDept, d.NomDept;\n";
        try
        {
            Statement St = myConn.createStatement();
            ResultSet resultSet = St.executeQuery(sql);
            if(resultSet.next()) return resultSet.getLong("Masse_Salariale_Departement");
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0L ;
    }

    public List<Employer> TopSal(){
        List<Employer> employers = new ArrayList<Employer>();
        String stmt = "SELECT * FROM Employee ORDER BY Salaire DESC LIMIT 5;";
        try{
            PreparedStatement pstmt = myConn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                employers.add(new Employer(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5) ));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return employers;
    }



}
