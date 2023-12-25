package yassineidr.com.server.JDBC.Classes;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Departement {
    private String NomDept;
    private Integer IdDept;
    private Integer NbrEmp;

    public Departement(String name, Integer id, Integer nbrEmp) {
        this.NomDept = name;
        this.IdDept = id;
        this.NbrEmp = nbrEmp;
    }
}


