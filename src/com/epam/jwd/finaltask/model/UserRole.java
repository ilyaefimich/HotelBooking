package com.epam.jwd.finaltask.model;

public class UserRole {
    private int roleId;
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role= '" + roleName + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        if (roleName != null ? !roleName.equals(userRole.roleName) : userRole.roleName != null) {
            return false;
        }
        return roleName != null ? roleName.equals(userRole.roleName) : userRole.roleName == null;


    }

    @Override
    public int hashCode() {
        int result = roleName != null ? roleName.hashCode() : 0;
        result = 32 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;


    }

}
