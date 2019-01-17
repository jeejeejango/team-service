package org.jeejeejango.repository;

import org.jeejeejango.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jeejeejango
 * @since 17/01/2019 4:08 PM
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
