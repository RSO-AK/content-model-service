package si.fri.rso.rsocontentmodel.lib;

import java.time.Instant;
import java.util.List;

public class ContentModel {

    private Integer contentModelId;
    private String title;
    private String description;
    private Instant created;
    private List<Attribute> attributes;

    public Integer getContentModelId() { return contentModelId; }

    public void setContentModelId(Integer contentModelId) { this.contentModelId = contentModelId; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreated() { return created; }

    public void setCreated(Instant created) { this.created = created; }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
