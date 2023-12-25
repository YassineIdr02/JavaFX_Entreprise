package yassineidr.com.server.JDBC.DAOs;

import yassineidr.com.server.JDBC.Classes.Departement;
import yassineidr.com.server.JDBC.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class DAODepartement {

    private Connection myConn = DAOFactory.getConnection();

    public boolean Create(Departement dept) {
        String stmt = "INSERT INTO Departement (NomDept, NbrEmp) VALUES (?, 0)";
        PreparedStatement pstmt;
        try{
            pstmt = myConn.prepareStatement(stmt);
            pstmt.setString(1,dept.getNomDept());
            int Lig = pstmt.executeUpdate();
            if (Lig == 1)
                System.out.println(" Departement added successfully");
            else
                System.out.println(" Something went wrong!!!");
            return true ;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Departement> All() {
        List<Departement> departements = new ArrayList<Departement>();
        String stmt = "SELECT * FROM DEPARTEMENT";
        PreparedStatement pstmt;
        try{
            pstmt = myConn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                departements.add(new Departement(rs.getString(2), rs.getInt(1), rs.getInt(3) ));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return departements;
    }

    public List<Integer> AllIds() {
        List<Integer> departements = new ArrayList<Integer>();
        String stmt = "SELECT IdDept FROM DEPARTEMENT";
        PreparedStatement pstmt;
        try{
            pstmt = myConn.prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                departements.add( rs.getInt(1) );
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return departements;
    }

    public Optional<Departement> Read(Integer Id) {
        String stmt = "SELECT * FROM DEPARTEMENT WHERE IDDEPT = ?";
        ResultSet rs;

        try{
            PreparedStatement pstmt = myConn.prepareStatement(stmt);
            pstmt.setInt(1,Id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                System.out.println("Departement found!!");
                Departement departement = Departement.builder()
                        .IdDept(rs.getInt(1))
                        .NomDept(rs.getString(2))
                        .build();
                return Optional.of(departement);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return Optional.empty();
    }

    public boolean Update(Departement departement, Integer Id) {
        String query = "UPDATE DEPARTEMENT SET NOMDEPT = ? where IDDEPT = ?" ;
        try {
            PreparedStatement pst = myConn.prepareStatement(query);
            pst.setInt(2, Id);
            pst.setString(1, departement.getNomDept());
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
        String sql = "DELETE FROM DEPARTEMENT WHERE IDDEPT = ?";
        PreparedStatement Pst;
        try {
            Pst = myConn.prepareStatement(sql);
            Pst.setInt(1, Id);
            int Lig= Pst.executeUpdate();
            if (Lig != 0)
                System.out.println(Lig + " Departement delete with success");
            else
                System.out.println("No article deleted");
            return true ;
        } catch (SQLException e) {
            System.err.println("Error in the request delete article " + e.getMessage());
        }return false ;
    }

    public Departement MaxDept(){
        String sql = "SELECT d.IdDept, d.NomDept, COUNT(e.IdEmp) AS Nombre_Employes FROM Departement d JOIN Employee e ON d.IdDept = e.RefDept GROUP BY d.IdDept, d.NomDept ORDER BY Nombre_Employes DESC LIMIT 1;";
        Statement St;
        ResultSet rs;
        try{
            St = myConn.createStatement();
            rs = St.executeQuery(sql);
            if(rs.next())
                return Departement.builder()
                        .IdDept(rs.getInt(1))
                        .NomDept(rs.getString(2))
                        .build();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Long EmpDept(){
        String sql = "SELECT d.IdDept, d.NomDept, COUNT(e.IdEmp) AS Nombre_Employes\n" +
                "FROM Departement d\n" +
                "LEFT JOIN Employee e ON d.IdDept = e.RefDept\n" +
                "GROUP BY d.IdDept, d.NomDept;\n";
        Statement St ;
        ResultSet resultSet;
        try
        {
            St = myConn.createStatement();
            resultSet = St.executeQuery(sql);
            if(resultSet.next()) return resultSet.getLong("Nombre_Employes");
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return 0L ;
    }



}
