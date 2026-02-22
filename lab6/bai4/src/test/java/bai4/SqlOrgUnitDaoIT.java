package bai4;

import bai4.dao.SqlOrgUnitDao;
import bai4.db.DbConfig;
import bai4.model.OrganizationUnit;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.*;

public class SqlOrgUnitDaoIT {

    private SqlOrgUnitDao dao;

    @Before
    public void setup() throws Exception {
        dao = new SqlOrgUnitDao(DbConfig.URL, DbConfig.USER, DbConfig.PASS);

        try (Connection c = DriverManager.getConnection(DbConfig.URL, DbConfig.USER, DbConfig.PASS);
             Statement st = c.createStatement()) {
            st.executeUpdate("DELETE FROM dbo.OrganizationUnit WHERE UnitId LIKE 'TEST_%' OR Name LIKE 'TEST_%'");
        }
    }

    @Test
    public void TC_B4_DB_01_InsertThenExists_ShouldTrue() {
        dao.insert(new OrganizationUnit("TEST_01", "TEST_HR", "desc", 1));
        assertTrue(dao.existsUnitId("TEST_01"));
        assertTrue(dao.existsName("TEST_HR"));
    }
}