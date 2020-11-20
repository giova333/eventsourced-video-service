package com.gladunalexander.videoservice.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.UUID;

public interface VideoRepository extends JpaRepository<Video, UUID>, JpaSpecificationExecutor<Video> {

    default Page<Video> findAll(Pageable pageable, MultiValueMap<String, String> filter) {
        return findAll(
                withAccounts(filter),
                pageable);
    }

    private Specification<Video> withAccounts(MultiValueMap<String, String> filter) {
        return (root, query, cb) -> {

            List<String> accounts = filter.get(Video_.ACCOUNT_ID);

            if (CollectionUtils.isEmpty(accounts)) {
                return cb.isTrue(cb.literal(true));
            }
            CriteriaBuilder.In<Object> inClause = cb.in(root.get(Video_.ACCOUNT_ID));
            accounts.stream()
                    .map(UUID::fromString)
                    .forEach(inClause::value);

            return inClause;
        };
    }

}
