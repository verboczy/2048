# Test case file structure

## Input file

N

C00 C01 C02

C10 C11 C12

C20 C21 C22

N = count of rows = count of columns.
CXY marks the cell at the Xth row and Yth column.

## Output file

N

C

S

C00 C01 C02

C10 C11 C12

C20 C21 C22

N = count of rows = count of columns.

C marks if we expect there was at least one change in the field. 0 = false, 1 = true.

S marks the expected score.

CXY marks the expected cell at the Xth row and Yth column.