package si.fri.rso.rsocontentmodel.models.converters;

import si.fri.rso.rsocontentmodel.lib.Attribute;
import si.fri.rso.rsocontentmodel.models.entities.AttributeEntity;

public class AtributeConverter {

    public static Attribute toDto(AttributeEntity entity) {
        Attribute dto = new Attribute();
        dto.setAttributeId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setType(entity.getType());

        return dto;
    }

    public static AttributeEntity toEntity(Attribute dto) {
        AttributeEntity entity = new AttributeEntity();
        entity.setId(dto.getAttributeId());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());

        return entity;
    }

}

