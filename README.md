<html>
  <h3>
    Billards Simulator
  </h3>
  <body>
    
  <pre>
    
      Pool is a common "table-based" game (similar to Billiards and Snooker). The mechanics are simple: one or more balls can be moved on a table. 
      They bounce off the walls of the table.When a ball is hit it travels in a straight line until it hits a side of the pool. It is then "reflected" from that side, and continues moving. 
      The ball may also collide with other balls on the table. In any case the friction between the ball and the table cause the ball to move slowly and eventually stop.
  <hr>
       interface PoolSimulator that offers only the following methods:

void start(int x,int y,int radius,int speed,double dx,double dy) throws IllegalArgumentException : Start the simulation with a ball at the given position, with the given radius and velocity. This method throws an exception if its parameters are invalid. The conditions for invalidity are dependent on the implementation.

void advance(); : When called, this method advances the simulation by one discrete step (to the next bounce, or the ball stopping).

int getTableWidth(); and int getTableHeight(); : When called, these methods return the width and height of the table for this simulation.

double getBallPositionX(); , double getBallPositionY(); and double getBallRadius() : When called, these methods return the x and y coordinate of the current position of the ball, and the radius of the ball respectively.

double getBallVelocityX(); and double getBallVelocityY(); : When called, these methods return the x and y components of the current velocity of the ball, respectively.

String getStatus() : This method returns the status of the simulation at the current step. The contents and format of the string are decided by the implementation.

The overall way to use this would be to create the simulation, then start it by calling the first method. Then one would advance the simulation in steps.
<hr>

The implementation called SimplePoolSimulator that implements the PoolSimulator interface.
It must have the following characteristics:

The project will have one constructor SimplePoolSimulator(int width,int height,String type) throws IllegalArgumentException . It should initialize the table accordingly.
Attempts to pass a non-positive width or height should cause the constructor to throw an IllegalArgumentException . Similarly, passing the type as something other than "simple" and "friction" should also cause the constructor to throw an IllegalArgumentException .

The start method will throw an exception if the ball does not start completely inside the pool table.

The start method will allow specifying the last two parameters so that they do not create a vector of unit length (e.g. is an acceptable input). Remember that the math for the advancement still requires the direction to be normalized.
The advance method will advance to the next collision of the ball with some side of the table (or ball stoppage). The type of simulation of the ball should depend on the last parameter passed to the constructor ("simple" should simulate the simplistic physics by decrementing the speed by 
 for each collision. "friction" should simulate the Newtonian physics with a coefficient of friction of).

The advance method will implement one simple optimization: depending on the direction in which the ball is headed, only two of the four sides of the table need to be checked (e.g. if the ball is headed to the left and downwards, then it can collide only with the left and bottom sides of the table. So the right and top sides need not be checked).
The getStatus method will return a string "Status: SSS": where SSS is one of "Ball not set up", "Simulation started", "Ball hit bottom edge", "Ball hit top edge", "Ball hit left edge", "Ball hit right edge", "Ball is stationary". The first option should be used if this method is called before start . The second option should be used if this method is called after start but before the first call to advance() , 
and the last option should be used if a previous call to advance() has resulted in the ball stopping.
  </body>
</html>
