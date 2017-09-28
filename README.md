# Game of Life by Giorgio Gross

This is a demonstration application for Convey's Game of Life. One cycle ("day") takes 10 seconds (5 seconds night,
5 seconds day). On midnight the rules of the game of life are executed and some cells die and some cells are revived.
As the user can manually revive cells (by clicking on them) this implementations offers a somehow playful character.
In contrast to several other implementation I chose not to offer functions like adjusting the grid size, time per cycle,
and so on but instead show off an understandable and neat presentation of the game of life.


### Code Structure
The App is structured in such a way that the individuality of each cell is represented throughout the whole code structure.
That means that there is no local data store which maintains all information about all cells but instead each cell
references its own data store. It stores the cells properties and references (to its neighbours). Render calls are
delegated. Cells observe their neighbours and the environment to change their state based on the available information.

Instantiation and localization of each component (cell, environment) happens in the main logic controller (App.java).
This class also handles the render loop.


### Key-features
 * Separated data, view and logic
 * Modular code built on top of delegation
 * Self-contained cells, no complex central logic required
 * Scalable to several hundreds of cells (...though there are better approaches to simulate several thousands of cells.
 The bottleneck of this implementation is the heavy use of delegation in the UI thread, but as it reduces complexity and
 perfectly fits the chosen cell grid's scale I decided not to use a multithreading approach)
 * Extensible as new behavour, styles and patterns can be easily integrated into parent classes
 * Neat UI (from my point of view.. ;) )