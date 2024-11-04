package ma.fpl.accountsvc.module;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
