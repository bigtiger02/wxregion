package cn.luojh.reagion.repo.repository;

import cn.luojh.reagion.repo.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findByNameAndLevel(String name,int level);

}
