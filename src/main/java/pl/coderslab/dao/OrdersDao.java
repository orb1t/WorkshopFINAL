package pl.coderslab.dao;

import pl.coderslab.Classes.Orders;
import pl.coderslab.DbUtil;

import java.math.BigDecimal;
import java.sql.*;

public class OrdersDao extends Orders {

    public OrdersDao(Date acceptanceDate, Date planningStartDate, Date startRepair, int employee_id, String problemDescription, String repairDescription, String status, int vehicle_id, BigDecimal repairCost, BigDecimal partCost, int repairHours) {
        super(acceptanceDate, planningStartDate, startRepair, employee_id, problemDescription, repairDescription, status, vehicle_id, repairCost, partCost, repairHours);
    }

    public void delete(){
        String sql = "DELETE FROM orders WHERE id= ?";
        try{
            if(this.getId()!=0){
                PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
                stmt.setLong(1, this.getId());
                stmt.executeUpdate();
                setId(0);
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static OrdersDao loadById(int id){
        try {
            String sql = "SELECT * FROM orders WHERE id=?";
            PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                EmployeesDao loadedEmployee = new EmployeesDao();
                loadedEmployee.setId(resultSet.getInt("id"));
                loadedEmployee.setName(resultSet.getString("name"));
                loadedEmployee.setLastName(resultSet.getString("lastName"));
                loadedEmployee.setAddress(resultSet.getString("address"));
                loadedEmployee.setPhone(resultSet.getString("phone"));
                loadedEmployee.setNote(resultSet.getString("note"));
                loadedEmployee.setHourlyPayment(resultSet.getBigDecimal("hourlyPayment"));
                return loadedEmployee;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    public void saveToDB() {
        if (this.getId() == 0) {
            try {
                Connection conn = DbUtil.getConn();
                String generatedColumns[] = {"ID"};
                PreparedStatement stmt = DbUtil.getConn().prepareStatement("INSERT INTO orders(acceptanceDate, planningStartDate, startRepair, employee_id, problemDescription, repairDescription, status, vehicle_id,repairCost, partCost, repairHours) " +
                        "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", generatedColumns);
                stmt.setDate(1, this.getAcceptanceDate());
                stmt.setDate(2, this.getPlanningStartDate());
                stmt.setDate(3, this.getStartRepair());
                stmt.setInt(4, this.getEmployee_id());
                stmt.setString(5, this.getProblemDescription());
                stmt.setString(6, this.getRepairDescription());
                stmt.setString(7, this.getStatus());
                stmt.setInt(8, this.getVehicle_id());
                stmt.setBigDecimal(9, this.getRepairCost());
                stmt.setBigDecimal(10, getPartCost());
                stmt.setInt(11, this.getRepairHours());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    this.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {
                PreparedStatement stmt = DbUtil.getConn().prepareStatement("UPDATE orders SET acceptanceDate = ?, planningStartDate = ?, startRepair = ?, employee_id = ?, problemDescription = ?, repairDescription = ?, status = ?, vehicle_id = ?,repairCost = ?, partCost = ?, repairHours = ? " +
                        "WHERE id = ?");
                stmt.setDate(1, this.getAcceptanceDate());
                stmt.setDate(2, this.getPlanningStartDate());
                stmt.setDate(3, this.getStartRepair());
                stmt.setInt(4, this.getEmployee_id());
                stmt.setString(5, this.getProblemDescription());
                stmt.setString(6, this.getRepairDescription());
                stmt.setString(7, this.getStatus());
                stmt.setInt(8, this.getVehicle_id());
                stmt.setBigDecimal(9, this.getRepairCost());
                stmt.setBigDecimal(10, this.getPartCost());
                stmt.setInt(11, this.getRepairHours());
                stmt.setInt(12, this.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
//    public void delete(){
//        String sql = "DELETE FROM excercise WHERE id= ?";
//        try{
//            PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
//            stmt.setInt(1, this.id);
//            stmt.executeUpdate();
//            this.id=0;
//        }catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//    }
//    // static DB methods
//    public static ArrayList<Excercise> loadAllByUserId(int userId){
//        try{
//            String sql = "SELECT * FROM excercise "
//                    + "JOIN solution ON solution.excercise_id = excercise.id "
//                    + "JOIN users ON solution.users_id = users.id "
//                    + "WHERE users.id = ?";
//            PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
//            stmt.setInt(1, userId);
//            return getExcerciseFromStatement(stmt);
//        }catch(SQLException e){
//            System.err.println(e.getMessage());
//        }
//        return null;
//    }
//    public static ArrayList<Excercise> loadAll() throws SQLException {
//        String sql = "SELECT * FROM excercise";
//        PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
//
//        return getExcerciseFromStatement(stmt);
//    }
//
//    private static ArrayList<Excercise> getExcerciseFromStatement(PreparedStatement stmt) {
//        try {
//            ArrayList<Excercise> excercises = new ArrayList<Excercise>();
//            ResultSet resultSet = stmt.executeQuery();
//            while (resultSet.next()) {
//                Excercise loadedEx = new Excercise();
//                loadedEx.id = resultSet.getInt("id");
//                loadedEx.title = resultSet.getString("title");
//                loadedEx.description = resultSet.getString("description");
//
//                excercises.add(loadedEx);
//            }
//            return excercises;
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//
//        return null;
//    }
//    public static Excercise loadById(int id){
//        try {
//            String sql = "SELECT * FROM excercise where id=?";
//            PreparedStatement stmt = DbUtil.getConn().prepareStatement(sql);
//            stmt.setInt(1, id);
//            ResultSet resultSet = stmt.executeQuery();
//            while (resultSet.next()) {
//                Excercise loadedEx = new Excercise();
//                loadedEx.id = resultSet.getInt("id");
//                loadedEx.title = resultSet.getString("title");
//                loadedEx.description = resultSet.getString("description");
//                return loadedEx;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//
//        return null;
//    }
//
//}