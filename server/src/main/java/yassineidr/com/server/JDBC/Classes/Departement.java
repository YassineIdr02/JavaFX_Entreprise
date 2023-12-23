package yassineidr.com.server.JDBC.Classes;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Departement {
    private String NomDept;
    private Integer IdDept;

    public Departement(String name, Integer id) {
        this.NomDept = name;
        this.IdDept = id;
    }
}


