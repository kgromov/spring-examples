package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;

import java.util.Collection;

/**
 * Created by jt on 6/28/17.
 */
public interface UnitOfMeasureService {

    Collection<UnitOfMeasureCommand> listAllUoms();
}
