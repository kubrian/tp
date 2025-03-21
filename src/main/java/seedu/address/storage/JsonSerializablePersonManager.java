package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.ItemManager;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonManager;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializablePersonManager {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonManager} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePersonManager(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ItemManager<Person>} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePersonManager(ItemManager<Person> source) {
        persons.addAll(source.getItemList().stream().map(JsonAdaptedPerson::new).toList());
    }

    /**
     * Converts this address book into the model's {@code PersonManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PersonManager toModelType() throws IllegalValueException {
        PersonManager personManager = new PersonManager();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (personManager.hasItem(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            personManager.addItem(person);
        }
        return personManager;
    }

}
