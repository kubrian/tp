---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# TutorConnect User Guide

TutorConnect is a **CLI-based student management tool designed for tutors handling multiple courses across different platforms.**
It provides a centralized solution for organizing student contacts and managing tasks—without the complexity of a full-fledged learning management system.
If you can type fast, TutorConnect can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your TutorConnect.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


## Contact: `contact`

A contact is an entry with information of a student. It supports the following commands.

### Adding a contact: `add`
Adds a contact to the app.

Format: `contact add --id ID --name NAME --email EMAIL --course COURSE --group GROUP [--tag TAG(S)]`

**Tip:** A contact can have any number of tags (including 0)

Examples:
* `contact add --id A1234567A --name John Doe --email johnd@example.com --course CS50 --group T01 --tag friends owesMoney`

### Editing an contact: `edit`
Edits the details of the contact identified by the index number in the currently displayed contact list. Only the fields specified in the command will be updated; all others remain unchanged.

Format:
`contact edit INDEX [--id ID] [--name NAME] [--email EMAIL] [--course COURSE] [--group GROUP] [--tag TAG(S)]`

* `INDEX` refers to the contact to be edited, based on its position in the displayed contact list.

* Fields that are not included in the command will remain unchanged. You can update any combination of fields at once.

* If `--tag` is provided with no value, all tags will be cleared.

Examples:

* `contact edit 1 --email johndoe@example.com`

* `contact edit 3 --name Jack Doe`

* `contact edit 4 --group CS2103T`

### Deleting a contact: `delete`

Deletes the contact identified by the index number used in the displayed contact list.

Format: `contact delete INDEX`

Example:
* `contact delete 1`

### Displaying information of contact: `info`
Displays the complete information belonging to the contact identified by the index number used in the displayed contact list.

Format: `contact info INDEX`

Example:
* `contact info 1`

### Clearing contact list: `clear`
Clears the contact list.

Format: `contact clear`

### Tagging a contact: `tag`
Adds one or more tags to the specified contact. Tags help categorize and filter contacts more easily (e.g., by priority, role, type, etc.).

Format: `contact tag INDEX --tag TAG(S)`

* `INDEX` refers to the index of the contact in the currently displayed contact list.

* Duplicate tags (already added to the contact) are not allowed.

Example:
* `contact tag 1 --tag important need-help`

### Untagging a contact: `untag`
Removes one or more tags from the specified contact.

Format: `contact untag INDEX --tag TAG(S)`

* `INDEX` refers to the index of the contact in the currently displayed contact list.

* Tags are case-sensitive and must match the tag(s) assigned to the contact.

Example:
* `contact untag 1 --tag important weekly`

### Filtering contacts: `filter`
Filters the list of contacts based on one or more criteria. You can search by contacts name, time, location, tags, or linked contacts using logical operators to combine multiple values.

Format:
`contact filter --COLUMN OPERATOR: VALUE(S) [...]`

Supported columns:
* `--name`: contact name.
* `--id`: contact id.
* `--email`: contact email.
* `--course`: contact course.
* `--group`: contact group.
* `--tag`: tags.

Operators (optional):
* `and` (default): All values must match.
* `or`: At least one value must match.
* `nand`: None of the values must match.
* `nor`: Reject all values.

If an operator is not provided, it defaults to `and`. If an unrecognized operator is provided, it will be treated as a value. If multiple valid operators are provided, the first one will be applied and the rest will be treated as values.

**Value formats**:

Name, ID, email, course, group, and tags:
* Provide one or more keywords separated by spaces.
* Keywords are case-insensitive and support partial matches.

Examples:
* `contact filter --id or: 12 13`.
    * Find students with ID 12 or 13.

* `contact filter --name John Doe --course CS1010S --group or: T01 T02 T03`
    * Find contacts with both "Darren" and "Tan" in their name who enroll in course CS1010S and class T01, T02, or T03.

* `contact filter --name nand: enemy Hater --tag and: handsome smart`
    * Find contacts whose names do not contain "enemy" and "Hater" and are tagged with both "handsome" and "smart".

### Listing all features: `help`

Lists all features 

Format: `help`

### Listing all subcommands of a feature: `help`

Lists all subcommands of a feature.

