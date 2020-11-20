package com.gladunalexander.videoservice.persistance;

import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoFullTextSearch {

    private final EntityManager entityManager;

    @Transactional(readOnly = true)
    public Page<Video> search(Pageable pageable, String text) {
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Video.class)
                .get();

        Query containsTextQuery = qb.keyword()
                .onFields(Video_.TITLE)
                .matching(text)
                .createQuery();

        List<Video> videos = fullTextEntityManager
                .createFullTextQuery(containsTextQuery, Video.class)
                .setFirstResult(Math.toIntExact(pageable.getOffset()))
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<>(videos);
    }

}
