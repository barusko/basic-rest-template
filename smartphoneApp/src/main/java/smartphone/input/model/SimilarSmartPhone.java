package smartphone.input.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimilarSmartPhone {

    private String id;

    private String name;

    private String brand;

    private String price;
}
