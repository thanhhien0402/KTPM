package bai4.service;

import bai4.dao.OrgUnitDao;
import bai4.model.OrganizationUnit;

public class OrgUnitService {

    private final OrgUnitDao dao;

    public OrgUnitService(OrgUnitDao dao) {
        this.dao = dao;
    }

    public void create(OrganizationUnit u) {
        // Normalize
        String unitId = (u.unitId == null) ? null : u.unitId.trim();
        String name = (u.name == null) ? null : u.name.trim();
        String desc = (u.description == null) ? null : u.description.trim();

        // Name* required
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name is required");
        if (name.length() > 100)
            throw new IllegalArgumentException("Name max length is 100");

        // UnitId optional but validated if present
        if (unitId != null && !unitId.isEmpty()) {
            if (unitId.length() > 20)
                throw new IllegalArgumentException("Unit Id max length is 20");
            if (!unitId.matches("[A-Za-z0-9_-]+"))
                throw new IllegalArgumentException("Unit Id format is invalid");
            if (dao.existsUnitId(unitId))
                throw new IllegalArgumentException("Unit Id already exists");
        } else {
            unitId = null; // store null
        }

        // Description optional max 255
        if (desc != null && desc.length() > 255)
            throw new IllegalArgumentException("Description max length is 255");

        // Name unique (nếu bạn bật constraint UQ_OrgUnit_Name)
        if (dao.existsName(name))
            throw new IllegalArgumentException("Name already exists");

        // OrgId required (>0)
        if (u.orgId <= 0)
            throw new IllegalArgumentException("OrgId is invalid");

        dao.insert(new OrganizationUnit(unitId, name, desc, u.orgId));
    }
}