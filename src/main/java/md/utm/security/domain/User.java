package md.utm.security.domain;

import com.bol.secure.Encrypted;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
  @Id
  private String id;
  @Encrypted
  private String name;
  @Encrypted
  private String email;

  public User(String name, String email) {
    this.id = null;
    this.name = name;
    this.email = email;
  }

  @Override
  public boolean equals(Object other) {
    return other instanceof User && equals((User) other);
  }

  private boolean equals(User other) {
    return name.equals(other.name)
        && email.equals(other.email);
  }
}
