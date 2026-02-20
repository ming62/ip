# Gus — User Guide

Gus is a simple personal assistant chatbot with a GUI for managing tasks.

## Getting started

Requirements
- Java 17 
- A JavaFX-capable environment 

Run the app
- In your IDE: run `Launcher` or `Main` 
- Or run the packaged jar: `java -jar gus.jar` 

When the GUI opens, type commands into the input box and press Enter or the Send button.

Data file
- Tasks are saved to `./data/gus.txt` relative to the project working directory.
- Tasks are only saved on command `bye`

## Commands (detailed)

All commands are typed into the GUI input box.

1. list
   - Usage: `list`
   - Description: Display all tasks with their index, status and brief details.
   - Example: `list`

2. todo
   - Usage: `todo <title>`
   - Description: Add a Todo task (no date/time).
   - Example: `todo Buy groceries`

3. deadline
   - Usage: `deadline <title> /by <date/time>`
   - Description: Add a Deadline task with a due date/time.
   - Example: `deadline Submit assignment /by 2026-03-01 2359`
   - Notes: Date/time format: `YYYY-MM-DD HHmm`.

4. event
   - Usage: `event <title> /from <date/time> /to <date/time>`
   - Description: Add an Event task that occurs at a specified time.
   - Example: `event Team meeting /from 2026-02-25 1400 /to 2026-02-25 1800`
   - Notes: Date/time format: `YYYY-MM-DD HHmm`.

5. delete
   - Usage: `delete <index>`
   - Description: Remove the task at the given list index.
   - Example: `delete 3`
   - Notes: Deleted tasks are removed from the saved data file on next save.

6. find
   - Usage: `find <keyword>`
   - Description: Search and display tasks whose descriptions contain the keyword.
   - Example: `find book`
   - Notes: Matching is a case-sensitive substring search (uses `String.contains`).
7. mark
   - Usage: `mark <index> [<index> ...]`
   - Description: Mark one or more tasks (by list index) as completed.
   - Example: `mark 2` or `mark 1 3 4`
   - Notes: Use `list` to find task indices. Multiple indices may be provided separated by spaces.

8. unmark
   - Usage: `unmark <index> [<index> ...]`
   - Description: Mark one or more tasks (by list index) as not done.
   - Example: `unmark 2` or `unmark 1 3`
   - Notes: Use `list` to find task indices. Multiple indices may be provided separated by spaces.

9. pri
   - Usage: `pri <level> <index> [<index> ...]`
   - Description: Set the priority level of one or more tasks. Valid priority levels: `TOP`, `MID`, `LOW`, `NONE`.
   - Example: `pri TOP 2` or `pri MID 1 3`
   - Notes: Priority level is validated; use `TOP`, `MID`, `LOW`, or `NONE`.

10. on
   - Usage: `on <date>`
   - Description: Show tasks that occur on the specified date.
   - Example: `on 2026-02-25`
   - Notes: Date format: `yyyy-MM-dd` (e.g., `2026-02-25`).

11. bye
   - Usage: `bye`
   - Description: Saves the task list to the local file and exits the application.
   - Example: `bye`

## Command parsing notes

- Indexing: All task indices referenced by commands are 1-based (e.g., `1` refers to the first task shown by `list`).
- Multiple indices: `mark`, `unmark`, `delete`, and `pri` accept multiple space-separated indices (e.g., `mark 1 3 4`). When deleting multiple indices the app parses indices and processes them in reverse order to avoid shifting problems.
- Deadline/event separators: `deadline` requires the literal ` /by ` (space-slash-by-space). `event` requires ` /from ` and ` /to ` separators (space before and after the slash token).
- Date/time formats:
   - `deadline` and `event` timestamps must be in `yyyy-MM-dd HHmm` (example: `2026-03-01 2359`). Note: the time uses 24-hour format without a colon.
   - `on` expects a date only in `yyyy-MM-dd` (example: `on 2026-02-25`).
- `pri` usage: `pri <level> <index> [<index> ...]` where `<level>` must be one of `TOP`, `MID`, `LOW`, or `NONE`. The level is validated and stored uppercase.
