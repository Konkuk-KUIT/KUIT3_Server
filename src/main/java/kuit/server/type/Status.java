package kuit.server.type;

import kuit.server.common.exception.DatabaseException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Status {
  ACTIVE, DORMANT, DELETED;

  private static Map<String, Status> map = new HashMap<>();

  static {
    for (Status status : Status.values()) {
      map.put(status.toString().toLowerCase(), status);
    }
  }

  public static Optional<Status> get(String status) {
    return Optional.ofNullable(map.get(status));
  }
}
