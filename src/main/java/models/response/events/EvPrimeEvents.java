package models.response.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvPrimeEvents {

    String id;
    String title;
    String image;
    String date;
    String location;
    String description;

}
