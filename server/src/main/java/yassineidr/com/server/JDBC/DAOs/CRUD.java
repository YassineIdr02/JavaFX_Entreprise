package yassineidr.com.server.JDBC.DAOs;


import java.util.List;
import java.util.Optional;

public interface CRUD<T,PK> {
     boolean Create(T object);
     List<T> All();
     Optional<T> Read(PK Id);
     boolean Update(T object , PK Id);
     boolean Delete(PK Id);
     Long Count();
}