package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetPojo {

    private int id;

    private String name;

    private List<String> photoUrls;

    private String status;

    private CategoryPojo category;

    private List<TagPojo> tags;

}
