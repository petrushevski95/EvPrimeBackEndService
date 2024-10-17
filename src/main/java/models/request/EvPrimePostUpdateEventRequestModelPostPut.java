package models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvPrimePostUpdateEventRequestModelPostPut {

    String title;
    String image;
    String date;
    String location;
    String description;

}
