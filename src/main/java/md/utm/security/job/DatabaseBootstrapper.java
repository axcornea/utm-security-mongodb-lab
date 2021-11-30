package md.utm.security.job;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.utm.security.domain.Meeting;
import md.utm.security.domain.User;
import md.utm.security.repository.MeetingRepository;
import md.utm.security.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Slf4j
@RequiredArgsConstructor
public class DatabaseBootstrapper implements ApplicationRunner {

  private final UserRepository userRepository;
  private final MeetingRepository meetingRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    log.info("Started populating database");

    var users = buildUsers();
    users.forEach(user -> ensureEntityExists(user, userRepository));
    log.info("Inserted {} users", users.size());

    var meetings = buildMeetings();
    meetings.forEach(meeting -> ensureEntityExists(meeting, meetingRepository));
    log.info("Inserted {} meetings", meetings.size());

    log.info("Finished populating database");
  }

  private List<User> buildUsers() {
    return List.of(
      new User("Alice", "alice@xcompany.com"),
      new User("Bob", "bob@xcompany.com"),
      new User("Mike", "mike@xcompany.com")
    );
  }

  private List<Meeting> buildMeetings() {
    var users = userRepository.findAll();

    return List.of(
        new Meeting("Project kickoff", users.get(0), users.subList(1, users.size())),
        new Meeting("Sprint retrospection", users.get(1), users.subList(0, 2))
    );
  }

  // This method is just good enough for this lab, not the most efficient approach
  private <T> void ensureEntityExists(T entity, MongoRepository<T, String> repository) {
    // Attempt to find it in the repository
    for (T object : repository.findAll()) {
      if (object.equals(entity)) {
        return;
      }
    }

    // Save it if not found in repository
    repository.save(entity);
  }
}
