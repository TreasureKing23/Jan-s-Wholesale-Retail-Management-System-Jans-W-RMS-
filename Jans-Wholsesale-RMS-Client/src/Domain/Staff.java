package Domain;

public class Staff {

    private String name;
    private String staffID;
    private String position;
    private String department;
    private String dateOfBirth;

    public Staff(String name, String staffID, String position, String department, String dateOfBirth) {
        this.name = name;
        this.staffID = staffID;
        this.position = position;
        this.department = department;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "entities.Staff{" +
                "name='" + name + '\'' +
                ", staffID='" + staffID + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
