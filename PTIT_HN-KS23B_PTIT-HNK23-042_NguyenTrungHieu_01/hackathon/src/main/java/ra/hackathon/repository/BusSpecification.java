package ra.hackathon.repository;

import org.springframework.data.jpa.domain.Specification;
import ra.hackathon.model.entity.Bus;

public class BusSpecification {
    public static Specification<Bus> hasStatus(String status) {
        return (root, query, cb) -> {
            if (status == null) {
                return cb.conjunction();
            }
            return cb.like(root.get("status"), status);

        };
    }
}
