package core.env_config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Environment {
    private String url;
    private int max_attempts;
}
