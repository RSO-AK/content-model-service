package si.fri.rso.rsocontentmodel.models.converters;

import si.fri.rso.rsocontentmodel.lib.ContentModel;
import si.fri.rso.rsocontentmodel.models.entities.ContentModelEntity;

import java.util.stream.Collectors;

public class ContentModelConverter {

    public static ContentModel toDto(ContentModelEntity entity) {
        ContentModel dto = new ContentModel();
        dto.setContentModelId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setDescription(entity.getDescription());
        dto.setTitle(entity.getTitle());
        dto.setAttributes(entity.getAttributes().stream().map(atr -> AtributeConverter.toDto(atr)).collect(Collectors.toList()));

        return dto;
    }

    public static ContentModelEntity toEntity(ContentModel dto) {
        ContentModelEntity entity = new ContentModelEntity();
        entity.setId(dto.getContentModelId());
        entity.setCreated(dto.getCreated());
        entity.setDescription(dto.getDescription());
        entity.setTitle(dto.getTitle());
        entity.setAttributes(dto.getAttributes().stream().map(atr -> AtributeConverter.toEntity(atr)).collect(Collectors.toList()));

        return entity;
    }

}

