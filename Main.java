import java.util.Arrays;
import java.util.Optional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
	public static final int OPTION_SEARCH = 0, OPTION_EDIT = 1, OPTION_ADD_ROW = 2, 
		OPTION_ADD_COLUMN = 3, OPTION_DISPLAY = 4, OPTION_SORT = 5, OPTION_RESET = 6, OPTION_EXIT = 7;

	public static void main(String[] args) {

		Exercise2 exercise2 = null;

		try {
			boolean isDefaultFile = 
				Utility.getBooleanInput("Table File [0] Choose File, [1] Use Default:", '1', '0');

			if (!isDefaultFile) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
				fileChooser.setFileFilter(filter);

				int result = fileChooser.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					exercise2 = new Exercise2(fileChooser.getSelectedFile());
				}
				else {
					System.out.println("File chooser cancelled. Using default table file.");
					isDefaultFile = true;		
				}
			}
			
			if (isDefaultFile) {
				Optional<String> tableResourcePath = Utility.getResourcePath("table.txt");

				if (!tableResourcePath.isPresent()) {
					throw new FileNotFoundException();
				}

				exercise2 = new Exercise2(tableResourcePath.get());
			}

			exercise2.displayTable();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		
		} catch (IOException e) {
			System.out.println("Table parsing failed");
			return;
		}

		boolean isExit = false;

		do {
			System.out.println("[0] Search, [1] Edit, [2] Add Row, [3] Add Column, [4] Display, " + 
				"[5] Sort, [6] Reset, [7] Exit");
			int option = Utility.getIntegerInput("Enter Option:", 0, 7);
			int[] cellLocation;

			switch (option) {
				case OPTION_SEARCH:
					String searchString = Utility.getStringInput("Enter Search String:");
					exercise2.searchTable(searchString);
					break;

				case OPTION_EDIT: 
					cellLocation = exercise2.getCell(false);
					boolean isRightPart = 
						Utility.getBooleanInput("[0] Left Part, [1] Right Part:", '1', '0');

					String str = Utility.getStringInput(
						"Enter New String:", Exercise2.INNER_CELL_DELIMITER + Utility.EMPTY_STRING);

					exercise2.editCell(str, cellLocation[0], cellLocation[1], isRightPart);
					exercise2.persistTable();
					exercise2.displayTable();
					break;

				case OPTION_ADD_ROW:
					exercise2.addRow();
					exercise2.persistTable();
					exercise2.displayTable();
					break;

				case OPTION_ADD_COLUMN: 
					cellLocation = exercise2.getCell(true);
					String leftStr = Utility.getStringInput(
						"Enter Left String:", Exercise2.INNER_CELL_DELIMITER + Utility.EMPTY_STRING);
					String rightStr = Utility.getStringInput(
						"Enter Right String:", Exercise2.INNER_CELL_DELIMITER + Utility.EMPTY_STRING);

					exercise2.addColumn(leftStr, rightStr, cellLocation[0], cellLocation[1]);
					exercise2.persistTable();
					exercise2.displayTable();
					break;

				case OPTION_DISPLAY:
					exercise2.displayTable();
					break;

				case OPTION_SORT: 
					int row = 
						Utility.getIntegerInput("Enter Row to Sort:", 0, exercise2.getRowCount() - 1);

					boolean isAscending = 
						Utility.getBooleanInput("[0] Descending, [1] Ascending:", '1', '0');

					exercise2.sortRow(row, isAscending);
					exercise2.persistTable();
					exercise2.displayTable();
					break;

				case OPTION_RESET:
					row = Utility.getIntegerInput("Enter Desired Number of Rows:", 0, 5000);
					int col = Utility.getIntegerInput("Enter Desired Number of Columns:", 0, 5000);

					exercise2.resetTable(row, col);
					exercise2.persistTable();
					exercise2.displayTable();
					break;

				case OPTION_EXIT:
					isExit = true;
			}	

		} while (!isExit);
	}
}