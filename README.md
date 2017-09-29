# Game of Life by Giorgio Gross

This is a demonstration application for Convey's Game of Life. One cycle ("day") takes 10 seconds (5 seconds night,
5 seconds day). On midnight the rules of the game of life are executed and some cells die and some cells are revived.
As the user can manually revive cells (by clicking on them) this implementations offers a somehow playful character.
In contrast to several other implementation I chose not to offer functions like adjusting the grid size, time per cycle,
and so on but instead show off an understandable and neat presentation of the game of life.


### Code Structure
The App is todo..

Instantiation and localization of each component (cell, environment) happens in the main logic controller (App.java).
This class also handles the render loop.


### Key-features
 * Separated cell state, view and logic
 * Modular code built on top of delegation
 * Self-contained cells presenting themselves to the user while encapsulating game of life logic in a state manager
 * Scalable to several hundreds of cells (...though there are better approaches to simulate several thousands of cells.
 The bottleneck of this implementation is the heavy use of delegation in the UI thread, but as it reduces complexity and
 perfectly fits the chosen cell grid's scale I decided not to use a multithreading approach)
 * Extensible as new behaviour, styles and patterns can be easily integrated without changing existing code
 * Neat UI (from my point of view.. ;) )
