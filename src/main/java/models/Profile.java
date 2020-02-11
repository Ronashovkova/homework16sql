package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "username")
@ToString(exclude = "id")
public class Profile implements Querier {
    private Integer id;
    private String username;
    private String job_title;
    private String department;
    private String company;
    private String skill;

    public String getFieldsNames() {
        return "(id, username, job_title, department, company, skill)";
    }

    public String getValues() {
        return "('" + getId() + "', "
                + "'" + getUsername() + "', "
                + "'" + getJob_title() + "', "
                + "'" + getDepartment() + "', "
                + "'" + getCompany() + "', "
                + "'" + getSkill() + "')";
    }
}
