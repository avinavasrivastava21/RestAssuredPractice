package post_pojo;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CreateUserPojo {

    String name;
    String email;
    String gender;
    String status;
}