Format: `help FEATURE`

Examples:
* `help contact` shows all subcommands of contact feature.
* `help todo` shows all subcommands of todo feature.
* `help event` shows all subcommands of event feature.

### Listing help message of a subcommand of a feature: `help`

Lists help message of a subcommand of a feature.

Format: `help FEATURE SUBCOMMAND`

Examples:
* `help contact add` shows help message of `contact add` command.
* `help todo mark` shows help message of `todo mark` command.
* `help event log` show help message of `event log` command.

### Exiting the program : `exit`/`quit`/`kill`/`bye`

Exits the program.

Format: `exit`/`quit`/`kill`/`bye`

### Saving the data

Data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Data are saved automatically as a JSON file `[JAR file location]/data/[FILE_NAME].json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`

## Event: `event`

### Adding an event: `add`
Adds an event to the to the address book.

Format: `event add --name NAME --start START_DATETIME --end END_DATETIME --location LOCATION [--tag TAG(S)]`

**Important:** start time and end time format is `YY-MM-DD HH:MM`, where `HH` is in 24 hour format and start time must be earlier than end time.

**Tip:** An event can have any number of tags (including 0)

Examples:
* `event add --name CS2040S tutorial --start 24-08-26 12:00 --end 24-08-26 14:00 --location NUS SoC COM1 --tag algorithms`
* `event add --name CS1010S tutorial --start 25-01-31 14:00 --end 25-01-31 18:00 --location NUS BIZ2 --tag tut29 tut30`

### Editing an event: `edit`
Edits the details of the event identified by the index number in the currently displayed event list. Only the fields specified in the command will be updated; all others remain unchanged.

Format:
`event edit INDEX [--name NAME] [--start START_TIME] [--end END_TIME] [--location LOCATION] [--contact CONTACT_INDEX/INDICES] [--tag TAG(S)]`

* `INDEX` refers to the event to be edited, based on its position in the displayed event list.

* Fields that are not included in the command will remain unchanged. You can update any combination of fields at once.

* If `--tag` is provided with no value, all tags will be cleared.

Important:

* `INDEX` must be a positive integer.

* `CONTACT_INDEX/INDICES` refers to the index(es) in the contact list currently displayed on the right.

Examples:

* `event edit 1 --name Project Meeting`

* `event edit 2 --start 25-03-15 10:00 --end 25-03-15 12:00`

* `event edit 3 --location COM2-0208 --tag project`

* `event edit 4 --contact 1 2 3`

### Deleting an event: `delete`

Deletes the event identified by the index number used in the displayed event list. 

Format: `event delete INDEX`

Example:
* `event delete 1`

### Displaying information of event: `info`
Displays the complete information belonging to the event identified by the index number used in the displayed event list.

Format: `event info INDEX`

Example:
* `event info 1`

### Clearing event list: `clear`
Clears the event list.

Format: `event clear`

### Linking contacts: `link`
Associates one or more contacts to an event. Useful for keeping track of which contacts are involved in a particular event.

Format: `event link INDEX --contact CONTACT_INDEX/INDICES`

* `INDEX` refers to the index of the event in the displayed event list.

* `CONTACT_INDEX/INDICES` refers to the index(es) of the contact(s) in the currently displayed contact list.

* You can link multiple contacts by providing more than one contact index.

* At least one `CONTACT_INDEX` must be provided.

Examples:
* `event link 1 --contact 1 3 4`

### Unlinking contacts: `unlink`
Removes the association between one or more contacts and a specific event.

Format: `event unlink EVENT_INDEX --contact CONTACT_INDEX/INDICES`

* `EVENT_INDEX` refers to the index of the event in the displayed event list.

* `CONTACT_INDEX/INDICES` refers to the index(es) of the contact(s) to be unlinked from the event.

* You can unlink multiple contacts by providing more than one contact index.

Examples:
* `event unlink 1 --contact 3 4`

### Tagging an event: `tag`
Adds one or more tags to the specified event. Tags help categorize and filter events more easily (e.g., by priority, role, type, etc.).

Format: `event tag EVENT_INDEX --tag TAG(S)`

* `EVENT_INDEX` refers to the index of the event in the currently displayed event list.

