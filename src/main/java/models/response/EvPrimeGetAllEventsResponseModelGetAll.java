package models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.response.events.EvPrimeEvents;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvPrimeGetAllEventsResponseModelGetAll {

    List<EvPrimeEvents> events;

}
