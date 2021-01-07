package si.fri.rso.rsocontentmodel.lib;

import java.time.Instant;
import java.util.HashMap;

public class Attribute {

    private Integer attributeId;
    private String title;
    private String type;

    public Integer getAttributeId() { return attributeId; }

    public void setAttributeId(Integer contentModelId) { this.attributeId = contentModelId; }

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
