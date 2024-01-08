package sim;

/**
 * The PoolSimulator interface represents a contract for a pool simulation system. It defines
 * methods to manage the simulation, retrieve ball and table information, and get the simulation
 * status.
 */
public interface PoolSimulator {

  void start(int x, int y, int radius, int speed, double dx, double dy)
      throws IllegalArgumentException;

  void advance();

  int getTableWidth();

  int getTableHeight();

  double getBallPositionX();

  double getBallPositionY();

  double getBallRadius();

  double getBallVelocityX();

  double getBallVelocityY();

  String getStatus();


}
