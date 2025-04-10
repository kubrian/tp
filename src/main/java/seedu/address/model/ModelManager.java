package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.logging.Logger;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.contact.ContactManagerAndList;
import seedu.address.model.contact.ContactManagerWithFilteredList;
import seedu.address.model.event.EventManagerAndList;
import seedu.address.model.event.EventManagerWithFilteredList;
import seedu.address.model.todo.TodoManagerAndList;
import seedu.address.model.todo.TodoManagerWithFilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;

    private final ContactManagerAndList contactManagerAndList;
    private final TodoManagerAndList todoManagerAndList;
    private final EventManagerAndList eventManagerAndList;

    /**
     * Initializes a ModelManager with the given managers with lists and userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs,
                        ContactManagerAndList contactManagerAndList,
                        TodoManagerAndList todoManagerAndList,
                        EventManagerAndList eventManagerAndList) {
        requireAllNonNull(userPrefs, contactManagerAndList, todoManagerAndList, eventManagerAndList);

        logger.fine("Initializing with contact manager: " + contactManagerAndList
                + ", todo manager: " + todoManagerAndList
                + ", event manager: " + eventManagerAndList
                + ", and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.contactManagerAndList = contactManagerAndList;
        this.todoManagerAndList = todoManagerAndList;
        this.eventManagerAndList = eventManagerAndList;
    }

    /**
     * Initializes a default ModelManager.
     */
    public ModelManager() {
        this(new UserPrefs(), new ContactManagerWithFilteredList(),
                new TodoManagerWithFilteredList(), new EventManagerWithFilteredList());
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        userPrefs.setContactListFilePath(contactListFilePath);
    }

    @Override
    public Path getTodoListFilePath() {
        return userPrefs.getTodoListFilePath();
    }

    @Override
    public void setTodoListFilePath(Path todoListFilePath) {
        requireNonNull(todoListFilePath);
        userPrefs.setTodoListFilePath(todoListFilePath);
    }

    @Override
    public Path getEventListFilePath() {
        return userPrefs.getEventListFilePath();
    }

    @Override
    public void setEventListFilePath(Path eventListFilePath) {
        requireNonNull(eventListFilePath);
        userPrefs.setEventListFilePath(eventListFilePath);
    }

    @Override
    public ContactManagerAndList getContactManagerAndList() {
        return contactManagerAndList;
    }

    @Override
    public TodoManagerAndList getTodoManagerAndList() {
        return todoManagerAndList;
    }

    @Override
    public EventManagerAndList getEventManagerAndList() {
        return eventManagerAndList;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager otherModelManager)) {
            return false;
        }

        return userPrefs.equals(otherModelManager.userPrefs)
                && contactManagerAndList.equals(otherModelManager.contactManagerAndList)
                && todoManagerAndList.equals(otherModelManager.todoManagerAndList)
                && eventManagerAndList.equals(otherModelManager.eventManagerAndList);
    }

}
