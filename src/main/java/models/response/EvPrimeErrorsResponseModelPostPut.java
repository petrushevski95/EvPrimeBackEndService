package models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.response.errors.EvPrimeErrorMessages;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvPrimeErrorsResponseModelPostPut {

    String message;
    EvPrimeErrorMessages errors;

}
