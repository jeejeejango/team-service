package org.jeejeejango.web;

import lombok.extern.slf4j.Slf4j;
import org.jeejeejango.entity.Team;
import org.jeejeejango.repository.TeamRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

/**
 * @author jeejeejango
 * @since 17/01/2019 4:09 PM
 */
@RestController
@RequestMapping("/api/v1/teams")
@Slf4j
public class TeamRestController {

    private TeamRepository teamRepository;


    public TeamRestController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @GetMapping
    public ResponseEntity<Collection<Team>> getAllTeams(Pageable pageable) {
        if (log.isInfoEnabled()) {
            log.info("find all team, pageable {}", pageable);
        }
        return new ResponseEntity<>(teamRepository.findAll(pageable).getContent(), HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<Collection<Team>> findTeamByName(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String description,
                                                           Pageable pageable) {
        Team team = new Team(name, description);

        if (log.isInfoEnabled()) {
            log.info("search team {}, pageable {}", team, pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withMatcher("name", contains())
            .withMatcher("description", contains())
            .withIgnorePaths("id")
            .withIgnoreCase().withIgnoreNullValues();

        return ResponseEntity.ok(teamRepository.findAll(Example.of(team, matcher), pageable).getContent());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (log.isInfoEnabled()) {
            log.info("team id {} found {}", id, team.isPresent());
        }
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody @Valid Team team) {
        team.setId(null);
        team = teamRepository.save(team);
        if (log.isInfoEnabled()) {
            log.info("add team by id {}", team.getId());
        }
        return ResponseEntity.ok(team);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable("id") Long id, @RequestBody @Valid Team team) {
        if (!id.equals(team.getId())) {
            if (log.isWarnEnabled()) {
                log.warn("update team id does not match", id);
            }
            return ResponseEntity.badRequest().build();
        }
        if (log.isInfoEnabled()) {
            log.info("update team by id {}", id);
        }
        teamRepository.save(team);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id) {
        if (log.isInfoEnabled()) {
            log.info("delete team by id {}", id);
        }
        teamRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
