Exist Software Labs, Inc. (PH)  
Exist Code Camp (ECC)  

### Exercise 2: Advance Java  
Create a MxN table that can hold 2 string values (left and right) for each cells.  
The table should be read from an external text file.
Provide your own delimiter for the 2 strings.
The program should be able to accomplish the following:  

1.  String Search  
    Search the whole table for the given string. If a match is found, then display its occurrence in   
    that column together with its respective row, column and whether it is the left or right string.  

2.  Edit Cell  
    Edit the string value of the designated non-null cell. Additionally, ask the user regarding 
    which string side (left or right) should be edited. Null cells cannot be edited.

3.  Reset Table  
    Reset the whole table by initially inquiring for the new size of the table, then generate a new  
    table based on the specified size with randomized strings for their value.  

4.  Print Table  
    Display the contents of the table.  

5.  Add Row
    Add a row at the bottom of the table. All cells are null except for the first column of the 
    generated row, which will contain a randomized string by default.

6.  Add Cell
    Add cells on null cells by requesting two string inputs from the user.

7.  Sort Row
    Sort a given row either in natural or reverse order. The 2 strings contained on each cells 
    should be considered as a single string during comparison.

NOTE: All table manipulations should persist within the text file.  
