package bai4.dao;

import bai4.model.OrganizationUnit;

public interface OrgUnitDao {
    boolean existsUnitId(String unitId);
    boolean existsName(String name);
    void insert(OrganizationUnit u);
}