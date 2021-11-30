package md.utm.security.domain;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Meeting {

  @Id
  private String id;
  private String title;
  private User author;
  private List<User> guests;

  public Meeting(String title, User author, List<User> guests) {
    this.id = null;
    this.title = title;
    this.author = author;
    this.guests = guests;
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof Meeting && equals((Meeting) other);
  }

  private boolean equals(Meeting other) {
    return title.equals(other.title)
        && author.equals(other.author)
        && guests.equals(other.guests);
  }
}
