package bai4;

import bai4.dao.OrgUnitDao;
import bai4.model.OrganizationUnit;
import bai4.service.OrgUnitService;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrgUnitServiceTest {

    static class FakeDao implements OrgUnitDao {
        boolean unitIdExists;
        boolean nameExists;
        boolean inserted;

        @Override public boolean existsUnitId(String unitId) { return unitIdExists; }
        @Override public boolean existsName(String name) { return nameExists; }
        @Override public void insert(OrganizationUnit u) { inserted = true; }
    }

    @Test(expected = IllegalArgumentException.class)
    public void TC_B4_01_NameEmpty_ShouldThrow() {
        FakeDao dao = new FakeDao();
        OrgUnitService s = new OrgUnitService(dao);
        s.create(new OrganizationUnit("U01", "   ", "desc", 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TC_B4_02_UnitIdInvalidFormat_ShouldThrow() {
        FakeDao dao = new FakeDao();
        OrgUnitService s = new OrgUnitService(dao);
        s.create(new OrganizationUnit("U 01", "HR", "desc", 1)); // có space
    }

    @Test(expected = IllegalArgumentException.class)
    public void TC_B4_03_UnitIdDuplicate_ShouldThrow() {
        FakeDao dao = new FakeDao();
        dao.unitIdExists = true;
        OrgUnitService s = new OrgUnitService(dao);
        s.create(new OrganizationUnit("U01", "HR", "desc", 1));
    }

    @Test
    public void TC_B4_04_Valid_ShouldInsert() {
        FakeDao dao = new FakeDao();
        OrgUnitService s = new OrgUnitService(dao);
        s.create(new OrganizationUnit("U01", "HR", "desc", 1));
        assertTrue(dao.inserted);
    }
}