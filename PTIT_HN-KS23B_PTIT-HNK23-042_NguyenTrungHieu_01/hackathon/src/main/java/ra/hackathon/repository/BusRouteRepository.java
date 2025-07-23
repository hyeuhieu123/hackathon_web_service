package ra.hackathon.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.hackathon.model.entity.BusRoute;

@Repository
public interface BusRouteRepository  extends JpaRepository<BusRoute, Long>, JpaSpecificationExecutor<BusRoute> {
    @Query("UPDATE BusRoute br SET br.status = false WHERE br.bus_route_id = :id")
    @Modifying
    @Transactional
    public void updateStatusById(@Param("id") Long id);
}
