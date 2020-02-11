package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "username")
@ToString(exclude = "id")
public class Account implements Querier {
    private Integer id;
    private String first_name;
    private String last_name;
    private String city;
    private String gender;
    private String username;

    public String getFieldsNames() {
        return "(id, first_name, last_name, city, gender, username)";
    }

    public String getValues() {
        return "('" + getId() + "', "
                + "'" + getFirst_name() + "', "
                + "'" + getLast_name() + "', "
                + "'" + getCity() + "', "
                + "'" + getGender() + "', "
                + "'" + getUsername() + "')";
    }
}
