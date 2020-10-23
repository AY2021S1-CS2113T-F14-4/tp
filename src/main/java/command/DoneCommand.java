package command;

import eventlist.EventList;
import exception.NuScheduleException;
import exception.UndefinedEventException;
import exception.WritingFileException;
import locationlist.BusStopList;
import locationlist.LocationList;
import storage.Storage;
import ui.UI;

/**
 * Represents the command call when the user mark a task as done.
 */
public class DoneCommand extends Command {
    private final int eventIndex;

    public DoneCommand(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    /**
     * Mark the task as done, and update the file.
     *
     * @param events    the list of Events.
     * @param locations the list of Locations.
     * @param busStops  the list of BusStops.
     * @param ui        do outputs.
     * @param storage   store the data.
     * @throws UndefinedEventException the case when the the user trying to operate (delete/edit/done) some events
     *                                 that does not exist.
     * @throws WritingFileException    the file is not correctly written.
     */
    @Override
    public void execute(EventList events, LocationList locations, BusStopList busStops, UI ui, Storage storage)
            throws UndefinedEventException, WritingFileException {
        if (eventIndex <= -1 || eventIndex >= events.getSize()) {
            throw new UndefinedEventException(eventIndex + 1);
        }
        events.doneEvent(eventIndex);
        ui.printDoneMessage(events.get(eventIndex));
        storage.writeFile(events.getEventList());
    }
}
