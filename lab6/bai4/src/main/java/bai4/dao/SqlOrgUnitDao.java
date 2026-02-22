package bai4.dao;

import bai4.model.OrganizationUnit;

import java.sql.*;

public class SqlOrgUnitDao implements OrgUnitDao {

    private final String url, user, pass;

    public SqlOrgUnitDao(String url, String user, String pass) {
        this.url = url; this.user = user; this.pass = pass;
    }

    private Connection open() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    @Override
    public boolean existsUnitId(String unitId) {
        if (unitId == null) return false;
        String sql = "SELECT 1 FROM dbo.OrganizationUnit WHERE UnitId = ?";
        try (Connection c = open(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, unitId);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public boolean existsName(String name) {
        String sql = "SELECT 1 FROM dbo.OrganizationUnit WHERE Name = ?";
        try (Connection c = open(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void insert(OrganizationUnit u) {
        String sql = "INSERT INTO dbo.OrganizationUnit(UnitId, Name, Description, OrgId) VALUES (?,?,?,?)";
        try (Connection c = open(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.unitId);
            ps.setString(2, u.name);
            ps.setString(3, u.description);
            ps.setInt(4, u.orgId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}