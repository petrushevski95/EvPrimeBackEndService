package models.response.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvPrimeErrorMessages {

    String credentials;
    String id;
    String email;
    String password;
    String title;
    String image;
    String date;
    String location;
    String description;


}
