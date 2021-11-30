package md.utm.security.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.utm.security.domain.Meeting;
import md.utm.security.domain.User;
import md.utm.security.repository.MeetingRepository;
import md.utm.security.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
@Slf4j
public class EntityPresenter implements ApplicationRunner {

  private final UserRepository userRepository;
  private final MeetingRepository meetingRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println();
    System.out.println("Users from the MongoDB:");
    for (User user : userRepository.findAll()) {
      System.out.println(user.toString());
    }

    System.out.println();
    System.out.println("Meetings from the MongoDB:");
    for (Meeting meeting : meetingRepository.findAll()) {
      System.out.println(meeting.toString());
    }

    System.out.println();
  }
}
