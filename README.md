# COMP3111 Project - Webscrapper - Group 11

[Link to project requirements and skeleton code](https://github.com/khwang0/2018F-COMP3111)

[Link to javadoc](https://chihimng.com/COMP3111_Project)

## Group Members and Task Allocation
- Ng Chi Him: Basic 2, 3
- Wong Hiu Nam: Basic 4, 5
- Chan Ngo Hin: Basic 1, 6

## Notes
- [Task 2] Craigslist prices are multiplied by 8 to convert USD to HKD
- [Task 2] DCFever is used as custom data source
- [Task 3] Pagination is implemented for both Craigslist and DCFever
- [Task 3] Progress is printed on gradle console instead of UI console, see https://github.com/khwang0/2018F-COMP3111/issues/50
- [Task 2, 3] "PS4" is used as search keyword in unit tests, 3 pages were returned from craigslist and 14 pages were returned from dcfever at the time of development
- [Task 6] "Last Search" is only enabled when there is at least two searches recorded
- [Task 6] Since "Close" will clear all the search record, "Last Search" is enable after recording two searches if "Close" is pressed