* You can add one or more tags by listing them after `--tag`.

* Duplicate tags (already added to the event) are not allowed.

* Tags are case-sensitive and stored as-is.

Example: 
* `event tag 1 --tag TA Important`

### Untagging an event: `untag`
Removes one or more tags from the specified event. Useful for decluttering or updating tag information as event details change.

Format: `event untag EVENT_INDEX --tag TAG(S)`

* `EVENT_INDEX` refers to the index of the event in the currently displayed event list.

* You can remove one or more tags by listing them after `--tag`.

* Tags are case-sensitive and must match the tag(s) assigned to the event.

**Important**: `EVENT_INDEX` must be a positive integer.

Example:
* `event untag 1 --tag important weekly`

### Logging attendance for an event: `log`
Records the attendance of one or more contacts for a specific event. This allows you to track who actually attended, separate from just being linked to the event.

Format: `event log EVENT_INDEX --contact CONTACT_INDEX [CONTACT_INDEX/INDICES]`

* `EVENT_INDEX` refers to the index of the event in the displayed event list.

* `CONTACT_INDEX/INDICES` refers to the index(es) of the contacts linked to the event, as shown by event info.

* You can log multiple contacts at once by listing their indices.

* You cannot unlog a contact that is already unlogged.

**Tip:** Use event info to view the current linked contacts and their indices.

Example:
* `event log 1 --contact 1 2 3`
(Logs attendance for contacts 1, 2, and 3 at the event with index 1)

### Removing attendance logs from an event: `unlog`
Removes the attendance logs of one or more contacts from a specific event. Useful for correcting mistakes or updating attendance records.

Format:
`event unlog EVENT_INDEX --contact CONTACT_INDEX [CONTACT_INDEX/INCIDES]`

* `EVENT_INDEX` refers to the index of the event in the displayed event list.

* `CONTACT_INDEX/INDICES` refers to the index(es) of the contacts previously logged for that event, as shown in event info.

* You can unlog multiple contacts at once by listing their indices.

* You cannot unlog a contact that is already unlogged

**Important:** EVENT_INDEX must be a positive integer.

**Tip:** Use event info to view currently logged contacts and their indices.

Example:
`event unlog 1 --contact 1 2 3`
(Removes attendance logs for contacts 1, 2, and 3 from the event with index 1)

### Filtering events: `filter`
Filters the list of events based on one or more criteria. You can search by event name, time, location, tags, or linked contacts using logical operators to combine multiple values.

Format:
`event filter --<COLUMN> [<OPERATOR>:] <VALUE(S)> [...]`

Supported columns:
* `--name`: Event name.
* `--start`: Start time.
* `--end`: End time.
* `--location`: Event location.
* `--tag`: Tags.
* `--contact`: Linked contacts.

Operators (optional):
* `and` (default): All values must match.
* `or`: At least one value must match.
* `nand`: None of the values must match.
* `nor`: Reject all values.

If an operator is not provided, it defaults to `and`.

**Value formats**:

Name, Location and Tag:
* Provide one or more keywords separated by spaces.
* Keywords are case-insensitive and support partial matches.

Start & End Time:
* Provide one or more closed intervals, each written as: `[<INTERVAL_START>/<INTERVAL_END>]`.
* Each datetime must follow the format: `YY-MM-DD HH:MM`.
* At least one of the two bounds must be specified. You can replace an empty bounds using `-`.

Contact:
* Provide contact indices (as shown in the current contact list).

* Matches events linked to all the specified contacts (default `and`, unless overridden).

Examples:
* `event filter --name or: Exam PRESENTATION`. 
  * Find todos whose name contains at least one of the keywords "exam" or "presentation."

* `event filter --name CS1010S eXAM --start or: [25-03-13 23:59/25-03-20 23:59] [25-03-27 23:59/-]`
  * Find todos whose name contains both the keywords `"CS1010S"` and `"exam"` and whose start time is between `25-03-13 23:59` and `25-03-20 23:59`(inclusive) or after `25-03-27 23:59` (inclusive).

* `event filter --location nand: NUS Home --contact 1 2 3`
  * Find todos whose location does not contain the keywords "NUS" or "home" and which are is linked to the people currently at index 1, 2 and 3.
