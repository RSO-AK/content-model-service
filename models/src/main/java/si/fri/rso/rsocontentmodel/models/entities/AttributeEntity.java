package si.fri.rso.rsocontentmodel.models.entities;


import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "attribute")
@NamedQueries(value =
        {
                @NamedQuery(name = "AttributeEntity.getAll",
                        query = "SELECT im FROM AttributeEntity im")
        })
public class AttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}