package ra.hackathon.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.hackathon.model.entity.Bus;
import ra.hackathon.model.entity.BusRoute;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>, JpaSpecificationExecutor<Bus> {
    @Query("UPDATE Bus b SET b.status = false WHERE b.bus_id = :id")
    @Modifying
    @Transactional
    public void updateStatusById(@Param("id") Long id);

    boolean existsBusByBusRoutes(List<BusRoute> busRoutes);
}
