package bai4.model;

public class OrganizationUnit {
    public String unitId;       // nullable
    public String name;         // required
    public String description;  // nullable
    public int orgId;           // required

    public OrganizationUnit(String unitId, String name, String description, int orgId) {
        this.unitId = unitId;
        this.name = name;
        this.description = description;
        this.orgId = orgId;
    }
}