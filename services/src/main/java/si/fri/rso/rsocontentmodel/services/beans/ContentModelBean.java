package si.fri.rso.rsocontentmodel.services.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.fri.rso.rsocontentmodel.lib.ContentModel;
import si.fri.rso.rsocontentmodel.models.converters.ContentModelConverter;
import si.fri.rso.rsocontentmodel.models.entities.ContentModelEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class ContentModelBean {

    private Logger log = Logger.getLogger(ContentModelBean.class.getName());

    @Inject
    private EntityManager em;

    @Timed(name = "getContentModel_time")
    @Counted(name = "getContentModel_count")
    public List<ContentModel> getContentModel() {

        TypedQuery<ContentModelEntity> query = em.createNamedQuery(
                "ContentModelEntity.getAll", ContentModelEntity.class);

        List<ContentModelEntity> resultList = query.getResultList();

        return resultList.stream().map(ContentModelConverter::toDto).collect(Collectors.toList());

    }

    @Timed(name = "getContentModelFilter_time")
    @Counted(name = "getContentModelFilter_count")
    public List<ContentModel> getContentModelFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                                                         .build();

        return JPAUtils.queryEntities(em, ContentModelEntity.class, queryParameters).stream()
                       .map(ContentModelConverter::toDto).collect(Collectors.toList());
    }

    @Timed(name = "getContentModelId_time")
    @Counted(name = "getContentModelId_count")
    public ContentModel getContentModel(Integer id) {

        ContentModelEntity contentModelEntity = em.find(ContentModelEntity.class, id);

        if (contentModelEntity == null) {
            throw new NotFoundException();
        }

        ContentModel contentModel = ContentModelConverter.toDto(contentModelEntity);

        return contentModel;
    }

    @Timed(name = "createContentModel_time")
    @Counted(name = "createContentModel_count")
    public ContentModel createContentModel(ContentModel contentModel) {

        ContentModelEntity contentModelEntity = ContentModelConverter.toEntity(contentModel);

        try {
            beginTx();
            em.persist(contentModelEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (contentModelEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return ContentModelConverter.toDto(contentModelEntity);
    }

    @Timed(name = "putContentModel_time")
    @Counted(name = "putContentModel_count")
    public ContentModel putContentModel(Integer id, ContentModel contentModel) {

        ContentModelEntity c = em.find(ContentModelEntity.class, id);

        if (c == null) {
            return null;
        }

        ContentModelEntity updatedContentModelEntity = ContentModelConverter.toEntity(contentModel);

        try {
            beginTx();
            updatedContentModelEntity.setId(c.getId());
            updatedContentModelEntity = em.merge(updatedContentModelEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return ContentModelConverter.toDto(updatedContentModelEntity);
    }

    @Timed(name = "deleteContentModel_time")
    @Counted(name = "deleteContentModel_time_count")
    public boolean deleteContentModel(Integer id) {

        ContentModelEntity contentModel = em.find(ContentModelEntity.class, id);

        if (contentModel != null) {
            try {
                beginTx();
                em.remove(contentModel);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}
