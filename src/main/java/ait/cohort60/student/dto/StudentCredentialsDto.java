package ait.cohort60.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCredentialsDto {
    // лучше чем int, так как при отстутствии значения будет не 0, a null
    private Long id;
    private String name;
    private String password;

}
