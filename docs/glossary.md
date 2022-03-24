# Glossary

+ Field: consist of cells. It is what the player sees, and able to modify.
+ Cell: basic building block of the field. A cell contains a single number.
+ Slide: the action the player can take to modify the state of the game.

## Example

The table below shows a field. 
For example the cell at row 1, column 1 has the value 0, while cell at row 2, column 2 has value 2.

| | Column 1 |  Column 2 | Column 3 |
| --- | ---: | ----: | ---: |
| Row 1 | 0 | 2 | 0 |
| Row 2 | 2 | 2 | 2 |
| Row 3 | 0 | 2 | 0 |

The slide _direction_ examples below show the field after a slide to the given direction.

### Slide up

| | Column 1 |  Column 2 | Column 3 |
| --- | ---: | ----: | ---: |
| Row 1 | 2 | 4 | 2 |
| Row 2 | 0 | 2 | 0 |
| Row 3 | 0 | 0 | 0 |

### Slide down

| | Column 1 |  Column 2 | Column 3 |
| --- | ---: | ----: | ---: |
| Row 1 | 0 | 0 | 0 |
| Row 2 | 0 | 2 | 0 |
| Row 3 | 2 | 4 | 2 |

### Slide right

| | Column 1 |  Column 2 | Column 3 |
| --- | ---: | ----: | ---: |
| Row 1 | 0 | 0 | 2 |
| Row 2 | 0 | 2 | 4 |
| Row 3 | 0 | 0 | 2 |

### Slide left

| | Column 1 |  Column 2 | Column 3 |
| --- | ---: | ----: | ---: |
| Row 1 | 2 | 0 | 0 |
| Row 2 | 4 | 2 | 0 |
| Row 3 | 2 | 0 | 0 |
