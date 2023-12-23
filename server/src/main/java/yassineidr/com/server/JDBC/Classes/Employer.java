package yassineidr.com.server.JDBC.Classes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employer {
    private Integer IdEmp;
    private String NomEmp;
    private Float Salaire;
    private Integer Age;
    private Integer RefDept;

    public Employer(Integer idEmp, String nomEmp, Float salaire, Integer age, Integer refDept) {
        IdEmp = idEmp;
        NomEmp = nomEmp;
        Salaire = salaire;
        Age = age;
        RefDept = refDept;
    }
}
